package ru.korytnikov.oleg;

import java.util.ArrayList;
import java.util.List;

public class GCTester {
    public static void main(String... args) {
        List<Integer> list = new ArrayList<Integer>();
        int i = 1;
        while (true){
            list.add(i++);
            if (i%10000000==0){
                System.out.println(i/10000000);
            }
        }
    }
}
