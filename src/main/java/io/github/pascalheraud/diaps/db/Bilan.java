package io.github.pascalheraud.diaps.db;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("diaps_bilan")
public class Bilan {
	@Id
	public Long id;
	public Long personneId;
	public Date occurenceDate;
}
