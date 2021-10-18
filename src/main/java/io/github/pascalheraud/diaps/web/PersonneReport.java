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
import io.github.pascalheraud.diaps.db.Personne.Gender;
import io.github.pascalheraud.diaps.model.EFMStandardManager;
import io.github.pascalheraud.diaps.model.WritingSpeedManager;

public class PersonneReport {
	private WritingSpeedManager writingSpeedManager;

	private EFMStandardManager efmStandardManager;

	public Bilan bilan;

	public Personne personne;

	private boolean writingSpeed;

	private boolean itemsF;

	private boolean itemsM;

	private boolean graphoMoteur;

	public PersonneReport() {
	}

	public PersonneReport(WritingSpeedManager writingSpeedManager, EFMStandardManager efmStandardManager, boolean writingSpeed, boolean itemsF, boolean itemsM,
			boolean graphoMoteur) {
		this.writingSpeedManager = writingSpeedManager;
		this.efmStandardManager = efmStandardManager;
		this.writingSpeed = writingSpeed;
		this.itemsF = itemsF;
		this.itemsM = itemsM;
		this.graphoMoteur = graphoMoteur;
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
		calendarNow.set(Calendar.MILLISECOND, 0);
		calendarNow.set(Calendar.SECOND, 0);
		calendarNow.set(Calendar.MINUTE, 0);
		calendarNow.set(Calendar.HOUR, 0);
		int age = calendarNow.get(Calendar.YEAR) - calendarDOB.get(Calendar.YEAR);
		if (calendarDOB.get(Calendar.DAY_OF_YEAR) > calendarNow.get(Calendar.DAY_OF_YEAR)) {
			age--;
		}
		calendarDOB.add(Calendar.YEAR, age);
		calendarDOB.add(Calendar.MONTH, 1);
		int monthes = 0;
		while (calendarDOB.compareTo(calendarNow) < 0) {
			calendarDOB.add(Calendar.MONTH, 1);
			monthes++;
		}
		String yearAge = age + " ans";
		if (monthes > 0) {
			yearAge += " et " + monthes + " mois";
		}
		return yearAge;
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
			return personne.gender == Gender.FEMALE ? "gauchère" : "gaucher";
		case RIGHT:
			return personne.gender == Gender.FEMALE ? "droitière" : "droitier";
		case UNKNOWN:
		default:
			return "Indéfini";
		}
	}

	private String formatDouble(double value) {
		return new DecimalFormat("##.#").format(value);
	}

	public String getScoreEf() {
		if (!itemsF) {
			return null;
		}
		return formatDouble(getScoreEfDouble());
	}

	private Double getScoreEfDouble() {
		if (!itemsF) {
			return null;
		}
		return this.formeItems.stream().mapToDouble(this::getStdScore).sum();
	}

	public String getScoreEm() {
		if (!itemsM) {
			return null;
		}
		return formatDouble(getScoreEmDouble());
	}

	private Double getScoreEmDouble() {
		if (!itemsM) {
			return null;
		}
		return this.mouvementItems.stream().mapToDouble(this::getStdScore).sum();
	}

	public List<BilanItemReport> getPageItemsSupZeroForPage() {
		if (!graphoMoteur) {
			return new ArrayList<>();
		}
		return dysPageItems.stream().filter(i -> i.note > 0).collect(Collectors.toList());
	}

	public List<BilanItemReport> getMaladresseItemsSupZeroForPage() {
		if (!graphoMoteur) {
			return new ArrayList<>();
		}
		return dysMaladresseItems.stream().filter(i -> i.note > 0).collect(Collectors.toList());
	}

	public List<BilanItemReport> getFormeItemsSupZeroForPage() {
		if (!graphoMoteur) {
			return new ArrayList<>();
		}
		return dysFormesEtProportionsItems.stream().filter(i -> i.note > 0).collect(Collectors.toList());
	}

	public List<PersonneReport> getDysIsSup19() {
		if (!graphoMoteur) {
			return new ArrayList<>();
		}
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
		if (!graphoMoteur) {
			return new ArrayList<>();
		}
		return getListFromMe(getDysNote() >= 14 && getDysNote() < 19);
	}

	public List<PersonneReport> getDysIsSup10() {
		if (!graphoMoteur) {
			return new ArrayList<>();
		}
		return getListFromMe(getDysNote() >= 10 && getDysNote() < 14);
	}

	public List<PersonneReport> getDysIsInf10() {
		if (!graphoMoteur) {
			return new ArrayList<>();
		}
		return getListFromMe(getDysNote() < 10);
	}

	public String getDysNoteString() {
		if (!graphoMoteur) {
			return null;
		}
		return formatDouble(getDysNote());
	}

	private Double getDysNote() {
		if (!graphoMoteur) {
			return null;
		}
		return getDysNoteValue(dysPageItems) + getDysNoteValue(dysMaladresseItems) + getDysNoteValue(dysFormesEtProportionsItems);
	}

	private Double getDysNoteValue(List<BilanItemReport> items) {
		if (!graphoMoteur) {
			return null;
		}
		return items.stream().mapToDouble(this::getDysItemNote).sum();
	}

	private Double getDysItemNote(BilanItemReport item) {
		if (!graphoMoteur) {
			return null;
		}
		return (double) item.note * (double) item.item.dysRate;
	}

	public Double getStdScore(BilanItemReport bilanItem) {
		if (!graphoMoteur) {
			return null;
		}
		return (double) bilanItem.note * (double) bilanItem.item.rate;
	}

	public List<PersonneReport> getHasWritingSpeedMax() {
		if (!writingSpeed) {
			return new ArrayList<>();
		}
		return getListFromMe(bilan.writingSpeedMax != null);
	}

	public Integer getWritingSpeedNormal() {
		if (!writingSpeed) {
			return null;
		}
		return bilan.writingSpeedNormal;
	}

	public Integer getWritingSpeedMax() {
		if (!writingSpeed) {
			return null;
		}
		return bilan.writingSpeedMax;
	}

	public List<PersonneReport> getHasWritingSpeedNormal() {
		if (!writingSpeed) {
			return new ArrayList<>();
		}
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
		if (!writingSpeed) {
			return null;
		}
		return writingSpeedManager.getAgeWritingSpeed(bilan.writingSpeedNormal, personne.gender, true);
	}

	public String getAgeWritingSpeedMax() {
		if (!writingSpeed) {
			return null;
		}
		return writingSpeedManager.getAgeWritingSpeed(bilan.writingSpeedMax, personne.gender, false);
	}

	public String getClassRoomWritingSpeedNormal() {
		if (!writingSpeed) {
			return null;
		}
		return writingSpeedManager.getClassRoomWritingSpeed(bilan.writingSpeedNormal, personne.gender, true);
	}

	public String getClassRoomWritingSpeedMax() {
		if (!writingSpeed) {
			return null;
		}
		return writingSpeedManager.getClassRoomWritingSpeed(bilan.writingSpeedMax, personne.gender, false);
	}

	public String getClassRoomEF() {
		if (!itemsF) {
			return null;
		}
		return efmStandardManager.getClassRoomForEF(getScoreEfDouble(), personne.gender);
	}

	public String getClassRoomEM() {
		if (!itemsM) {
			return null;
		}
		return efmStandardManager.getClassRoomForEF(getScoreEmDouble(), personne.gender);
	}

	public String getAgeEF() {
		if (!itemsF) {
			return null;
		}
		return efmStandardManager.getAgeForEF(getScoreEfDouble(), personne.gender);
	}

	public String getAgeEM() {
		if (!itemsM) {
			return null;
		}
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
