
package io.github.pascalheraud.diaps;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	// List<User> findEmailByFirstNameLikeIgnoreCaseAndIdGreaterThanEqualOrderByDateOfBirthAsc(@Param("email") String email, @Param("id") Long id);
}
