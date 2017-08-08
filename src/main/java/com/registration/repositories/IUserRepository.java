package com.registration.repositories;

import com.registration.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserRepository extends CrudRepository<User,Integer>{
    public User save(User users);
    public List<User> findByUsername(String username);
    public  List<User> findByUsernameAndPassword(String username,String password);

}
