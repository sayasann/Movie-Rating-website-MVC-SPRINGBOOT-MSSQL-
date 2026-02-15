package com.example_odev_1.odev_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example_odev_1.odev_1.entity.User;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("SELECT u FROM User u WHERE u.userName = :userName")
	Optional<User> findByUserName(@Param("userName") String userName);
	boolean existsByUserName(String userName);

}
