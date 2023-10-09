package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class PersonService {

    private final PersonDao personDao;
    @Autowired
    public PersonService(@Qualifier("postGres") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person)
    {
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPersons()
    {
        return personDao.getAllPersons();
    }

    public Optional<Person> getPersonByID(UUID id) {
        return personDao.getPersonByID(id);
    }

    public int deletePersonByID(UUID id) {
        return personDao.deletePersonByID(id);
    }

    public int updatePersonByID(UUID id, Person person){
        return personDao.updatePersonByID(id, person);
    }


}
