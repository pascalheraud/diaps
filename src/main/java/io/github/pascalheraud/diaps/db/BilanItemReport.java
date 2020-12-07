package io.github.pascalheraud.diaps.db;

import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Embedded.OnEmpty;

import io.github.pascalheraud.diaps.db.Item.Category;
import io.github.pascalheraud.diaps.db.Item.DysGroup;

public class BilanItemReport extends BilanItem {
	@Embedded(prefix = "refItem", onEmpty = OnEmpty.USE_NULL)
	public Item item;

	public Category getItemCategory() {
		return item.category;
	}

	public DysGroup getItemDysGroup() {
		return item.dysGroup;
	}

	public String getDescription() {
		return description;
	}

	public String getItemName() {
		if (item.category == Category.OTHER) {
			return "D" + item.dysNumber;
		} else if (item.category == Category.EF) {
			return "D" + item.dysNumber + "(F" + item.number + ")";
		} else {
			return "D" + item.dysNumber + "(M" + item.number + ")";
		}
	}
}
