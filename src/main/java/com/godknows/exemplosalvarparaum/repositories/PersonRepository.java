package com.godknows.exemplosalvarparaum.repositories;

import com.godknows.exemplosalvarparaum.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
