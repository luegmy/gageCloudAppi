package com.cloudappi.user.service;

import java.util.List;
import java.util.Optional;

import com.cloudappi.user.model.User;

public interface IUserService {

	List<User> getUsers();

	User createUser(User user);

	User getUserById(int id);
	
	User updateUser(User user);

	void deleteUser(int id);

}
