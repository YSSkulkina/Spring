package ru.skulkina.ProjectOfLibraryWithBootApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skulkina.ProjectOfLibraryWithBootApp.models.Person;


@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {

}
