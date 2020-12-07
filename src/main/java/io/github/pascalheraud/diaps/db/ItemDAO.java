package io.github.pascalheraud.diaps.db;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import io.github.pascalheraud.diaps.db.Item.Category;

public interface ItemDAO extends CrudRepository<Item, Long> {
	@Query("select * from diaps_item where category = :category::diaps_item_category")
	List<Item> findAllByCategory(Category category);

	List<Item> findAllByDysNumberNotNull();
}
