package com.claude.practiceapi.controller;

import com.claude.practiceapi.service.PersonService;
import com.claude.practiceapi.domain.Person;
import com.sun.istack.internal.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
public class PersonController {

    private Logger log = LoggerFactory.getLogger(PersonController.class);

    //dependency injection
    @Autowired
    private PersonService personService;

    //when you do a get request on the localhost:8080/people url you should get a full list of your people objects
    @RequestMapping("/people")
    public List<Person> getPersonList(){
        return personService.getPersonList();
    }

    //a post request on the people uri will let you create a person object in the body of the post
    @RequestMapping(value = "/people", method = RequestMethod.POST)
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        personService.createPerson(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    //a get request on the for a specific person object by adding the object's id property to the the url
    @RequestMapping(value = "/people/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPerson(@PathVariable Long id){
        @Nullable
        Person p = personService.getPerson(id);
        ResponseEntity<?> responseEntity;

        if(p.equals(null)){
            responseEntity = new ResponseEntity<>(p, HttpStatus.OK);
        }
        else  {
            responseEntity = new ResponseEntity<>(p,HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    //allows for an update to an existing person object by simply retyping the object with changes to the properties
    @RequestMapping(value = "/people/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePerson(@RequestBody Person person){
        //check for an existing person obj before before I put one in the repo
        Person p = personService.getPerson(person.getId());
        Person aPerson = personService.updatePerson(person);

        ResponseEntity responseEntity;
        log.info("put " + p);

        //if the person I pass in already exists
        if(!aPerson.equals(p)) {
            responseEntity = new ResponseEntity<>(aPerson,HttpStatus.CREATED);
        }
        else {
            responseEntity = new ResponseEntity<>(aPerson, HttpStatus.OK);

        }
        return responseEntity;
    }

    //allows for delete requests on individual objects based on their id property
    @RequestMapping(value = "/people/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Optional<Person>> deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
        ResponseEntity<Optional<Person>> responseEntity = new ResponseEntity<Optional<Person>>(HttpStatus.NO_CONTENT);
        return responseEntity;
    }
}
