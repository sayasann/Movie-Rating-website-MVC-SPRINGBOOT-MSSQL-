package com.example_odev_1.odev_1.Service.ServiceImpl;

import com.example_odev_1.odev_1.Service.IActorService;
import com.example_odev_1.odev_1.entity.Actor;
import com.example_odev_1.odev_1.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ActorServiceImpl implements IActorService {
    @Autowired
    ActorRepository actorRepository;

    @Override
    public Actor getActorDetail(Integer id) {

        Optional<Actor> optional =actorRepository.findById(id);
        if(optional.isEmpty()){
            return null;
        }
        return optional.get();

    }
}
