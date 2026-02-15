package com.example_odev_1.odev_1.controller.ControllerImpl;

import com.example_odev_1.odev_1.Service.IActorService;
import com.example_odev_1.odev_1.Service.IMovieService;
import com.example_odev_1.odev_1.entity.Actor;
import com.example_odev_1.odev_1.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller()
@RequestMapping("/actors")
public class ActorController {
    @Autowired
    IActorService actorService;


    @GetMapping("/detail/{actorid}")
    public String getActorDetail(@PathVariable(name="actorid") Integer actorId, @RequestParam(value="movieId")
    Long movieId ,Model model){

        Actor actor = actorService.getActorDetail(actorId);


        model.addAttribute("actor", actor);
        model.addAttribute("movieId", movieId);




        return "actors/detail";
    }



}
