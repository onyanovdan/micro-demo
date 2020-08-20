package com.example.demo;

import com.example.demo.AOP.LogAllParams;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("greeting")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final List<String> names_list = new ArrayList<>();

    @LogAllParams
    @GetMapping()
    public List<String> getNames_list() {
        return names_list;
    }

    @GetMapping("{id}")
    public Greeting greeting(@PathVariable Integer id) {
        return getName(id);
    }

    private Greeting getName(Integer id) {
        String name = names_list.get(id);
        return new Greeting(id, formatGreeting(name));
    }

    private String formatGreeting(String name) {
        return String.format(template, name);
    }

    @PostMapping()
    public Greeting newGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        names_list.add(name);
        return new Greeting(names_list.size(), formatGreeting(name));
    }

    @PutMapping("{id}")
    public Greeting setGreeting(@PathVariable Integer id, @RequestBody String name) {
        names_list.set(id, name);
        return new Greeting(id, formatGreeting(name));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        System.out.println(names_list.remove(id.intValue()));
        System.out.println(names_list);
    }
}
