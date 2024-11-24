import java.util.*;

class ShortestPathWithTraffic {
    static class Edge {
        int dest, weight;

        public Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    static class Node implements Comparable<Node> {
        int vertex, distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return this.distance - other.distance;
        }
    }

    public static int[] dijkstra(int n, List<List<Edge>> graph, int[] traffic, int source) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE); // Initialize all distances to infinity
        dist[source] = 0; // Distance to source is 0

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(source, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;

            // Traverse adjacent nodes
            for (Edge edge : graph.get(u)) {
                int v = edge.dest;
                int adjustedWeight = edge.weight + traffic[v]; // Adjust weight based on traffic

                // Relaxation step
                if (dist[u] + adjustedWeight < dist[v]) {
                    dist[v] = dist[u] + adjustedWeight;
                    pq.add(new Node(v, dist[v])); // Push updated distance into the queue
                }
            }
        }

        return dist; // Return the distance array
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of nodes: ");
        int n = scanner.nextInt();

        System.out.print("Enter number of edges: ");
        int m = scanner.nextInt();

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        System.out.println("Enter edges in the format (src dest weight):");
        for (int i = 0; i < m; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.get(src).add(new Edge(dest, weight)); // Add edge from src to dest
            graph.get(dest).add(new Edge(src, weight)); // Add edge from dest to src (undirected)
        }

        int[] traffic = new int[n];
        System.out.println("Enter traffic levels for each node (higher value indicates more traffic):");
        for (int i = 0; i < n; i++) {
            System.out.print("Traffic for node " + i + ": ");
            traffic[i] = scanner.nextInt();
        }

        System.out.print("Enter source node (A): ");
        int source = scanner.nextInt();

        System.out.print("Enter destination node (B): ");
        int destination = scanner.nextInt();

        int[] distances = dijkstra(n, graph, traffic, source);

        // Output the result
        if (distances[destination] == Integer.MAX_VALUE) {
            System.out.println("There is no path from " + source + " to " + destination + ".");
        } else {
            System.out.println("The shortest path from " + source + " to " + destination +
                               " considering traffic is: " + distances[destination]);
        }

        scanner.close();
    }
}
