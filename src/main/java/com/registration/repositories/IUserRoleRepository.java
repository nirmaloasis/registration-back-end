package com.registration.repositories;

import com.registration.entities.User;
import com.registration.entities.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface IUserRoleRepository extends CrudRepository<UserRole,Integer> {
    public UserRole save(UserRole userRole);
}
