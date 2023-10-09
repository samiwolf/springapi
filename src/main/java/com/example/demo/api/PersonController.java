package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1")
@RestController
public class PersonController {
    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @PostMapping("/add")
    public void addPerson(@RequestBody Person person){
        personService.addPerson(person);
    }


    @GetMapping("/all")
    public List<Person> getAllPeople()
    {
        return personService.getAllPersons();
    }

    @GetMapping("/person={id}")
    public Person getPersonByID(@PathVariable("id") UUID id) {
        return personService.getPersonByID(id).orElse(null);
    }
    @PostMapping("/delete={id}")
    public int deletePersonByID(@PathVariable("id") UUID id) {
        return personService.deletePersonByID(id);
    }

    @PostMapping("/update={id}")
    public int updatePersonByID(@PathVariable("id") UUID id, @RequestBody Person person) {
        return personService.updatePersonByID(id,person);
    }
}
