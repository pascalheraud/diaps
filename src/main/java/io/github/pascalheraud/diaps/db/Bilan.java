package io.github.pascalheraud.diaps.db;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import io.github.pascalheraud.diaps.apis.APIs;
import jsweet.lang.Optional;

@Table("diaps_bilan")
public class Bilan {
	@Id
	@Optional
	public Long id;

	@NotNull(groups = APIs.Update.class)
	@Optional
	public Long personneId;

	@Optional
	public Date occurenceDate;

	@NotNull(groups = APIs.Update.class)
	@Optional
	public Integer writingSpeedNormal;

	@NotNull(groups = APIs.Update.class)
	@Optional
	public Integer writingSpeedMax;
}
