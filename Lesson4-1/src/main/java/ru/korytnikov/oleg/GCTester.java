package ru.korytnikov.oleg;

import java.util.ArrayList;
import java.util.List;

public class GCTester {
    public static void main(String... args) {
        List<Integer> list = new ArrayList<Integer>();
        int i = 1;
        int clean = 10;
        while (true) {
            list.add(i++);
            if (i == clean) {
                List<Integer> backupList = new ArrayList<>(list.subList(0,list.size()/2));
                list.clear();
                list = new ArrayList<>(backupList);
                backupList.clear();
                clean *= 1.1;
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
