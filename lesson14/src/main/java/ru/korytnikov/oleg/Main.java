package ru.korytnikov.oleg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Integer> unsortedArray = getUnsortedArray(10000);
        try {
            System.out.println(ThreadSortUtil.getSortedArray(unsortedArray, 5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    private static List<Integer> getUnsortedArray(int capacity) {
        List<Integer> unsortedArray = new ArrayList<Integer>(capacity);

        for (int i = 0; i < capacity; i++) {
            unsortedArray.add(i);
        }
        Collections.shuffle(unsortedArray);
        return unsortedArray;
    }
}
