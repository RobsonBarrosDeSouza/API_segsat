package com.segsatworks.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.segsatworks.crm.model.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

}
