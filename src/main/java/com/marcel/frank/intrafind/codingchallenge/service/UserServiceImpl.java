package com.marcel.frank.intrafind.codingchallenge.service;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import com.marcel.frank.intrafind.codingchallenge.model.User;
import com.marcel.frank.intrafind.codingchallenge.repository.UserRepository;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQueryBuilder;
import org.springframework.stereotype.Service;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@Service
public class UserServiceImpl implements UserService
{
	private final ElasticsearchOperations elasticsearchTemplate;
	private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	private UserRepository userRepository;

	public UserServiceImpl(ElasticsearchOperations elasticsearchTemplate)
	{
		this.elasticsearchTemplate = elasticsearchTemplate;
	}

	@Override
	public Iterable<User> findAll()
	{
		return userRepository.findAll();
	}

	@Override
	public Optional<User> get(String key)
	{
		return userRepository.findById(key);
	}

	@Override
	public User add(User user)
	{
		return userRepository.save(user);
	}

	@Override
	public UpdateResponse update(String key, User updatedUser)
	{
		UpdateResponse result = new UpdateResponse();
		try
		{
			UpdateRequest updateRequest = new UpdateRequest().doc("UpdatedDateTime", new Date(), "CreatedDateTime", new Date(), "CreatedBy", "admin",
					"UpdatedBy", "admin");
			updateRequest.doc(jsonBuilder().startObject().
					field("key", updatedUser.key).
					field("firstName", updatedUser.firstName).
					field("lastName", updatedUser.lastName).
					field("email", updatedUser.email).
					endObject());
			UpdateQuery updateQuery = new UpdateQueryBuilder().withId(key).withClass(User.class).build();
			updateQuery.setUpdateRequest(updateRequest);
			result = elasticsearchTemplate.update(updateQuery);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void clear()
	{
		userRepository.deleteAll();
		userRepository.refresh();
	}

	@Override
	public void delete(String key)
	{
		Optional<User> userOptional = userRepository.findById(key);
		userOptional.ifPresent(user -> userRepository.delete(user));
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}
}
