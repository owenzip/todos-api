/**
 * UserRepository.java
 * Created by NhutNguyen
 * Date 21/11/2017	16:00 PM
 */

package com.api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findOneByUsernameAndPassword(String username, String password);

	User findOneByUsername(String username);
}
