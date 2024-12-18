public class UnionFind {
    // TODO: Instance variables
    private int[] item;
    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        if (N <= 0) {
            throw new IllegalArgumentException("Illegal Argument. Please provide a positive int.");
        }
        item = new int[N];
        for (int i = 0; i < N; i++) item[i] = -1;
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        while (item[v] > 0) {
            v = item[v];
        }
        return Math.abs(item[v]);
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        return item[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (this.find(v1) == this.find(v2)) return true;


        return false;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        if (v >= item.length) {
            throw new IllegalArgumentException("Cannot find an out of range vertex!");
        }
        int r = v;
        while (item[v] > 0) {
            v = item[v];
        }
        while (item[r] > 0) {
            int tmp = item[r];
            item[r] = v;
            r = tmp;
        }
        return v;
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        int root1 = find(v1);
        int root2 = find(v2);
        if (root2 == root1) return;
        if (sizeOf(v1) <= sizeOf((v2))) {
            item[find(v2)] += item[find(v1)];
            item[find(v1)] = find(v2);
        } else {
            item[find(v1)] += item[find(v2)];
            item[find(v2)] = find(v1);
        }
    }

}
