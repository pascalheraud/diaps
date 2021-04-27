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
public class WritingSpeedManager {

	private List<Pair<ClassRoom, StandardWritingSpeed>> standardSpeedsPerClassRoom;
	private List<Pair<String, StandardWritingSpeed>> standardSpeedsPerAge;

	@PostConstruct
	public void init() {
		standardSpeedsPerClassRoom = new ArrayList<>();
		standardSpeedsPerClassRoom.add(new ImmutablePair<>(ClassRoom.CP, new StandardWritingSpeed(14, 12, null, null)));
		standardSpeedsPerClassRoom.add(new ImmutablePair<>(ClassRoom.CE1, new StandardWritingSpeed(32, 22, 44, 47)));
		standardSpeedsPerClassRoom.add(new ImmutablePair<>(ClassRoom.CE2, new StandardWritingSpeed(51, 45, 68, 71)));
		standardSpeedsPerClassRoom.add(new ImmutablePair<>(ClassRoom.CM1, new StandardWritingSpeed(52, 60, 89, 99)));
		standardSpeedsPerClassRoom.add(new ImmutablePair<>(ClassRoom.CM2, new StandardWritingSpeed(75, 83, 107, 116)));
		standardSpeedsPerClassRoom.add(new ImmutablePair<>(ClassRoom.SIXIEME, new StandardWritingSpeed(88, 95, 128, 139)));
		standardSpeedsPerClassRoom.add(new ImmutablePair<>(ClassRoom.CINQUIEME, new StandardWritingSpeed(100, 117, 147, 155)));
		standardSpeedsPerClassRoom.add(new ImmutablePair<>(ClassRoom.QUATRIEME, new StandardWritingSpeed(117, 121, 157, 165)));
		standardSpeedsPerClassRoom.add(new ImmutablePair<>(ClassRoom.TROISIEME, new StandardWritingSpeed(130, 130, 172, 172)));

		standardSpeedsPerAge = new ArrayList<>();
		standardSpeedsPerAge.add(new ImmutablePair<>("6-6,5", new StandardWritingSpeed(14, 12, null, null)));
		standardSpeedsPerAge.add(new ImmutablePair<>("6,5-7,5", new StandardWritingSpeed(21, 28, 62, 58)));
		standardSpeedsPerAge.add(new ImmutablePair<>("7,5-8,5", new StandardWritingSpeed(44, 40, 62, 58)));
		standardSpeedsPerAge.add(new ImmutablePair<>("8.5-9,5", new StandardWritingSpeed(52, 55, 80, 89)));
		standardSpeedsPerAge.add(new ImmutablePair<>("9.5-10,5", new StandardWritingSpeed(59, 61, 93, 104)));
		standardSpeedsPerAge.add(new ImmutablePair<>("10.5-11,5", new StandardWritingSpeed(79, 81, 112, 117)));
	}

	/**
	 * the standard ClassRoom for a speed
	 *
	 * @param speed
	 * @param gender
	 * @param normalOrNot
	 * @return
	 */
	public String getClassRoomWritingSpeed(Integer speed, Gender gender, boolean normalOrNot) {
		ClassRoom classRoom = getWritingSpeedKey(speed, gender, standardSpeedsPerClassRoom, normalOrNot);
		if (classRoom == null) {
			return "Avant " + standardSpeedsPerClassRoom.get(0).getKey();
		}
		return classRoom.toString();
	}

	/**
	 * The standard age for a speed
	 *
	 * @param speed
	 * @param gender
	 * @param normalOrNot
	 * @return
	 */
	public String getAgeWritingSpeed(Integer speed, Gender gender, boolean normalOrNot) {
		String age = getWritingSpeedKey(speed, gender, standardSpeedsPerAge, normalOrNot);
		if (age == null) {
			return "Avant " + standardSpeedsPerAge.get(0).getKey();
		}
		return age;
	}

	public Integer getStandardSpeedForClassRoom(Gender gender, ClassRoom classRoom, boolean normalOrNot) {
		for (Pair<ClassRoom, StandardWritingSpeed> pair : standardSpeedsPerClassRoom) {
			if (pair.getLeft().equals(classRoom)) {
				if (normalOrNot) {
					return getSpeedNormal(gender, pair.getRight());
				} else {
					return getSpeedMax(gender, pair.getRight());
				}
			}
		}
		// Must not return null becase here because all entries exist. But the speed may be null for maximal / CP
		return null;
	}

	public String getAgeWritingSpeedMaximal(Integer speed, Gender gender) {
		return getWritingSpeedKey(speed, gender, standardSpeedsPerAge, false);
	}

	/**
	 *
	 * @param <T>
	 * @param speed
	 * @param gender
	 * @param speeds
	 * @param normalOrNot if true takes the normal speed, if false take the maxima speed
	 * @return
	 */
	private <T> T getWritingSpeedKey(Integer speed, Gender gender, List<Pair<T, StandardWritingSpeed>> speeds, boolean normalOrNot) {
		for (int i = speeds.size() - 1; i >= 0; i--) {
			Pair<T, StandardWritingSpeed> speedForAgeMax = speeds.get(i);
			Integer theSpeed = normalOrNot ? getSpeedNormal(gender, speedForAgeMax.getRight()) : getSpeedMax(gender, speedForAgeMax.getRight());
			if (theSpeed != null && speed >= theSpeed) {
				return speedForAgeMax.getLeft();
			}
		}
		return null;
	}

	private Integer getSpeedMax(Gender gender, StandardWritingSpeed speed) {
		return gender == Gender.FEMALE ? speed.girlMaximal : speed.boyMaximal;
	}

	private Integer getSpeedNormal(Gender gender, StandardWritingSpeed speed) {
		return gender == Gender.FEMALE ? speed.girlNormal : speed.boyNormal;
	}
}
