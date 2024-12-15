import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private int first;
    private int last;
    private int size;
    private int capacity;
    private T[] item;

    public ArrayDeque61B() {
        item =(T[]) new Object[8];
        first = 0;
        last = 1;
        size = 0;
        capacity = 8;
    }

    private void resize() {
        int newCapacity = capacity * 2;
        T[] newItem = (T[]) new Object[newCapacity];
        for (int i = first; i < last; ++i) {
            newItem[Math.floorMod(i, newCapacity)] = item[Math.floorMod(i, capacity)];
        }
        item = newItem;
        capacity = newCapacity;

    }

    @Override
    public void addFirst(T x) {
        if (size == capacity) resize();
        size += 1;
        item[Math.floorMod(first, capacity)] = x;
        first--;
    }

    @Override
    public void addLast(T x) {
        if (size == capacity) resize();
        size += 1;
        item[Math.floorMod(last, capacity)] = x;
        last++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            returnList.add(i, this.get(i));
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) return null;
        T x = item[Math.floorMod(first+1, capacity)];
        item[Math.floorMod(first+1, capacity)] = null;
        first += 1;
        size--;
        return x;
    }

    @Override
    public T removeLast() {
        if (size == 0) return null;
        size --;
        last --;
        T x = item[Math.floorMod(last, capacity)];
        item[Math.floorMod(last, capacity)] = null;
        return x;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) return null;
        int toGet = first + index + 1;
        return item[Math.floorMod(toGet, capacity)];
    }

    @Override
    public T getRecursive(int index) {
        return getRecursive(index, first);
    }

    private T getRecursive(int index, int starter) {
        if (index >= size || index < 0) return null;
        if (index == 0) return item[Math.floorMod(starter+1, capacity)];
        else {
            return getRecursive(index-1, starter+1);
        }
    }
}
