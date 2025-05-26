package com.example.javawebdemo.repository;

import com.example.javawebdemo.model.FizzBuzzResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FizzBuzzResultRepository extends JpaRepository<FizzBuzzResult, Integer> {
}
