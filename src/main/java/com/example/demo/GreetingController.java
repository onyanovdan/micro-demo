package com.example.demo;

import com.example.demo.AOP.LogAllParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("greeting")
public class GreetingController {

    @Autowired
    GreetingUtils greetingUtils;

    private static final String template = "Hello, %s!";
    private final List<String> names_list = new ArrayList<>();

    @LogAllParams
    @GetMapping()
    public List<String> getNames_list() {
        return names_list;
    }

    @GetMapping("{id}")
    public Greeting greeting(@PathVariable Long id) {
        return greetingUtils.findByID(id);
    }

//    private Greeting getName(Integer id) {
//        String name = names_list.get(id);
//        return new Greeting(id, formatGreeting(name));
//    }

    private String formatGreeting(String name) {
        return String.format(template, name);
    }

    @PostMapping()
    public Greeting newGreeting(@RequestParam(value = "name", defaultValue = template) String title) {
        return greetingUtils.create(title);
    }

    @PutMapping("{id}")
    public Greeting setGreeting(@PathVariable Long id, @RequestBody String name) {
        greetingUtils.addUser(id, new User(name));
        return greetingUtils.findByID(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        greetingUtils.delete(id);
    }
}
