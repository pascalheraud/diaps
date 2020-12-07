package io.github.pascalheraud.diaps.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("diaps_bilan_item")
public class BilanItem {
	@Id
	public Long id;

	public Long bilanId;

	public Long itemId;

	public float note;

	public String description;
}
