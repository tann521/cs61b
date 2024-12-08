public class Main {
    public static void main(String[] args) {

        IntNode L = new IntNode(15, null);
        L = new IntNode(20, L);
        L = new IntNode(5, L);
        L = new IntNode(13, L);
        L = new IntNode(9, L);
//        System.out.println(L.size( ));
//        System.out.println(L.IterSize());
//        System.out.println(L.get(2));
//        IntNode res = IntNode.incrList(L, 2);
        for (int i = 0; i < L.size(); i++) {
            System.out.println(L.get(i));
        }
        System.out.println();
        IntNode res = IntNode.dincrList(L, 3);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }
}