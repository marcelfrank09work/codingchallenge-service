package com.marcel.frank.intrafind.codingchallenge.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "service", type = "users")
public class User
{
	@Id
	public String key;

	@JsonProperty("firstName")
	public String firstName;

	@JsonProperty("lastName")
	public String lastName;

	@JsonProperty("email")
	public String email;

	public User()
	{
	}

	public User(String firstName, String lastName, String email)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	@Override
	public String toString()
	{
		return "User{" + "key='" + key + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + '}';
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String key)
	{
		this.key = key;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
}
