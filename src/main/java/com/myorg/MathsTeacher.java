package com.myorg;

import java.time.LocalDateTime;

public class MathsTeacher {

    public static void main(String args[]) throws InterruptedException {
        MathsTeacher mathsTeacher = new MathsTeacher();
        System.out.println("Generating the first number");
        int firstNumber = mathsTeacher.generateMultipliers();
        Thread.sleep(1000);
        System.out.println("Generating the second number");
        int secondNumber = mathsTeacher.generateMultipliers();
        Thread.sleep(2000);
        int firstNumMinus100 = 100 - firstNumber;
        System.out.println(firstNumber + " === (100 - "+ firstNumber +") ===> " + firstNumMinus100);

        Thread.sleep(2000);
        int secondNumMinus100 = 100 - secondNumber;
        System.out.println(secondNumber + " === (100 - "+ secondNumber +") ===> " + secondNumMinus100);

        int result = (firstNumber - secondNumMinus100) * 100 + (firstNumMinus100 * secondNumMinus100);
        System.out.println(result);

        Thread.sleep(4000);
        System.out.println( result == firstNumber * secondNumber);

        System.out.println(LocalDateTime.now());

    }

    private int generateMultipliers() {
        int min = 90;
        int max = 100;
        int mathRandom = min + (int) (Math.random() * (max - min) + 1);

        if(mathRandom< 90 || mathRandom > 100 ) {
            System.err.println(mathRandom + "generated beyond the limit by <mathRandom>");

        } else {
            System.out.println(mathRandom +" generated as random number ");
        }
        return mathRandom;
    }
}
