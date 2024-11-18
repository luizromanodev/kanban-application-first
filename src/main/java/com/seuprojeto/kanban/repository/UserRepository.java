package com.seuprojeto.kanban.repository;

import com.seuprojeto.kanban.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String>{
    UserDetails findByLogin(String login);
}
