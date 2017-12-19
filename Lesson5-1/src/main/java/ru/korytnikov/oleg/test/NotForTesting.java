package ru.korytnikov.oleg.test;

import ru.korytnikov.oleg.annotations.Test;
import ru.korytnikov.oleg.asserts.Asserts;

public class NotForTesting {
    private int a = 0;

    public void aInit(){
        a = 1;
    }

    @Test(enabled = false)
    public void aCheck(){
        System.out.println("Never see this msg");
        Asserts.assertEquals(1,0);
    }

    public void aClear(){
        a = 0;
    }
}
