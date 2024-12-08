public class Dessert {
    int flavor;
    int price;
    static int numDesserts = 0;
    public Dessert(int flavor, int price) {
        this.flavor = flavor;
        this.price = price;
        numDesserts += 1;
    }
    public void printDessert() {
        System.out.printf("%d %d %d", flavor, price, numDesserts);
    }
    public static void main(String[] args) {
        System.out.println("I love dessert!");
    }
}
