public class SLList {
    private IntNode Sentinel;
    public int size;

    public SLList(int x) {
        Sentinel = new IntNode(114514, null);
        Sentinel.next = new IntNode(x, null);
        size = 1;
    }

    public SLList() {
        Sentinel = new IntNode(114514, null);
        size = 0;
    }


    public int getFirst() {
        if (Sentinel.next == null) return -1;
        else return Sentinel.next.item;
    }

    public void addFirst(int i) {
        Sentinel.next = new IntNode(i, Sentinel.next);
        size += 1;
    }

    public void addLast(int x) {
        IntNode current = Sentinel;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new IntNode(x, null);
        size += 1;
    }

    public int size() {
        return size;
    }
}
//    public void insert(int index, int value) {
//        insertHelper(first, index, value);
//    }
//    private void insertHelper(IntNode current, int index, int value) {
//        System.out.println("Inserting " + value + " at index " + index);
//        if (index == 0) {
//            IntNode toAdd = new IntNode(value, current.next);
//            current.next = toAdd;
//        } else {
//            insertHelper(current.next, index-1, value);
//        }
//    }
//}
