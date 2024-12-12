import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>{
    Node sentinel;
    int size = 0;
    private class Node {
        T item;
        Node prev;
        Node next;
        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }
    @Override
    public void addFirst(T x) {
        Node toAdd = new Node(x, sentinel, sentinel.next);
        sentinel.next.prev = toAdd;
        sentinel.next = toAdd;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node toAdd = new Node(x, sentinel.prev, sentinel);
        sentinel.prev.next = toAdd;
        sentinel.prev = toAdd;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node current = sentinel.next;
        while (current.item != null) {
            returnList.add(current.item);
            current = current.next;
        }
        return returnList;
//        return List.of();
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel) return true;
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        Node oldFirst = sentinel.next;
        sentinel.next = oldFirst.next;
        oldFirst.next.prev = sentinel;
        size -= 1;
        return oldFirst.item;
    }

    @Override
    public T removeLast() {
        Node oldLast = sentinel.prev;
        oldLast.prev.next = sentinel;
        sentinel.prev = oldLast.prev;
        size -= 1;
        return oldLast.item;
    }

    @Override
    public T get(int index) {
        if (index <= size) {
            Node current = sentinel.next;
            T res = sentinel.next.item;
            for (int i = 0; i <= index; i++) {
                res = current.item;
                current = current.next;
            }
            return res;
        }
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return getRecursiveHelper(index, sentinel.next);
    }
    private T getRecursiveHelper(int index, Node starter) {
        if (index >= size || index < 0) return null;
        if (index == 0) return starter.item;
        else {
            return getRecursiveHelper(index-1, starter.next);
        }
    }
}
