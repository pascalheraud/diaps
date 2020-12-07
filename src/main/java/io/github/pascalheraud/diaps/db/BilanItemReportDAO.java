package io.github.pascalheraud.diaps.db;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import io.github.pascalheraud.diaps.db.Item.Category;
import io.github.pascalheraud.diaps.db.Item.DysGroup;

public interface BilanItemReportDAO extends CrudRepository<BilanItemReport, Long> {
	@Query(value = "select bi.id,bi.bilanId,bi.itemId,bi.note,bi.description,"
			+ "item.id as refItemId,item.number as refItemNumber, item.label as refItemLabel, item.rate as refItemRate, item.category as refItemCategory, item.dysGroup  as refItemDysGroup, item.dysNumber as refItemDysNumber, item.dysRate as refItemDysRate"
			+ " from diaps_bilan_item bi join diaps_item item on bi.itemid = item.id where item.category=:category::diaps_item_category and bi.bilanid = :bilanId"
			+ " order by item.number asc")
	List<BilanItemReport> findAllByCategoryAndBilanIdOrderByNumber(Category category, Long bilanId);

	@Query(value = "select bi.id,bi.bilanId,bi.itemId,bi.note,bi.description,"
			+ "item.id as refItemId,item.number as refItemNumber, item.label as refItemLabel, item.rate as refItemRate, item.category as refItemCategory, item.dysGroup  as refItemDysGroup, item.dysNumber as refItemDysNumber, item.dysRate as refItemDysRate"
			+ " from diaps_bilan_item bi join diaps_item item on bi.itemid = item.id where item.dysNumber is not null and bi.bilanid = :bilanId"
			+ " order by item.dysnumber asc")
	List<BilanItemReport> findAllByDysNumberNotNullAndBilanIdOrderByDysNumber(Long bilanId);

	@Query(value = "select bi.id,bi.bilanId,bi.itemId,bi.note,bi.description,"
			+ "item.id as refItemId,item.number as refItemNumber, item.label as refItemLabel, item.rate as refItemRate, item.category as refItemCategory, item.dysGroup  as refItemDysGroup, item.dysNumber as refItemDysNumber, item.dysRate as refItemDysRate"
			+ " from diaps_bilan_item bi join diaps_item item on bi.itemid = item.id where item.dysGroup = :dysGroup::diaps_item_dysgroup and bi.bilanid = :bilanId"
			+ " order by item.dysnumber asc")
	List<BilanItemReport> findAllBilanIdAndDysGroupOrderByDysNumber(Long bilanId, DysGroup dysGroup);
}
