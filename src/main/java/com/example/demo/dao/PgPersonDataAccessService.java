package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("postGres")
public class PgPersonDataAccessService implements PersonDao{
    String insertQuery = "INSERT INTO your_table_name (id, name) VALUES (?, ?)";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private static List<Person> personDB = new ArrayList<>();
    @Override
    public int insertPerson(UUID id, Person person) {
        String insertQuery = "INSERT INTO person_schema.persons (id, name) VALUES (?, ?)";
        int rows = jdbcTemplate.update(insertQuery, id, person.getName());
        if (rows > 0) {
            System.out.println("A new row has been inserted.");
        }
        return 1;
    }

    @Override
    public List<Person> getAllPersons() {
        return personDB;
    }

    @Override
    public Optional<Person> getPersonByID(UUID id) {
        String selectQuery = "SELECT * FROM your_table_name WHERE id = ?";
        return personDB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public int deletePersonByID(UUID id) {
        Optional<Person> person = getPersonByID(id);
        if(person.isEmpty())
        {
            return 0;
        }
        else {
            personDB.remove(person.get());
        }
        return 1;

    }

    @Override
    public int updatePersonByID(UUID id, Person person) {
        return getPersonByID(id).map(p -> {
            int index = personDB.indexOf(p);
            if(index >= 0)
            {
                personDB.set(index, person);
                return 1;
            }
            return 0;
        }).orElse(0);
    }

}
