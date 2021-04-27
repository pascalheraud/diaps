package io.github.pascalheraud.diaps.model;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import io.github.pascalheraud.diaps.db.Personne.ClassRoom;
import io.github.pascalheraud.diaps.db.Personne.Gender;

@Component
public class EFMStandardManager {
	private List<Pair<ClassRoom, Double>> standardEFPerClassRoomBoy;
	private List<Pair<ClassRoom, Double>> standardEFPerClassRoomGirl;
	private List<Pair<ClassRoom, Double>> standardEMPerClassRoomBoy;
	private List<Pair<ClassRoom, Double>> standardEMPerClassRoomGirl;
	private List<Pair<Double, Double>> standardEFPerAgeBoy;
	private List<Pair<Double, Double>> standardEFPerAgeGirl;
	private List<Pair<Double, Double>> standardEMPerAgeBoy;
	private List<Pair<Double, Double>> standardEMPerAgeGirl;

	@PostConstruct
	public void init() {
		standardEFPerClassRoomBoy = new ArrayList<>();
		standardEFPerClassRoomBoy.add(new ImmutablePair<>(ClassRoom.CP, 23.0));
		standardEFPerClassRoomBoy.add(new ImmutablePair<>(ClassRoom.CE1, 16.5));
		standardEFPerClassRoomBoy.add(new ImmutablePair<>(ClassRoom.CM1, 13.5));
		standardEFPerClassRoomBoy.add(new ImmutablePair<>(ClassRoom.CM2, 12.5));
		standardEFPerClassRoomBoy.add(new ImmutablePair<>(ClassRoom.CM2, 9.5));

		standardEFPerClassRoomGirl = new ArrayList<>();
		standardEFPerClassRoomGirl.add(new ImmutablePair<>(ClassRoom.CP, 20.0));
		standardEFPerClassRoomGirl.add(new ImmutablePair<>(ClassRoom.CE1, 15.5));
		standardEFPerClassRoomGirl.add(new ImmutablePair<>(ClassRoom.CM1, 10.5));
		standardEFPerClassRoomGirl.add(new ImmutablePair<>(ClassRoom.CM2, 11.0));
		standardEFPerClassRoomGirl.add(new ImmutablePair<>(ClassRoom.CM2, 8.5));

		standardEMPerClassRoomBoy = new ArrayList<>();
		standardEMPerClassRoomBoy.add(new ImmutablePair<>(ClassRoom.CP, 16.0));
		standardEMPerClassRoomBoy.add(new ImmutablePair<>(ClassRoom.CE1, 10.5));
		standardEMPerClassRoomBoy.add(new ImmutablePair<>(ClassRoom.CM1, 10.5));
		standardEMPerClassRoomBoy.add(new ImmutablePair<>(ClassRoom.CM2, 7.0));
		standardEMPerClassRoomBoy.add(new ImmutablePair<>(ClassRoom.CM2, 5.0));

		standardEMPerClassRoomGirl = new ArrayList<>();
		standardEMPerClassRoomGirl.add(new ImmutablePair<>(ClassRoom.CP, 15.0));
		standardEMPerClassRoomGirl.add(new ImmutablePair<>(ClassRoom.CE1, 10.0));
		standardEMPerClassRoomGirl.add(new ImmutablePair<>(ClassRoom.CM1, 18.0));
		standardEMPerClassRoomGirl.add(new ImmutablePair<>(ClassRoom.CM2, 6.0));
		standardEMPerClassRoomGirl.add(new ImmutablePair<>(ClassRoom.CM2, 3.5));

		standardEFPerAgeBoy = new ArrayList<>();
		standardEFPerAgeBoy.add(new ImmutablePair<>(6.0, 23.5));
		standardEFPerAgeBoy.add(new ImmutablePair<>(6.5, 20.0));
		standardEFPerAgeBoy.add(new ImmutablePair<>(7.0, 17.5));
		standardEFPerAgeBoy.add(new ImmutablePair<>(7.5, 15.5));
		standardEFPerAgeBoy.add(new ImmutablePair<>(8.0, 13.5));
		standardEFPerAgeBoy.add(new ImmutablePair<>(8.5, 13.0));
		standardEFPerAgeBoy.add(new ImmutablePair<>(9.0, 12.5));
		standardEFPerAgeBoy.add(new ImmutablePair<>(9.5, 12.5));
		standardEFPerAgeBoy.add(new ImmutablePair<>(10.0, 12.0));
		standardEFPerAgeBoy.add(new ImmutablePair<>(10.5, 10.0));
		standardEFPerAgeBoy.add(new ImmutablePair<>(11.0, 8.0));

		standardEMPerAgeBoy = new ArrayList<>();
		standardEMPerAgeBoy.add(new ImmutablePair<>(6.0, 15.5));
		standardEMPerAgeBoy.add(new ImmutablePair<>(6.5, 14.0));
		standardEMPerAgeBoy.add(new ImmutablePair<>(7.0, 12.5));
		standardEMPerAgeBoy.add(new ImmutablePair<>(7.5, 11.5));
		standardEMPerAgeBoy.add(new ImmutablePair<>(8.0, 10.0));
		standardEMPerAgeBoy.add(new ImmutablePair<>(8.5, 8.5));
		standardEMPerAgeBoy.add(new ImmutablePair<>(9.0, 7.5));
		standardEMPerAgeBoy.add(new ImmutablePair<>(9.5, 7.5));
		standardEMPerAgeBoy.add(new ImmutablePair<>(10.0, 7.0));
		standardEMPerAgeBoy.add(new ImmutablePair<>(10.5, 5.5));
		standardEMPerAgeBoy.add(new ImmutablePair<>(11.0, 4.5));

		standardEFPerAgeGirl = new ArrayList<>();
		standardEFPerAgeGirl.add(new ImmutablePair<>(6.0, 22.5));
		standardEFPerAgeGirl.add(new ImmutablePair<>(6.6, 18.5));
		standardEFPerAgeGirl.add(new ImmutablePair<>(7.0, 16.0));
		standardEFPerAgeGirl.add(new ImmutablePair<>(7.6, 13.5));
		standardEFPerAgeGirl.add(new ImmutablePair<>(8.0, 12.0));
		standardEFPerAgeGirl.add(new ImmutablePair<>(8.6, 11.0));
		standardEFPerAgeGirl.add(new ImmutablePair<>(9.0, 10.5));
		standardEFPerAgeGirl.add(new ImmutablePair<>(9.6, 10.0));
		standardEFPerAgeGirl.add(new ImmutablePair<>(10.0, 10.0));
		standardEFPerAgeGirl.add(new ImmutablePair<>(10.6, 8.5));
		standardEFPerAgeGirl.add(new ImmutablePair<>(11.0, 7.5));

		standardEMPerAgeGirl = new ArrayList<>();
		standardEMPerAgeGirl.add(new ImmutablePair<>(6.0, 17.5));
		standardEMPerAgeGirl.add(new ImmutablePair<>(6.6, 14.5));
		standardEMPerAgeGirl.add(new ImmutablePair<>(7.0, 12.0));
		standardEMPerAgeGirl.add(new ImmutablePair<>(7.6, 10.5));
		standardEMPerAgeGirl.add(new ImmutablePair<>(8.0, 9.0));
		standardEMPerAgeGirl.add(new ImmutablePair<>(8.6, 8.0));
		standardEMPerAgeGirl.add(new ImmutablePair<>(9.0, 7.0));
		standardEMPerAgeGirl.add(new ImmutablePair<>(9.6, 6.5));
		standardEMPerAgeGirl.add(new ImmutablePair<>(10.0, 6.0));
		standardEMPerAgeGirl.add(new ImmutablePair<>(10.6, 4.5));
		standardEMPerAgeGirl.add(new ImmutablePair<>(11.0, 3.0));
	}

	public String getClassRoomForEF(Double ef, Gender gender) {
		return getKeyForNote(ef, gender == Gender.MALE ? standardEFPerClassRoomBoy : standardEFPerClassRoomGirl);
	}

	public String getClassRoomForEM(Double em, Gender gender) {
		return getKeyForNote(em, gender == Gender.MALE ? standardEMPerClassRoomBoy : standardEMPerClassRoomGirl);
	}

	public String getAgeForEF(Double ef, Gender gender) {
		return getKeyForNote(ef, gender == Gender.MALE ? standardEFPerAgeBoy : standardEFPerAgeGirl);
	}

	public String getAgeForEM(Double em, Gender gender) {
		return getKeyForNote(em, gender == Gender.MALE ? standardEMPerAgeBoy : standardEMPerAgeGirl);
	}

	private <KEY> String getKeyForNote(Double value, List<Pair<KEY, Double>> values) {
		for (int i = values.size() - 1; i >= 0; i--) {
			Pair<KEY, Double> pair = values.get(i);
			if (value < pair.getValue()) {
				return String.valueOf(pair.getKey());
			}
		}
		return "Avant " + values.get(0).getKey();
	}
}
