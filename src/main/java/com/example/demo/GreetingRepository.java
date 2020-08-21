package com.example.demo;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface GreetingRepository extends PagingAndSortingRepository<Greeting, Long> {

}
