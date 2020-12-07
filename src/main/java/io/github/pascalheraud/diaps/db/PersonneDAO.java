package io.github.pascalheraud.diaps.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PersonneDAO extends CrudRepository<Personne, Long> {
	List<Personne> findAllByOrderById();
}
