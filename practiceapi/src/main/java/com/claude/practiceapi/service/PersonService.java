package com.claude.practiceapi.service;


import com.claude.practiceapi.domain.Person;
import com.claude.practiceapi.repo.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * BUSINES LOGIC GOES HERE
 */


@Service
public class PersonService {

    //dependency injection
    @Autowired
    public PersonRepo personRepo;

    //looks through all of the person objects available, adds them all to an arrayList and then returns the whole list
    public List<Person>getPersonList(){
        List<Person> people = new ArrayList<>();
        personRepo.findAll().forEach(people::add);
        return people;
    }

    //saves newly created person object to the repository
    public Person createPerson(Person person){
        return personRepo.save(person);
    }

    //looks for people objects based on their id and returns them if they exist/can be found
    public Person getPerson(Long id){
        return personRepo.findById(id).orElse(null);
    }

    //same as createPerson //updates(overwrites) existing person object with one that has diff properties
    public Person updatePerson( Person person){
        return personRepo.save(person);
    }

    //looks for person by their id property then deletes them
    public void deletePerson(Long id){
        personRepo.deleteById(id);
    }

}
