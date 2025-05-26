package com.example.javawebdemo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "FizzBuzz")
public class FizzBuzzResult {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;
        private int input;
        private String result;

        public FizzBuzzResult() {}

        public FizzBuzzResult(int input, String result) {
            this.input = input;
            this.result = result;
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInput() {
            return input;
        }

        public void setInput(int input) {
            this.input = input;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }


