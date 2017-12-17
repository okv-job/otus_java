package ru.korytnikov.oleg.test;

import ru.korytnikov.oleg.annotations.After;
import ru.korytnikov.oleg.annotations.Before;

public class Test {

    private int a = 0;

    @Before
    public void aInit(){
        a = 1;
    }

    @ru.korytnikov.oleg.annotations.Test
    public void aCheck(){
        if (a == 1){
            throw new RuntimeException();
        }
    }

    @After
    public void aClear(){
        a = 0;
    }
}
