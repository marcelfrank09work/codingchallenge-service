package com.marcel.frank.intrafind.codingchallenge.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.marcel.frank.intrafind.codingchallenge.model.User;
import com.marcel.frank.intrafind.codingchallenge.service.UserService;
import org.elasticsearch.action.update.UpdateResponse;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController
{

	private final UserService userService;

	public UserController(UserService userService)
	{
		this.userService = userService;
	}

	@CrossOrigin
	@GetMapping
	public ResponseEntity<Collection<User>> findAll()
	{
		Iterable<User> users = userService.findAll();
		List<User> result = new ArrayList<>();
		users.forEach(result::add);
		return ResponseEntity.ok(result);
	}

	@CrossOrigin
	@GetMapping("/{key}")
	public ResponseEntity<User> show(@PathVariable String key)
	{
		Optional<User> user = userService.get(key);
		return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@CrossOrigin
	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user)
	{
		userService.add(user);
		return ResponseEntity.ok(user);
	}

	@CrossOrigin
	@PutMapping("/{key}")
	public ResponseEntity<UpdateResponse> update(@PathVariable String key, @RequestBody User user)
	{
		return ResponseEntity.ok(userService.update(key, user));
	}

	@CrossOrigin
	@DeleteMapping("/{key}")
	public ResponseEntity<Void> delete(@PathVariable String key)
	{
		userService.delete(key);
		return ResponseEntity.noContent().build();
	}

	@CrossOrigin
	@GetMapping("/clear")
	public ResponseEntity<Void> clear()
	{
		userService.clear();
		return ResponseEntity.noContent().build();
	}
}
