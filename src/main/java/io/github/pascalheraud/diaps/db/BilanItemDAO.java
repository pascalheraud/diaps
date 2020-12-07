package io.github.pascalheraud.diaps.db;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import io.github.pascalheraud.diaps.db.Item.Category;

public interface BilanItemDAO extends CrudRepository<BilanItem, Long> {
	@Query("select count(*) from diaps_bilan_item bi join diaps_item i on bi.itemid = i.id"
			+ " where bi.bilanId = :bilanId and i.category  = :itemCategory::diaps_item_category")
	int countByBilanAndCategory(Long bilanId, Category itemCategory);

	@Query("select count(*) from diaps_bilan_item bi join diaps_item i on bi.itemid = i.id"
			+ " where bi.bilanId = :bilanId and i.category  = :itemCategory::diaps_item_category and i.dysnumber is null")
	int countByBilanIdAndCategoryAndDysNumberNull(Long bilanId, Category itemCategory);
}
