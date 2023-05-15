package com.segsatworks.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.segsatworks.crm.model.Actor;
import com.segsatworks.crm.model.Film;
import com.segsatworks.crm.model.Language;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    @Query("SELECT f FROM Film f WHERE f.title = :title")
    public List<Film> findByTitle(@Param("title") String title);

    @Query("SELECT f.language FROM Film f WHERE f.id = :id")
    public Language findLanguageByFilmId(@Param("id") Long id);

    @Query("SELECT fa.actor FROM FilmActor fa WHERE fa.film.id = :id")
    public List<Actor> findActorsByFilmId(@Param("id") Long id);

    @Query("SELECT f FROM Film f JOIN f.filmCategories fc JOIN fc.category c WHERE c.name = :name")
    public List<Film> findByCategoryName(@Param("name") String name);
}
