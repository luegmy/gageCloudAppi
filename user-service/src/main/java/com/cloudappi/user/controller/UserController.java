package com.cloudappi.user.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cloudappi.user.exception.BadRequestException;
import com.cloudappi.user.exception.MethodNotAllowedException;
import com.cloudappi.user.exception.NotFoundException;
import com.cloudappi.user.model.User;
import com.cloudappi.user.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "user-service", description = "CRUD of users", consumes = "application/json",produces = "application/json")
public class UserController {

	@Autowired
	private IUserService userService;

	// response 200
	@ApiOperation(notes = "Get all users", value = "")
	@GetMapping("/getusers")
	ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.ok(userService.getUsers());
	}

	// response 201 405
	@ApiResponses(value = {@ApiResponse(code = 405, message = "Invalid input")})
	@PostMapping("/createUsers")
	ResponseEntity<User> createUser(@RequestBody User user) {

		if (user.getName().isEmpty()||user.getEmail().isEmpty()) {
			throw new MethodNotAllowedException("Invalid input");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
	}

	// response 200 400 404
	@ApiOperation(notes = "Get one user", value = "")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid user id"),
			@ApiResponse(code = 404, message = "User not found")})
	@GetMapping("/getUsersById/{userId}")
	ResponseEntity<User> getUserById(@PathVariable("userId") int userId) {
		if (userId != (int) userId) {
			throw new BadRequestException("Invalid user id");
		} 
		User optionalUser = userService.getUserById(userId);
		if (optionalUser ==null) {
			throw new NotFoundException("User not found");
		}
		return ResponseEntity.ok(optionalUser);
	}

	// response 200 400 404
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid user id"),
			@ApiResponse(code = 404, message = "User not found")})
	@PutMapping("/updateUsersById/{userId}")
	ResponseEntity<User> updateUserById(@PathVariable("userId") int userId, @RequestBody User user) {

		if (userId != (int) userId) {
			throw new BadRequestException("Invalid user id");
		}
		User optionalUser = userService.getUserById(userId);
		if (optionalUser ==null) {
			throw new NotFoundException("User not found");
		}
		user.setId(userId);
		return ResponseEntity.ok(userService.updateUser(user));

	}

	// response 200 400 404
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid user id"),
							@ApiResponse(code = 404, message = "User not found")})
	@DeleteMapping("/deleteUsersById/{userId}")
	ResponseEntity<?> deleteUserById(@PathVariable("userId") int userId) {
		
		if (userId != (int) userId) {
			throw new BadRequestException("Invalid user id");
		} 
		User optionalUser = userService.getUserById(userId);
		if (optionalUser == null) {
			throw new NotFoundException("User not found");
		}
		userService.deleteUser(userId);
		return ResponseEntity.ok().build();
	}

}
