package com.oop.rbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import jakarta.persistence.Table;
import java.util.UUID;

@Table
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}