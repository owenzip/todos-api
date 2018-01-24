/**
 * UserController.java
 * Created by NhutNguyen
 * Date 21/11/2017	15:00 PM
 */
package com.api.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.model.Task;
import com.api.model.User;
import com.api.repository.TaskRepository;
import com.api.repository.UserRepository;
import com.google.common.hash.Hashing;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/* Class com.api.controller for user
 *  1. [GET] Find UserId by Username 
 *  2. [PUT] Change password by UserId
 *  3. [DELETE] Delete User by UserId
 *  4. [POST] Create new User
 *  5. [GET] Find all Task of UserId
 *  6. [GET] Find all Task by Status of UserId
 *  7. [POST] Check login with OAuth2
 * */
@RestController
@Api(value = "Todo App", description = "Operations pertaining to User in Todo App")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TaskRepository taskRepository;
	
	/* FIND USER BY USERID */
	@RequestMapping(value = "/users/{userid}/profile", method = RequestMethod.GET)
	User getUserByUserId (@PathVariable("userid") long userId){
		return userRepository.findOne(userId);
	}
	
	/* FIND USER ID BY USERNAME */
	@ApiOperation(value = "Find User ID by Username", response = User.class)
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public User findUserIdByUsername(@RequestParam("username") String username) {
		User user = userRepository.findOneByUsername(username);
		return user;
	}

	/* CHANGE PASSWORD BY USER ID */
	@ApiOperation(value = "Update Password by User ID", response = User.class)
	@RequestMapping(value = "users/{userid}", method = RequestMethod.PUT)
	public String updatePasswordByUserId(@PathVariable("userid") long userId,
			@RequestParam("password") String password) {
		User user = userRepository.findOne(userId);
		user.setUsername(user.getUsername());
		user.setFirstname(user.getFirstname());
		user.setLastname(user.getLastname());
		user.setUserId(userId);
		// Hash password with sha256
		user.setPassword(Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString());
		userRepository.save(user);
		return "Successful";
	}

	/* DELETE USER BY USER ID */
	@ApiOperation(value = "Delete User by User ID", response = User.class)
	@RequestMapping(value = "/users/{userid}", method = RequestMethod.DELETE)
	public String deleteUserByUserId(@PathVariable("userid") long userId) {
		userRepository.delete(userId);
		return "Successful";
	}

	/* CREATE NEW USER */
	@ApiOperation(value = "Create new User", response = User.class)
	@RequestMapping(value = "/users/register", method = RequestMethod.POST)
	public String createNewUser(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam(value = "firstname") String firstname, @RequestParam(value = "lastname") String lastname) {
		User user = new User();
		user.setUsername(username);
		// Hash password with sha256
		user.setPassword(Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString());
		user.setFirstname(firstname);
		user.setLastname(lastname);
		userRepository.save(user);
		return "Successful";
	}

	/* FIND ALL TASK OF USER ID */
	@ApiOperation(value = "Find all Task by Username", response = Task.class)
	@RequestMapping(value = "/users/{userid}/tasks", method = RequestMethod.GET)
	public List<Task> findAllTaskByUserId(@PathVariable("userid") long userId) {
		return taskRepository.findAllTaskByUserId(userId);
	}

	/* FIND ALL TASK BY STATUS OF USER ID */
	@ApiOperation(value = "Find all Task by Status of User ID", response = Task.class)
	@RequestMapping(value = "/users/{userid}/tasks/{status}", method = RequestMethod.GET)
	public List<Task> findAllTaskByStatusOfUserId(@PathVariable("userid") long userId,
			@PathVariable("status") String status) {
		return taskRepository.findAllTaskByUserIdAndStatus(userId, status);
	}
}

