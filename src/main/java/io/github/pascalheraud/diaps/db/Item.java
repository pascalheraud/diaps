package io.github.pascalheraud.diaps.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("diaps_item")
public class Item {

	public enum Category {
		EM, EF, OTHER
	}

	public enum DysGroup {
		PAGE, MALADRESSE, FORMES_ET_PROPORTIONS
	}

	@Id
	public Long id;
	public Integer number;
	public String label;
	public Integer rate;
	public Category category;
	public DysGroup dysGroup;
	public Integer dysRate;
	public Integer dysNumber;
}
