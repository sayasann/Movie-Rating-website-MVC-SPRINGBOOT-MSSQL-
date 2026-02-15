package com.example_odev_1.odev_1.repository;


import com.example_odev_1.odev_1.entity.Review;
import com.example_odev_1.odev_1.entity.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
	@Query("SELECT r FROM Review r WHERE r.user = :user")
	List<Review> findByUser(@Param("user") User user);



}
