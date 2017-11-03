package ru.oleg.korytnikov;

import java.util.*;

public final class MyArrayList<T> implements List<T> {

    private final static int DEFAULT_CAPACITY = 10;


    private Object[] data;
    private int size;

    public MyArrayList() {
        data = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        data = new Object[capacity];
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int cursor = -1;

            public void remove() {
                MyArrayList.this.remove(cursor);
                cursor--;
            }

            public boolean hasNext() {
                return cursor < size - 1;
            }

            public T next() {
                if (++cursor < size) {
                    return (T) data[cursor];
                }
                return null;
            }
        };
    }

    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size)
            return (T1[]) Arrays.copyOf(data, size, a.getClass());
        System.arraycopy(data, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    public boolean add(T t) {
        data[size++] = t;
        incrementArray();
        return true;
    }

    public boolean remove(Object o) {
        for (int index = 0; index < size; index++)
            if (o.equals(data[index])) {
                remove(index);
                return true;
            }
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends T> c) {
        Object[] incomingArray = c.toArray();
        int newCapacity = data.length + incomingArray.length;
        incrementArray(newCapacity);
        System.arraycopy(incomingArray, 0, data, size, incomingArray.length);
        size += incomingArray.length;
        return true;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        Object[] incomingArray = c.toArray();
        int newCapacity = data.length + incomingArray.length;
        incrementArray(newCapacity);
        if (size - index > 0)
            System.arraycopy(data, index, data, index + incomingArray.length,
                    size - index);

        System.arraycopy(incomingArray, 0, data, index, incomingArray.length);
        size += incomingArray.length;
        return true;
    }

    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        data = new Object[data.length];
        size = 0;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return (T) data[index];
    }

    public T set(int index, T element) {
        data[index] = element;
        return null;
    }

    public void add(int index, T element) {
        throw new UnsupportedOperationException();
    }

    public T remove(int index) {
        int num = size - 1 - index;
        T old = get(index);
        System.arraycopy(data, index + 1, data, index, num);
        data[--size] = null;
        return old;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (data[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(data[i]))
                    return i;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private void incrementArray() {
        if (data.length / 2 < size) {
            int arrayCapacity = data.length * 2;
            Object[] objects = new Object[arrayCapacity];
            System.arraycopy(data, 0, objects, 0, data.length);
            data = objects;
        }
    }

    private void incrementArray(int newCapacity) {
        Object[] objects = new Object[newCapacity + 1];
        System.arraycopy(data, 0, objects, 0, data.length);
        data = objects;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object obj : data) {
            if (obj != null) sb.append(String.valueOf(obj) + ", ");
        }
        return sb.toString();
    }

    public void sort(Comparator<? super T> c) {
        Arrays.sort(data, 0, size, (Comparator<? super Object>) c);
    }

}
