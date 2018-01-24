/**
 * TaskRepository.java
 * Created by NhutNguyen
 * Date 27/11/2017	11:00 AM
 */
package com.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> findAllTaskByUserId(long userId);

	List<Task> findAllTaskByUserIdAndStatus(long userId, String status);
}
