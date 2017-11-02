package ru.oleg.korytnikov;

import java.util.*;

public class Test {
    public static void main(String ... args){
        List<String> myList = new MyArrayList<String>();
        myList.add("1");
        myList.add("2");
        myList.add("3");
        myList.add("4");
        System.out.println("Adding to myList " + myList);

        List<String> arrayList = new ArrayList<String>();
        arrayList.add("Jeka");
        arrayList.add("Oleg");
        arrayList.add("Pasha");
        arrayList.add("Alexey");

        System.out.println("Copy myList into arrayList ");
        System.out.println("ArrayList before " + arrayList);
        Collections.copy(myList,arrayList);
        myList.clear();
        myList.add("1");
        myList.add("2");
        myList.add("4");
        myList.add("3");
        Collections.copy(arrayList,myList);
        System.out.println(arrayList + " after");

        System.out.println("addAll to my collection ");
        System.out.println("my collection before " + myList);
        Collections.addAll(myList,"new value", "one more");
        System.out.println("After " + myList);
        System.out.println("Removing last 2 values and sorting ");
        myList.remove("new value");
        myList.remove("one more");


        Collections.sort(myList, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return Integer.parseInt(o1) - Integer.parseInt(o2);
            }
        });

        System.out.println(myList + " = FINAL");



    }
}
