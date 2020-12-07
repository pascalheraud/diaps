package io.github.pascalheraud.diaps.apis;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.pascalheraud.diaps.db.Bilan;
import io.github.pascalheraud.diaps.db.BilanDAO;
import io.github.pascalheraud.diaps.db.BilanItem;
import io.github.pascalheraud.diaps.db.BilanItemDAO;
import io.github.pascalheraud.diaps.db.BilanItemReport;
import io.github.pascalheraud.diaps.db.BilanItemReportDAO;
import io.github.pascalheraud.diaps.db.Item;
import io.github.pascalheraud.diaps.db.Item.Category;
import io.github.pascalheraud.diaps.db.ItemDAO;
import io.github.pascalheraud.diaps.db.Personne;
import io.github.pascalheraud.diaps.db.PersonneDAO;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class DIAPSApisController {
	public interface Add {

	}

	public interface Update {

	}

	public interface Reference {

	}

	@Autowired
	private PersonneDAO personneDAO;
	@Autowired
	private BilanItemDAO bilanItemDAO;
	@Autowired
	private BilanItemReportDAO bilanItemReportDAO;
	@Autowired
	private BilanDAO bilanDAO;
	@Autowired
	private ItemDAO itemDAO;

	@GetMapping(value = "/apis/personnes/all")
	public List<Personne> getAllPersonnes() {
		return personneDAO.findAllByOrderById();
	}

	@PostMapping(value = "/apis/personnes/add")
	public Personne addPersonne(@Validated(Add.class) @RequestBody Personne personne) {
		if (personne.handedInfo == null) {
			personne.handedInfo = "";
		}
		return personneDAO.save(personne);
	}

	@PostMapping(value = "/apis/personnes/update")
	public Personne updatePersonne(@Validated(Update.class) @RequestBody Personne personne) {
		return personneDAO.save(personne);
	}

	@PostMapping(value = "/apis/personnes/del")
	public void deletePersonne(@Validated(Reference.class) @RequestBody Personne personne) {
		personneDAO.delete(personne);
	}

	@PostMapping(value = "/apis/bilan/get")
	@Transactional
	public BilanReport getBilan(@Validated(Reference.class) @RequestBody Personne personne) {
		BilanReport bilanReport = getBilanReport(personne);
		bilanReport.hasFM = bilanItemDAO.countByBilanAndCategory(bilanReport.bilan.id, Category.EF) > 0;
		bilanReport.hasDys = bilanItemDAO.countByBilanAndCategory(bilanReport.bilan.id, Category.OTHER) > 0;
		return bilanReport;
	}

	@PostMapping(value = "/apis/bilan/fm/get")
	@Transactional
	public BilanReport getBilanFM(@Validated(Reference.class) @RequestBody Personne personne) {
		final BilanReport bilanReport = getBilanReport(personne);
		boolean hasFM = bilanItemDAO.countByBilanIdAndCategoryAndDysNumberNull(bilanReport.bilan.id, Category.EF) > 0;
		boolean hasDys = bilanItemDAO.countByBilanIdAndCategoryAndDysNumberNull(bilanReport.bilan.id, Category.OTHER) > 0;
		if (!hasFM) {
			List<Item> formeItems = itemDAO.findAllByCategory(Category.EF);
			formeItems.stream().forEach(item -> {
				if (!hasDys || item.category != Category.OTHER) {
					BilanItem bilanItem = new BilanItem();
					bilanItem.bilanId = bilanReport.bilan.id;
					bilanItem.itemId = item.id;
					bilanItem.note = 0;
					bilanItemDAO.save(bilanItem);
				}
			});
			List<Item> mouvementItems = itemDAO.findAllByCategory(Category.EM);
			mouvementItems.stream().forEach(item -> {
				if (!hasDys || item.category != Category.OTHER) {
					BilanItem bilanItem = new BilanItem();
					bilanItem.bilanId = bilanReport.bilan.id;
					bilanItem.itemId = item.id;
					bilanItem.note = 0;
					bilanItemDAO.save(bilanItem);
				}
			});
		}
		List<BilanItemReport> formeItems = bilanItemReportDAO.findAllByCategoryAndBilanIdOrderByNumber(Category.EF, bilanReport.bilan.id);
		bilanReport.formeItems = groupItems(formeItems, BilanItemReport::getItemCategory);

		List<BilanItemReport> mouvementItems = bilanItemReportDAO.findAllByCategoryAndBilanIdOrderByNumber(Category.EM, bilanReport.bilan.id);
		bilanReport.mouvementItems = groupItems(mouvementItems, BilanItemReport::getItemCategory);

		return bilanReport;
	}

	@PostMapping(value = "/apis/bilan/dys/get")
	@Transactional
	public BilanReport getBilanDys(@Validated(Reference.class) @RequestBody Personne personne) {
		final BilanReport bilanReport = getBilanReport(personne);
		if (bilanItemDAO.countByBilanAndCategory(bilanReport.bilan.id, Category.OTHER) == 0) {
			boolean hasFM = bilanItemDAO.countByBilanIdAndCategoryAndDysNumberNull(bilanReport.bilan.id, Category.EF) > 0;
			List<Item> formeItems = itemDAO.findAllByDysNumberNotNull();
			formeItems.stream().forEach(item -> {
				if (!hasFM || item.category == Category.OTHER) {
					BilanItem bilanItem = new BilanItem();
					bilanItem.bilanId = bilanReport.bilan.id;
					bilanItem.itemId = item.id;
					bilanItem.note = 0;
					bilanItemDAO.save(bilanItem);
				}
			});
		}

		List<BilanItemReport> items = bilanItemReportDAO.findAllByDysNumberNotNullAndBilanIdOrderByDysNumber(bilanReport.bilan.id);
		bilanReport.dysItems = groupItems(items, BilanItemReport::getItemDysGroup);
		return bilanReport;
	}

	private <KeyClass> Map<KeyClass, List<BilanItemReport>> groupItems(List<BilanItemReport> items, Function<BilanItemReport, KeyClass> keyMapper) {
		Function<BilanItemReport, List<BilanItemReport>> valueMapper = (BilanItemReport bir) -> {
			List<BilanItemReport> res = new ArrayList<>();
			res.add(bir);
			return res;
		};
		BinaryOperator<List<BilanItemReport>> merger = (List<BilanItemReport> l, List<BilanItemReport> r) -> {
			l.addAll(r);
			return l;
		};
		Supplier<Map<KeyClass, List<BilanItemReport>>> mapFactory = LinkedHashMap<KeyClass, List<BilanItemReport>>::new;
		return items.stream().collect(Collectors.toMap(keyMapper, valueMapper, merger, mapFactory));
	}

	private BilanReport getBilanReport(Personne personne) {
		final BilanReport bilanReport = new BilanReport();
		bilanReport.personne = personneDAO.findById(personne.id).get();
		Bilan bilan = bilanDAO.getByPersonneId(personne.id);
		if (bilan == null) {
			bilan = new Bilan();
			bilan.occurenceDate = new Date();
			bilan.personneId = personne.id;
			bilanDAO.save(bilan);
		}
		bilanReport.bilan = bilan;
		return bilanReport;
	}

	@PostMapping(value = "/apis/bilan/item/update")
	public BilanItem updateBilanItem(@Validated(Update.class) @RequestBody BilanItem bilanItem) {
		if (bilanItem.description == null) {
			bilanItem.description = "";
		}
		return bilanItemDAO.save(bilanItem);
	}
}
