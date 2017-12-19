package ru.korytnikov.oleg.asserts;

public class Asserts {
    public static void assertEquals(Object o1, Object o2) throws TestAssertException {
        if (!o1.equals(o2)) throw new TestAssertException(o1 + " != " + o2);
    }

    public static <T extends Number> void assertEquals(T o1, T o2) throws TestAssertException {
        if (o1 != o2) throw new TestAssertException(o1 + " != " + o2);
    }
}
