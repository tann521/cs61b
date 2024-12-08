public class IntNode {
    int first;
    IntNode next;
    public IntNode (int first, IntNode next) {
        this.first = first;
        this.next = next;
    }
    public int size() {
        if (this.next == null) return 1;
        else return 1+this.next.size();
    }
    public  int IterSize() {
        int cnt = 1;
        IntNode pnt = this;
        while (pnt.next != null) {
            pnt = pnt.next;
            cnt += 1;
        }
        return cnt;
    }
    public int get(int x) {
        if (x == 0) return this.first;
        else return this.next.get(x-1);
    }
    public static IntNode incrList(IntNode L, int x) {
        IntNode current = L;
        IntNode res = new IntNode(current.first+x, null);
        IntNode currentResult = res;
        while (current.next != null) {
            currentResult.next = new IntNode(current.next.first+x, null);
            current = current.next;
            currentResult = currentResult.next;
        }
        return res;
    }
    public static IntNode dincrList(IntNode L, int x) {
        IntNode self = L;
        while (self.next != null) {
            self.first += x;
            self = self.next;
        }
        return L;
    }
}