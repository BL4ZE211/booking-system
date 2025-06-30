package com.nitheesh.user_service.repository;

import com.nitheesh.user_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String > {

}
