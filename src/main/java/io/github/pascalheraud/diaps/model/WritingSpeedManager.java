package io.github.pascalheraud.diaps.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import io.github.pascalheraud.diaps.db.Personne.ClassRoom;
import io.github.pascalheraud.diaps.db.Personne.Gender;

@Component
public class WritingSpeedManager {

	private Map<ClassRoom, StandardWritingSpeed> standardSpeeds;

	@PostConstruct
	public void init() {
		standardSpeeds = new HashMap<>();
		standardSpeeds.put(ClassRoom.CP, new StandardWritingSpeed(14, 12, null, null));
		standardSpeeds.put(ClassRoom.CE1, new StandardWritingSpeed(32, 22, 44, 47));
		standardSpeeds.put(ClassRoom.CE2, new StandardWritingSpeed(51, 45, 68, 71));
		standardSpeeds.put(ClassRoom.CM1, new StandardWritingSpeed(52, 60, 89, 99));
		standardSpeeds.put(ClassRoom.CM2, new StandardWritingSpeed(75, 83, 107, 116));
		standardSpeeds.put(ClassRoom.SIXIEME, new StandardWritingSpeed(88, 95, 128, 139));
		standardSpeeds.put(ClassRoom.CINQUIEME, new StandardWritingSpeed(100, 117, 147, 155));
		standardSpeeds.put(ClassRoom.QUATRIEME, new StandardWritingSpeed(117, 121, 157, 165));
		standardSpeeds.put(ClassRoom.TROISIEME, new StandardWritingSpeed(117, 130, 157, 172));
	}

	public StandardWritingSpeed getClassRoomWritingSpeed(ClassRoom classRoom) {
		return standardSpeeds.get(classRoom);
	}

	public Integer getClassRoomWritingSpeedNormal(ClassRoom classRoom, Gender gender) {
		StandardWritingSpeed speed = getClassRoomWritingSpeed(classRoom);
		return gender == Gender.FEMALE ? speed.girlNormal : speed.boyNormal;
	}

	public Integer getClassRoomWritingSpeedMaximal(ClassRoom classRoom, Gender gender) {
		StandardWritingSpeed speed = getClassRoomWritingSpeed(classRoom);
		return gender == Gender.FEMALE ? speed.girlMaximal : speed.boyMaximal;
	}
}
