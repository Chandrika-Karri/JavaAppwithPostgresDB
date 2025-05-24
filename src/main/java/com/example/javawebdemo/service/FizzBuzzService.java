package com.example.javawebdemo.service;

import org.springframework.stereotype.Service;

@Service
public class FizzBuzzService {
      public String fizzBuzz(int number) {
          String result = "";
          for (char c : String.valueOf(number).toCharArray()) {
              if (c == '3') {
                  result += "Fizz";
              } else if (c == '5') {
                  result += "Buzz";
              }
          }
          if (number % 3 == 0) {
              result += "Fizz";
          }
          if (number % 5 == 0) {
              result += "Buzz";
          }

          return result.isEmpty() ? String.valueOf(number) : result;
      }
}
