package io.github.pascalheraud.diaps.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("diaps_model")
public class ReportModel {

	@Id
	public Long id;

	public byte[] data;
}
