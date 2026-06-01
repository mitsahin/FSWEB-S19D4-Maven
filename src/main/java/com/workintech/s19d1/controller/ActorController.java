package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.util.HollywoodValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actor")
@Slf4j
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public List<Actor> findAll() {
        return actorService.findAll();
    }

    @GetMapping("/{id}")
    public Actor findById(@PathVariable Long id) {
        try {
            return actorService.findById(id);
        } catch (Exception ex) {
            log.error("Error finding actor with id {}: {}", id, ex.getMessage());
            throw ex;
        }
    }

    @PostMapping
    public Actor save(@RequestBody ActorRequest actorRequest) {
        try {
            Actor actor = actorRequest.getActor();
            List<Movie> movies = actorRequest.getMovies();
            if (movies != null) {
                for (Movie movie : movies) {
                    actor.addMovie(movie);
                }
            }
            HollywoodValidation.validateActor(actor);
            return actorService.save(actor);
        } catch (Exception ex) {
            log.error("Error saving actor: {}", ex.getMessage());
            throw ex;
        }
    }

    @PutMapping("/{id}")
    public Actor update(@PathVariable Long id, @RequestBody Actor actor) {
        try {
            actorService.findById(id);
            actor.setId(id);
            HollywoodValidation.validateActor(actor);
            return actorService.save(actor);
        } catch (Exception ex) {
            log.error("Error updating actor with id {}: {}", id, ex.getMessage());
            throw ex;
        }
    }

    @DeleteMapping("/{id}")
    public Actor delete(@PathVariable Long id) {
        try {
            Actor actor = actorService.findById(id);
            actorService.delete(actor);
            return actor;
        } catch (Exception ex) {
            log.error("Error deleting actor with id {}: {}", id, ex.getMessage());
            throw ex;
        }
    }
}
