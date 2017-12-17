package ru.korytnikov.oleg.test;

import ru.korytnikov.oleg.annotations.Test;

public class NotForTesting {
    private int a = 0;

    public void aInit(){
        a = 1;
    }

    @Test(enabled = false)
    public void aCheck(){
        System.out.println("Never see this msg");
        if (a == 1){
            throw new RuntimeException();
        }
    }

    public void aClear(){
        a = 0;
    }
}
