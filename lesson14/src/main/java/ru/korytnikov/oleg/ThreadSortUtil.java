package ru.korytnikov.oleg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ThreadSortUtil {

    public static List<Integer> getSortedArray(final List<Integer> unsortedArray, int threadWorkersCount) throws InterruptedException {
        List<Integer> sortedArray;

        int workers = unsortedArray.size() >= threadWorkersCount ? threadWorkersCount : unsortedArray.size() / 2;

        if (workers == 0) {
            sortedArray = new ArrayList<>(unsortedArray);
            Collections.sort(sortedArray);
        } else {
            int arraySizePerWorker = unsortedArray.size() / workers;
            List<List<Integer>> sortedArrays = new ArrayList<>();

            int sortedCapacity = 0;

            while (sortedCapacity <= unsortedArray.size()) {
                List<Integer> unsortedPartOfArray;
                if (sortedCapacity + arraySizePerWorker > unsortedArray.size()) {
                    unsortedPartOfArray = unsortedArray.subList(sortedCapacity, unsortedArray.size());
                } else {
                    unsortedPartOfArray = unsortedArray.subList(sortedCapacity, sortedCapacity + arraySizePerWorker);
                }

                Thread thread = new Thread( () -> Collections.sort(unsortedPartOfArray));
                thread.start();
                thread.join();

                sortedArrays.add(unsortedPartOfArray);
                sortedCapacity += arraySizePerWorker;
            }

            sortedArray = sortAllArray(sortedArrays);

        }

        return sortedArray;
    }

    private static List<Integer> sortAllArray(List<List<Integer>> sortedPartsOfArray) {
        List<Integer> resultArray = null;
        Iterator<List<Integer>> it = sortedPartsOfArray.iterator();
        List<Integer> firstArr = null;

        while (it.hasNext()) {
            if (firstArr == null) {
                firstArr = it.next();
            } else if (resultArray == null) {
                resultArray = merge(firstArr, it.next());
            } else {
                resultArray = merge(resultArray, it.next());
            }
        }
        resultArray = resultArray == null ? firstArr : resultArray;

        return resultArray;
    }


    private static List<Integer> merge(List<Integer> first, List<Integer> second) {
        List<Integer> result = new ArrayList<>();

        int i=0, j=0;
        for (int k=0; k<first.size() + second.size(); k++) {

            int a;
            if (i > first.size()-1) {
                a = second.get(j);
                j++;
            }
            else if (j > second.size()-1) {
                a = first.get(i);
                i++;
            }
            else if (first.get(i) < second.get(j)) {
                a = first.get(i);
                i++;
            }
            else {
                a = second.get(j);
                j++;
            }

            result.add(a);
        }

        return result;
    }



}
