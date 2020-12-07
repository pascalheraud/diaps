package io.github.pascalheraud.diaps.web;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.wickedsource.docxstamper.DocxStamper;
import org.wickedsource.docxstamper.DocxStamperConfiguration;

import io.github.pascalheraud.diaps.db.BilanDAO;
import io.github.pascalheraud.diaps.db.BilanItemReportDAO;
import io.github.pascalheraud.diaps.db.Item.Category;
import io.github.pascalheraud.diaps.db.Item.DysGroup;
import io.github.pascalheraud.diaps.db.PersonneDAO;
import io.github.pascalheraud.diaps.db.ReportModel;
import io.github.pascalheraud.diaps.db.ReportModelDAO;

@Controller
@RequestMapping(value = "/", produces = { "text/html; charset=UTF-8" })
public class DIAPSController {
	@Autowired
	private PersonneDAO personneDAO;

	@Autowired
	private BilanDAO bilanDAO;

	@Autowired
	private BilanItemReportDAO bilanItemReportDAO;

	@Autowired
	private ReportModelDAO reportModelDAO;

	@RequestMapping("/")
	public String home() {
		return "redirect:/personnes";
	}

	@RequestMapping("/personnes")
	public String personnes() {
		return "personnes";
	}

	@RequestMapping("/bilan/{personneId}")
	public String bilan(@PathVariable Long personneId, Model model) {
		if (!personneDAO.findById(personneId).isPresent()) {
			throw new RuntimeException("not found");
		}
		return "bilan";
	}

	@RequestMapping("/bilan/fm/{personneId}")
	public String bilanef(@PathVariable Long personneId, Model model) {
		if (!personneDAO.findById(personneId).isPresent()) {
			throw new RuntimeException("not found");
		}
		return "bilanfm";
	}

	@RequestMapping("/bilan/dys/{personneId}")
	public String bilandys(@PathVariable Long personneId, Model model) {
		if (!personneDAO.findById(personneId).isPresent()) {
			throw new RuntimeException("not found");
		}
		return "bilandys";
	}

	@RequestMapping(value = "/personne/{personneId}/rapport", produces = "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
	@ResponseBody
	@SuppressWarnings("unchecked")
	public byte[] personneReport(@PathVariable Long personneId, Model model, HttpServletResponse response) throws IOException {
		PersonneReport personneReport = new PersonneReport();
		personneReport.personne = personneDAO.findById(personneId).get();
		personneReport.bilan = bilanDAO.getByPersonneId(personneId);
		personneReport.formeItems = bilanItemReportDAO.findAllByCategoryAndBilanIdOrderByNumber(Category.EF, personneReport.bilan.id);
		personneReport.mouvementItems = bilanItemReportDAO.findAllByCategoryAndBilanIdOrderByNumber(Category.EM, personneReport.bilan.id);
		personneReport.dysPageItems = bilanItemReportDAO.findAllBilanIdAndDysGroupOrderByDysNumber(personneReport.bilan.id, DysGroup.PAGE);
		personneReport.dysMaladresseItems = bilanItemReportDAO.findAllBilanIdAndDysGroupOrderByDysNumber(personneReport.bilan.id, DysGroup.MALADRESSE);
		personneReport.dysFormesEtProportionsItems = bilanItemReportDAO.findAllBilanIdAndDysGroupOrderByDysNumber(personneReport.bilan.id,
				DysGroup.FORMES_ET_PROPORTIONS);
		List<ReportModel> res = reportModelDAO.findAllByOrderById();
		if (res.size() == 0) {
			response.sendRedirect("/model/upload/new");
			return null;
		}

		InputStream template = new ByteArrayInputStream(res.get(0).data);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		DocxStamperConfiguration docxStamperConfiguration = new DocxStamperConfiguration();
		DocxStamper<PersonneReport> stamper = docxStamperConfiguration.setFailOnUnresolvedExpression(false).build();
		stamper.stamp(template, personneReport, out);
		return out.toByteArray();
	}

	@GetMapping("/model/upload")
	public String upload(Model model) throws IOException {
		return "upload";
	}

	@GetMapping("/model/upload/new")
	public String uploadNew(Model model) throws IOException {
		model.addAttribute("newfile",
				"Pour pouvoir générer un rapport, il faut tout d'abord envoyer un modèle de rapport. Veuillez sélectionner un fichier ci-dessous et cliquer sur envoyer.");
		return "upload";
	}

	@PostMapping("/model/upload")
	public String submit(@RequestParam MultipartFile reportModelFile, Model model) throws IOException {
		if (reportModelFile.isEmpty()) {
			model.addAttribute("fileuploaderror", "Pas de fichier sélectionné. Veuillez sélectionner un fichier ci-dessous et cliquer sur envoyer.");
		} else if (!reportModelFile.getOriginalFilename().endsWith(".docx")) {
			model.addAttribute("fileuploaderror", "Merci de sélectionner un fichier au format .docx");
		} else {
			List<ReportModel> res = reportModelDAO.findAllByOrderById();
			ReportModel reportModel;
			if (res.size() == 0) {
				reportModel = new ReportModel();
			} else {
				reportModel = res.get(0);
			}
			reportModel.data = reportModelFile.getBytes();
			reportModelDAO.save(reportModel);
			model.addAttribute("uploaded", true);
		}
		return "upload";
	}
}
