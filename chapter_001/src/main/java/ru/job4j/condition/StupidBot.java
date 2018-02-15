package ru.job4j.condition;

public class StupidBot {

    public String answer(String question){
        String answ = "fuck off";
        if ("Ku".equals(question)){
            answ = "ku,bro";
        } else if ("Chao".equals(question)){
            answ = "See you";
        }
        return answ;
    }


}

