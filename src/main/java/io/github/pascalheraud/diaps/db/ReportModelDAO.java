package io.github.pascalheraud.diaps.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReportModelDAO extends CrudRepository<ReportModel, Long> {
	List<ReportModel> findAllByOrderById();
}
