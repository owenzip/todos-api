/** 
 * GenericService.java
 * Created by NhutNguyen
 * Date 18/11/2017	15:30 PM
 */
package com.api.service;

import java.util.List;

import com.api.model.Task;
import com.api.model.User;

public interface GenericService {

	List<User> findOneByUsernameAndPassword(String username, String password);

	User findOneByUsername(String username);

	List<Task> findAllTaskByUserId(long userId);

	List<Task> findAllTaskByUserIdAndStatus(long userId, String status);
}
