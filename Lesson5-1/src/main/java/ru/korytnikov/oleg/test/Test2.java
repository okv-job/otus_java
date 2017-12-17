package ru.korytnikov.oleg.test;

import ru.korytnikov.oleg.annotations.After;
import ru.korytnikov.oleg.annotations.Before;

public class Test2 {

    private int a = 0;

    @Before
    public void a2Init(){
        a = 1;
    }

    @ru.korytnikov.oleg.annotations.Test
    public void a2Check(){
        if (a == 0){
            throw new RuntimeException();
        }
    }

    @After
    public void a2Clear(){
        a = 0;
    }
}
