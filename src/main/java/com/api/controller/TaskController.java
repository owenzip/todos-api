/**
 * TaskController.java
 * Created by NhutNguyen
 * Date 26/11/2017	15:00 PM
 */
package com.api.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.model.Task;
import com.api.repository.TaskRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/* Class com.api.controller for task
 *  1. [POST] Create new Task by UserId
 *  2. [PUT] Update Task by TaskId
 *  3. [PUT] Update Status by TaskId
 *  4. [DELETE] Delete Task by Status of UserId
 * */
@RestController
@Api(value = "Todo App", description = "Operations pertaining to Tasks in Todo App")
public class TaskController {
	
	@Autowired
	private TaskRepository taskRepository;

	/* CREATE NEW TASK BY USER ID */
	@ApiOperation(value = "Create new Task by User ID", response = Task.class)
	@RequestMapping(value = "/tasks", method = RequestMethod.POST)
	public Task createNewTask(@RequestParam("userid") long userId, @RequestParam("task") String task,
			@RequestParam(value = "status", defaultValue = "false") String status,
			@RequestParam(value = "image", defaultValue = "image") String image) {
		Task tasks = new Task();
		tasks.setUserId(userId);
		tasks.setTask(task);
		tasks.setStatus(status);
		tasks.setImage(image);
		taskRepository.save(tasks);
		return tasks;
	}

	/* UPDATE TASK BY TASK ID */
	@ApiOperation(value = "Update Task by Task ID", response = Task.class)
	@RequestMapping(value = "/tasks/{taskid}", method = RequestMethod.PUT)
	public Task updateTaskByTaskId(@PathVariable("taskid") long taskId,
			@RequestParam(value = "task", required = false) String task,
			@RequestParam(value = "status") String status) {
		Task tasks = taskRepository.findOne(taskId);
		tasks.setTaskId(taskId);
		tasks.setTask(task);
		tasks.setStatus(tasks.getStatus());
		taskRepository.save(tasks);
		return tasks;
	}

	/* UPDATE STATUS BY TASK ID */
	@ApiOperation(value = "Update Status by Task ID", response = Task.class)
	@RequestMapping(value = "/tasks/{taskid}/{status}", method = RequestMethod.PUT)
	public String updateStatusByTaskId(@PathVariable("taskid") long taskId,
			@PathVariable(value = "status") String status) {
		Task tasks = taskRepository.findOne(taskId);
		tasks.setTaskId(tasks.getTaskId());
		tasks.setStatus(status);
		taskRepository.save(tasks);
		return "Update successful";
	}

	/* DELETE ALL TASK BY STATUS (COMPLETED) OF USER ID */
	@ApiOperation(value = "Delete all Task Completed by User ID", response = Task.class)
	@RequestMapping(value = "/users/{userid}/tasks", method = RequestMethod.DELETE)
	public String deleteTaskByStatusOfUserId(@PathVariable("userid") long userId,
			@RequestParam(value = "status", defaultValue = "true") String status) {
		List<Task> tasks = taskRepository.findAllTaskByUserIdAndStatus(userId, status);
		taskRepository.delete(tasks);
		return "Delete successful";
	}
}
