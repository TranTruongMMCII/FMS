package fms.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fms.api.entity.*;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

}

