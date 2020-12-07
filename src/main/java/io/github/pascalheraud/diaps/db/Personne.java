package io.github.pascalheraud.diaps.db;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import io.github.pascalheraud.diaps.apis.DIAPSApisController.Add;
import io.github.pascalheraud.diaps.apis.DIAPSApisController.Reference;
import io.github.pascalheraud.diaps.apis.DIAPSApisController.Update;

@Table("diaps_personne")
public class Personne {

	public enum Handed {
		LEFT, RIGHT, UNKNOWN
	}

	@Id
	@NotNull(groups = { Update.class, Reference.class })
	public Long id;

	@NotNull(groups = { Add.class, Update.class })
	public Date dateOfReport;

	@NotNull(groups = { Add.class, Update.class })
	@Size(max = 100)
	public String firstName;

	@NotNull(groups = { Add.class, Update.class })
	@Size(max = 100)
	public String lastName;

	@NotNull(groups = { Add.class, Update.class })
	public Date dateOfBirth;

	@NotNull(groups = { Add.class, Update.class })
	public Handed handed;

	public String handedInfo;

	@NotNull(groups = { Add.class, Update.class })
	public String classRoom;
}
