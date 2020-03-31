package com.marcel.frank.intrafind.codingchallenge.service;

import java.util.Optional;

import com.marcel.frank.intrafind.codingchallenge.model.User;
import org.elasticsearch.action.update.UpdateResponse;

public interface UserService
{
	Iterable<User> findAll();

	Optional<User> get(String key);

	User add(User user);

	UpdateResponse update(String key, User user);

	void delete(String key);

	void clear();
}
