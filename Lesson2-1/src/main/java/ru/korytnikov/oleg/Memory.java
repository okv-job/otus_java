package ru.korytnikov.oleg;

public class Memory {
    private long initMemorySize;
    private long usedMemory;
    private Runtime runtime = Runtime.getRuntime();


    private void init() {
        System.gc();
        initMemorySize = runtime.totalMemory() - runtime.freeMemory();
    }


    private void calculateMemory() {
        System.gc();
        long memorySize = runtime.totalMemory() - runtime.freeMemory();
        System.out.print(memorySize - initMemorySize + " bytes ");
    }

    private long calculateMemoryForArray(int size) {
        long memorySize = (runtime.totalMemory() - runtime.freeMemory()) / size;
        return memorySize;
    }


    public void getSizeForObject() {
        System.out.print("Размер объекта равен: ");
        init();
        Object object = new Object();
        calculateMemory();
        object.toString();
        System.out.println();
    }

    public void getSizeForEmptyString() {
        System.out.print("Размер пустой строки равен: ");
        init();
        String string = new String();
        calculateMemory();
        System.out.println(string);
    }

    public void getSizeForEmptyIntContainer() {
        System.out.print("Размер пустого int контейнера равен: ");
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                init();
                int[] container = new int[100];
                continue;
            }
            init();
            int[] container = new int[100];
            calculateMemory();
        }
        System.out.println(" и 1 элемент занимает " + calculateMemoryForArray(100) + " bytes");
    }

    public void getSizeForEmptyStringContainer() {
        System.out.print("Размер пустого String контейнера равен: ");
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                init();
                String[] container = new String[100];
                continue;
            }
            init();
            String[] container = new String[100];
            calculateMemory();
        }
        System.out.println(" и 1 элемент занимает " + calculateMemoryForArray(100) + " bytes");
    }

    public void getSizeForEmptyDoubleContainer() {
        System.out.print("Размер пустого double контейнера равен: ");
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                init();
                double[] container = new double[100];
                continue;
            }
            init();
            double[] container = new double[100];
            calculateMemory();
        }
        System.out.println(" и 1 элемент занимает " + calculateMemoryForArray(100) + " bytes");
    }

    public void getSizeForEmptyLongContainer() {
        System.out.print("Размер пустого long контейнера равен: ");
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                init();
                long[] container = new long[100];
                continue;
            }
            init();
            long[] container = new long[100];
            calculateMemory();
        }
        System.out.println(" и 1 элемент занимает " + calculateMemoryForArray(100) + " bytes");
    }


    public void getSizeForFilledIntContainer(int size) {
        System.out.println("Размер long контейнера равен: ");
        for (int i = 0; i < size + 1; i++) {
            if (i == 0) {
                init();
                int[] container = new int[i];
                continue;
            }
            if (i % 5 == 0) {
                System.out.print("при " + i + " элементах ");
                init();
                int[] container = new int[i];
                for (int j = 0; j < i; j++) {
                    container[j] = j;
                }
                calculateMemory();
                System.out.println();
            }
        }
        System.out.println("и 1 элемент занимает " + calculateMemoryForArray(100) + " bytes");
    }


}
