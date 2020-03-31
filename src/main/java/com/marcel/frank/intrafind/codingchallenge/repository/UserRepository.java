package com.marcel.frank.intrafind.codingchallenge.repository;

import java.util.Optional;

import com.marcel.frank.intrafind.codingchallenge.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<User, String>
{
	@Override
	Optional<User> findById(String key);
}
