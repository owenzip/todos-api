/**
 * RoleRepository.java
 * Created by NhutNguyen
 * Date 18/12/2017	9:30 AM
 */
package com.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.api.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
