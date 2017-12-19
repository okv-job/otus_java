package ru.korytnikov.oleg.test;

import ru.korytnikov.oleg.annotations.After;
import ru.korytnikov.oleg.annotations.Before;
import ru.korytnikov.oleg.asserts.Asserts;

public class Test {

    private int a = 0;

    @Before
    public void aInit(){
        a = 1;
    }

    @ru.korytnikov.oleg.annotations.Test
    public void aCheck(){
        Asserts.assertEquals(1,0);
    }

    @After
    public void aClear(){
        a = 0;
    }
}
