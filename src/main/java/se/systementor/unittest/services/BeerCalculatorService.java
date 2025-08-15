package se.systementor.unittest.services;

import org.springframework.stereotype.Service;

@Service
public class BeerCalculatorService {
    public boolean   CanBuyBeer(int age, String location,float promille) {

        if(promille > 1){
            return false;
        }
        if(age >= 18 && location.equalsIgnoreCase("K")){
            return true;
        }
        if(age >= 20 && location.equalsIgnoreCase("S")){
            return true;
        }

        return false;
    }
}
