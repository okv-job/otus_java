package ru.korytnikov.oleg.test;

import ru.korytnikov.oleg.annotations.After;
import ru.korytnikov.oleg.annotations.Before;
import ru.korytnikov.oleg.asserts.Asserts;

public class Test2 {

    private int a = 0;

    @Before
    public void a2Init(){
        a = 1;
    }

    @ru.korytnikov.oleg.annotations.Test
    public void a2Check(){
        Asserts.assertEquals(1,1);
        Asserts.assertEquals("Oleg", "Oleg");
    }

    @After
    public void a2Clear(){
        a = 0;
    }
}
