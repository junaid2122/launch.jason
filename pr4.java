import java.util.*;

class Edge {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class DisjointSet {
    private int[] parent, rank;

    public DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int u) {
        if (u < 0 || u >= parent.length) {
            throw new IndexOutOfBoundsException("Node index out of range in find()");
        }
        if (parent[u] != u) {
            parent[u] = find(parent[u]); // Path compression
        }
        return parent[u];
    }

    public void unionSet(int u, int v) {
        int rootU = find(u);
        int rootV = find(v);

        if (rootU != rootV) {
            if (rank[rootU] > rank[rootV]) {
                parent[rootV] = rootU;
            } else if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
        }
    }
}

public class pr4 {

    public static void kruskalMST(int n, List<Edge> edges) {
        if (n <= 0) {
            System.out.println("The graph must have at least one node.");
            return;
        }

        DisjointSet ds = new DisjointSet(n);

        // Sort edges by weight
        edges.sort(Comparator.comparingInt(e -> e.weight));

        List<Edge> mst = new ArrayList<>();
        int totalWeight = 0;

        for (Edge edge : edges) {
            if (edge.src < 0 || edge.src >= n || edge.dest < 0 || edge.dest >= n) {
                System.err.println("Error: Invalid edge with src: " + edge.src + ", dest: " + edge.dest);
                continue;
            }

            if (ds.find(edge.src) != ds.find(edge.dest)) {
                ds.unionSet(edge.src, edge.dest);
                mst.add(edge);
                totalWeight += edge.weight;
                System.out.println("Adding edge: " + edge.src + " -- " + edge.dest + " (Weight: " + edge.weight + ")");
            }
        }

        if (mst.size() != n - 1) {
            System.out.println("The graph is disconnected. MST cannot be formed.");
            return;
        }

        System.out.println("Total weight of the minimum spanning tree: " + totalWeight);
        System.out.println("Edges in the minimum spanning tree:");
        for (Edge edge : mst) {
            System.out.println(edge.src + " -- " + edge.dest + " (Weight: " + edge.weight + ")");
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter number of nodes: ");
            int n = scanner.nextInt();

            if (n <= 0) {
                System.out.println("Invalid number of nodes. Must be greater than zero.");
                return;
            }

            System.out.print("Enter number of edges: ");
            int m = scanner.nextInt();

            if (m < 0) {
                System.out.println("Invalid number of edges. Cannot be negative.");
                return;
            }

            List<Edge> edges = new ArrayList<>();
            System.out.println("Enter edges in the format (src dest weight):");
            for (int i = 0; i < m; i++) {
                int src = scanner.nextInt();
                int dest = scanner.nextInt();
                int weight = scanner.nextInt();

                if (weight < 0) {
                    System.out.println("Invalid edge weight. Must be non-negative.");
                    return;
                }

                edges.add(new Edge(src, dest, weight));
            }

            try {
                kruskalMST(n, edges);
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            } finally {
                scanner.close();
            }
        }
    }
}
