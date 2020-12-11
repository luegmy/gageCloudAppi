package com.cloudappi.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudappi.user.model.User;
import com.cloudappi.user.service.IUserService;

@RestController
@RequestMapping("/users")  //rutas concretas
public class UserController {

	@Autowired
	IUserService userService;
//------------------------- usar formatos sencillos------------------------------------//
	// response 200
	@GetMapping  //("/getUsers")
	List<User> getUsers() {
		return userService.getUsers();
	}

	// response 201 405
	@PostMapping  //("/createUser")
	User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	// response 200 400 404
	@GetMapping("/{userId}")   //("{/getUsersById/userId}")
	User getUserById(@PathVariable("userId") int userId) {
		return userService.getUserById(userId);
	}

	//response 200 400 404
	@PutMapping("/{userId}")   //("{/updateUser/userId}")
	User updateUserById(@PathVariable("userId") int userId, @RequestBody User user) {
		user.setId(userId);
		User userUpdate=userService.createUser(user);
		return userUpdate;
	}
	
	//response 200 400 404
	@DeleteMapping("/{userId}")
	void deleteUserById(@PathVariable("userId")int userId) {
		userService.deleteUser(userId);
	}
}
