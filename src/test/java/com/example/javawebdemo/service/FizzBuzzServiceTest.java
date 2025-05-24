package com.example.javawebdemo.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FizzBuzzServiceTest {

    // sut = System Under Test
    private FizzBuzzService sut = new FizzBuzzService();

    @Test
    public void When_Number_Is_Not_3or5_Not_Divisible_By_3or5(){
        int number = 8;
        String result = sut.fizzBuzz(number);
        assertEquals("8", result);
    }

    @Test
    public void When_Number_Is_Divisible_By_5(){
        int number = 10;
        String result = sut.fizzBuzz(number);
        assertEquals("Buzz", result);
    }

    @Test
    public void When_Number_Is_Divisible_By_3(){
        int number = 9;
        String result = sut.fizzBuzz(number);
        assertEquals("Fizz", result);
    }

    @Test
    public void When_Number_Is_3_And_Divisible_By_3(){
        int number = 3;
        String result = sut.fizzBuzz(number);
        assertEquals("FizzFizz", result);
    }

    @Test
    public void When_Number_Is_5_And_Divisible_By_5(){
        int number = 5;
        String result = sut.fizzBuzz(number);
        assertEquals("BuzzBuzz", result);
    }

    @Test
    public void Number_Contains_3() {
        assertEquals("Fizz", sut.fizzBuzz(13)); // contains '3'
    }

    @Test
    public void Number_Contains_5() {
        assertEquals("Buzz", sut.fizzBuzz(52)); // contains '5'
    }

    @Test
    public void Number_Divisible_By_5_And_Contains_5() {
        assertEquals("BuzzBuzzBuzz", sut.fizzBuzz(55));
    }

    @Test
    public void Number_Divisible_By_3_And_Contains_3() {
        assertEquals("FizzFizzFizz", sut.fizzBuzz(33));
    }

    @Test
    public void Number_ContainsBy_3_And_5() {
        assertEquals("BuzzFizz", sut.fizzBuzz(53));
    }

    @Test
    public void Number_Contains_5_And_3_Divisible_By_5() {
        assertEquals("FizzBuzzBuzz", sut.fizzBuzz(35));
    }

    @Test
    public void Number__Divisible_By_3_And_5() {
        assertEquals("BuzzFizzBuzz", sut.fizzBuzz(15));
    }

}