package io.github.pascalheraud.diaps.web;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import io.github.pascalheraud.diaps.db.Bilan;
import io.github.pascalheraud.diaps.db.BilanItemReport;
import io.github.pascalheraud.diaps.db.Personne;
import io.github.pascalheraud.diaps.db.Personne.ClassRoom;
import io.github.pascalheraud.diaps.model.EFMStandardManager;
import io.github.pascalheraud.diaps.model.WritingSpeedManager;

public class PersonneReport {
	private WritingSpeedManager writingSpeedManager;

	private EFMStandardManager efmStandardManager;

	public Bilan bilan;

	public Personne personne;

	public PersonneReport() {
	}

	public PersonneReport(WritingSpeedManager writingSpeedManager, EFMStandardManager efmStandardManager) {
		this.writingSpeedManager = writingSpeedManager;
		this.efmStandardManager = efmStandardManager;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public Bilan getBilan() {
		return bilan;
	}

	public List<BilanItemReport> formeItems;
	public List<BilanItemReport> mouvementItems;

	public List<BilanItemReport> dysPageItems;
	public List<BilanItemReport> dysMaladresseItems;
	public List<BilanItemReport> dysFormesEtProportionsItems;

	public String getDateOfBirth() {
		return new SimpleDateFormat("dd/MM/yyyy").format(personne.dateOfBirth);
	}

	public String getDateOfReport() {
		return new SimpleDateFormat("dd/MM/yyyy").format(personne.dateOfReport);
	}

	public String getAge() {
		Calendar calendarDOB = Calendar.getInstance();
		calendarDOB.setTime(personne.dateOfBirth);
		Calendar calendarNow = Calendar.getInstance();
		int age = calendarNow.get(Calendar.YEAR) - calendarDOB.get(Calendar.YEAR);
		if (calendarDOB.get(Calendar.DAY_OF_YEAR) > calendarNow.get(Calendar.DAY_OF_YEAR)) {
			age--;
		}
		return String.valueOf(age);
	}

	public float getAgeFloat() {
		long millisecondsDiff = (new Date()).getTime() - personne.dateOfBirth.getTime();
		return millisecondsDiff / 1000f / 60f / 60f / 24f / 365.25f;
	}

	public String getFirstName() {
		return personne.firstName;
	}

	public String getFirstNameDe() {
		String lowerCaseFN = personne.firstName.toLowerCase();
		if (lowerCaseFN.startsWith("a") || lowerCaseFN.startsWith("e") || lowerCaseFN.startsWith("i") || lowerCaseFN.startsWith("o")
				|| lowerCaseFN.startsWith("u")) {
			return "’" + personne.firstName;
		}
		return "e " + personne.firstName;
	}

	public String getFirstNameDeUppercase() {
		return getFirstNameDe().toUpperCase();
	}

	public String getClassRoom() {
		return getClassRoomName(personne.classRoom);
	}

	public String getClassRoomName(ClassRoom classRoom) {
		switch (classRoom) {
		case SIXIEME:
			return "6eme";
		case CINQUIEME:
			return "5eme";
		case QUATRIEME:
			return "4eme";
		case TROISIEME:
			return "3eme";
		default:
			return classRoom.toString();
		}
	}

	public String getHanded() {
		switch (personne.handed) {
		case LEFT:
			return "Gaucher";
		case RIGHT:
			return "Droitier";
		case UNKNOWN:
		default:
			return "Indéfini";
		}
	}

	private String formatDouble(double value) {
		return new DecimalFormat("##.#").format(value);
	}

	public String getScoreEf() {
		return formatDouble(getScoreEfDouble());
	}

	private double getScoreEfDouble() {
		return this.formeItems.stream().mapToDouble(this::getStdScore).sum();
	}

	public String getScoreEm() {
		return formatDouble(getScoreEmDouble());
	}

	private double getScoreEmDouble() {
		return this.mouvementItems.stream().mapToDouble(this::getStdScore).sum();
	}

	public List<BilanItemReport> getPageItemsSupZeroForPage() {
		return dysPageItems.stream().filter(i -> i.note > 0).collect(Collectors.toList());
	}

	public List<BilanItemReport> getMaladresseItemsSupZeroForPage() {
		return dysMaladresseItems.stream().filter(i -> i.note > 0).collect(Collectors.toList());
	}

	public List<BilanItemReport> getFormeItemsSupZeroForPage() {
		return dysFormesEtProportionsItems.stream().filter(i -> i.note > 0).collect(Collectors.toList());
	}

	public List<PersonneReport> getDysIsSup19() {
		return getListFromMe(getDysNote() >= 19);
	}

	private List<PersonneReport> getListFromMe(boolean b) {
		ArrayList<PersonneReport> res = new ArrayList<>();
		if (b) {
			res.add(this);
		}
		return res;
	}

	public List<PersonneReport> getDysIsSup14() {
		return getListFromMe(getDysNote() >= 14 && getDysNote() < 19);
	}

	public List<PersonneReport> getDysIsSup10() {
		return getListFromMe(getDysNote() >= 10 && getDysNote() < 14);
	}

	public List<PersonneReport> getDysIsInf10() {
		return getListFromMe(getDysNote() < 10);
	}

	public String getDysNoteString() {
		return formatDouble(getDysNote());
	}

	private double getDysNote() {
		return getDysNoteValue(dysPageItems) + getDysNoteValue(dysMaladresseItems) + getDysNoteValue(dysFormesEtProportionsItems);
	}

	private double getDysNoteValue(List<BilanItemReport> items) {
		return items.stream().mapToDouble(this::getDysItemNote).sum();
	}

	private double getDysItemNote(BilanItemReport item) {
		return item.note * item.item.dysRate;
	}

	public double getStdScore(BilanItemReport bilanItem) {
		return bilanItem.note * bilanItem.item.rate;
	}

	public List<PersonneReport> getHasWritingSpeedMax() {
		return getListFromMe(bilan.writingSpeedMax != null);
	}

	public int getWritingSpeedNormal() {
		return bilan.writingSpeedNormal;
	}

	public int getWritingSpeedMax() {
		return bilan.writingSpeedMax;
	}

	public List<PersonneReport> getHasWritingSpeedNormal() {
		return getListFromMe(bilan.writingSpeedNormal != null);
	}

	public String getStandardNormalSpeedForClassRoom() {
		Integer res = writingSpeedManager.getStandardSpeedForClassRoom(personne.gender, personne.classRoom, true);
		if (res == null) {
			return "";
		}
		return res.toString();
	}

	public String getStandardMaxSpeedForClassRoom() {
		Integer res = writingSpeedManager.getStandardSpeedForClassRoom(personne.gender, personne.classRoom, false);
		if (res == null) {
			return "";
		}
		return res.toString();
	}

	public String getAgeWritingSpeedNormal() {
		return writingSpeedManager.getAgeWritingSpeed(bilan.writingSpeedNormal, personne.gender, true);
	}

	public String getAgeWritingSpeedMax() {
		return writingSpeedManager.getAgeWritingSpeed(bilan.writingSpeedMax, personne.gender, false);
	}

	public String getClassRoomWritingSpeedNormal() {
		return writingSpeedManager.getClassRoomWritingSpeed(bilan.writingSpeedNormal, personne.gender, true);
	}

	public String getClassRoomWritingSpeedMax() {
		return writingSpeedManager.getClassRoomWritingSpeed(bilan.writingSpeedMax, personne.gender, false);
	}

	public String getClassRoomEF() {
		return efmStandardManager.getClassRoomForEF(getScoreEfDouble(), personne.gender);
	}

	public String getClassRoomEM() {
		return efmStandardManager.getClassRoomForEF(getScoreEmDouble(), personne.gender);
	}

	public String getAgeEF() {
		return efmStandardManager.getAgeForEF(getScoreEfDouble(), personne.gender);
	}

	public String getAgeEM() {
		return efmStandardManager.getAgeForEF(getScoreEmDouble(), personne.gender);
	}
	// public List<PersonneReport> getHasStandardWritingSpeed() {
	// return getListFromMe(isStandardWritingSpeed());
	// }

	// private boolean isStandardWritingSpeed() {
	// return getWritingSpeedNormal() >= getClassRoomWritingSpeedNormal() && getWritingSpeedMax() >= getClassRoomWritingSpeedMax();
	// }
	//
	// public List<PersonneReport> getHasNotStandardWritingSpeed() {
	// return getListFromMe(!isStandardWritingSpeed());
	// }
}
