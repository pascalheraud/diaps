package io.github.pascalheraud.diaps.db;

import org.springframework.data.repository.CrudRepository;

public interface BilanDAO extends CrudRepository<Bilan, Long> {
	Bilan getByPersonneId(Long personneId);
}
