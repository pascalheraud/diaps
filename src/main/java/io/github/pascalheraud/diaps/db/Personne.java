package io.github.pascalheraud.diaps.db;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import io.github.pascalheraud.diaps.apis.APIs;

@Table("diaps_personne")
public class Personne {

	public enum Handed {
		LEFT, RIGHT, UNKNOWN
	}

	public enum ClassRoom {
		CP, CE1, CE2, CM1, CM2, SIXIEME, CINQUIEME, QUATRIEME, TROISIEME
	}

	public enum Gender {
		MALE, FEMALE
	}

	@Id
	@NotNull(groups = { APIs.Update.class, APIs.Reference.class })
	public Long id;

	@NotNull(groups = { APIs.Add.class, APIs.Update.class })
	public Date dateOfReport;

	@NotNull(groups = { APIs.Add.class, APIs.Update.class })
	@Size(max = 100)
	public String firstName;

	@NotNull(groups = { APIs.Add.class, APIs.Update.class })
	@Size(max = 100)
	public String lastName;

	@NotNull(groups = { APIs.Add.class, APIs.Update.class })
	public Date dateOfBirth;

	@NotNull(groups = { APIs.Add.class, APIs.Update.class })
	public Gender gender;

	@NotNull(groups = { APIs.Add.class, APIs.Update.class })
	public Handed handed;

	public String handedInfo;

	@NotNull(groups = { APIs.Add.class, APIs.Update.class })
	public ClassRoom classRoom;
}
