package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GreetingUtils {

    @Autowired
    GreetingRepository greetingRepository;

    @Transactional
    public Greeting findByID(Long ID) {
        return greetingRepository.findById(ID).get();
    }

    @Transactional
    public List<Greeting> findAll(Sort sort) {
        return (List) greetingRepository.findAll(sort);
    }

    @Transactional
    public Greeting create(String title) {
        Greeting greeting = new Greeting(title);
        greetingRepository.save(greeting);
        return greeting;
    }

    @Transactional
    public void addUser(Long ID, User user) {
        Greeting greeting = greetingRepository.findById(ID).get();
        greeting.addUser(user);
        greetingRepository.save(greeting);
    }

    @Transactional
    public void delete(Long ID) {
        greetingRepository.deleteById(ID);
    }
}
