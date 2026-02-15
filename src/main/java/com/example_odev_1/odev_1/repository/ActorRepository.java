package com.example_odev_1.odev_1.repository;

import com.example_odev_1.odev_1.entity.Actor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends CrudRepository<Actor, Integer> {


}
