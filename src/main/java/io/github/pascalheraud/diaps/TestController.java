package io.github.pascalheraud.diaps;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@Autowired
	UserRepository userRepo;

	@GetMapping(value = "/apis/data", produces = MediaType.TEXT_PLAIN_VALUE)
	public String getData() {
		return "test";
	}

	@GetMapping(value = "/apis/users/get/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getData2() {
		List<User> res = new LinkedList<>();
		User u = new User();
		u.firstName = "a";
		res.add(u);
		return res;
	}
}
