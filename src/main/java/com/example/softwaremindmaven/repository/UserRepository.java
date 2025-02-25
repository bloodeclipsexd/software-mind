package com.example.softwaremindmaven.repository;

import com.example.softwaremindmaven.dao.UserDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<UserDao, UUID> {

}
