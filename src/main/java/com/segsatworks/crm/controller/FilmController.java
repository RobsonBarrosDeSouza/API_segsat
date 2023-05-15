package com.segsatworks.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.segsatworks.crm.model.Actor;
import com.segsatworks.crm.model.Category;
import com.segsatworks.crm.model.Film;
import com.segsatworks.crm.model.Language;
import com.segsatworks.crm.repository.ActorRepository;
import com.segsatworks.crm.repository.CategoryRepository;
import com.segsatworks.crm.repository.FilmRepository;

@RestController
@RequestMapping("/api")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/films")
    public Film createFilm(@RequestBody Film film) {
        return filmRepository.save(film);
    }

    @PostMapping("/actors")
    public Actor createActor(@RequestBody Actor actor) {
        return actorRepository.save(actor);
    }

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/films/{id}")
    public Film updateFilm(@PathVariable Long id, @RequestBody Film film) {
        Film existingFilm = filmRepository.findById(id).orElse(null);
        if (existingFilm != null) {
            existingFilm.setTitle(film.getTitle());
            existingFilm.setLanguage(film.getLanguage());
            return filmRepository.save(existingFilm);
        }
        return null;
    }

    @DeleteMapping("/films/{id}")
    public void deleteFilm(@PathVariable Long id) {
        Film existingFilm = filmRepository.findById(id).orElse(null);
        if (existingFilm != null) {
            filmRepository.delete(existingFilm);
        }
    }

    @GetMapping("/films")
    public List<Film> getAllFilmsByCategory(@RequestParam String categoryName) {
        return filmRepository.findByCategoryName(categoryName);
    }

    @GetMapping("/films/{id}/actors")
    public List<Actor> getActorsByFilmId(@PathVariable Long id) {
        return filmRepository.findActorsByFilmId(id);
    }

    @GetMapping("/films/{id}/language")
    public Language getLanguageByFilmId(@PathVariable Long id) {
        return filmRepository.findLanguageByFilmId(id);
    }
}


