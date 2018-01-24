/**
 * GenericServiceImpl.java
 * Created by NhutNguyen
 * Date 26/11/2017	17:00 PM
 */

package com.api.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.model.Task;
import com.api.model.User;
import com.api.repository.TaskRepository;
import com.api.repository.UserRepository;

public class GenericServiceImpl implements GenericService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public List<User> findOneByUsernameAndPassword(String username, String password) {
		return userRepository.findOneByUsernameAndPassword(username, password);
	}

	@Override
	public User findOneByUsername(String username) {
		return userRepository.findOneByUsername(username);
	}

	@Override
	public List<Task> findAllTaskByUserId(long userId) {
		return taskRepository.findAllTaskByUserId(userId);
	}

	@Override
	public List<Task> findAllTaskByUserIdAndStatus(long userId, String status) {
		return taskRepository.findAllTaskByUserIdAndStatus(userId, status);
	}
}
