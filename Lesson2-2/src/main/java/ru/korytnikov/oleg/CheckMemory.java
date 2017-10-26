package ru.korytnikov.oleg;

import java.util.ArrayList;

public class CheckMemory {

    public static void main(String ... args){

        Memory memory = new Memory();
        memory.getSizeForObject();
        memory.getSizeForEmptyString();
        memory.getSizeForEmptyIntContainer();
        memory.getSizeForEmptyDoubleContainer();
        memory.getSizeForEmptyLongContainer();
        memory.getSizeForEmptyStringContainer();
        memory.getSizeForFilledIntContainer(51);

    }
}
