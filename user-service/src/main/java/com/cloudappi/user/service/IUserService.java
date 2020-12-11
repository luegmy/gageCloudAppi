package com.cloudappi.user.service;

import java.util.List;

import com.cloudappi.user.model.User;

public interface IUserService {

	List<User> getUsers();

	User createUser(User user);

	User getUserById(int id);

	void deleteUser(int id);

}
