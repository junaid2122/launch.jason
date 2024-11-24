import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class City {
    String name;
}

class Flight {
    int connected;
    int dis;
    int amount;

    Flight(int connected, int dis, int amount) {
        this.connected = connected;
        this.dis = dis;
        this.amount = amount;
    }
}

class Graph {
    List<City> cities;
    List<List<Flight>> adjList;

    Graph(int num) {
        cities = new ArrayList<>(num);
        adjList = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            cities.add(new City());
            adjList.add(new ArrayList<>());
        }
    }

    void addFlight(int u, int v, int dis, int am) {
        adjList.get(u).add(new Flight(v, dis, am));
        adjList.get(v).add(new Flight(u, dis, am));
    }

    void addCity(int i, String name) {
        cities.get(i).name = name;
    }
}

public class pr3b {
    
    public static void findFlightDetails(Graph g, String cname) {
        for (int i = 0; i < g.adjList.size(); i++) {
            if (g.cities.get(i).name.equals(cname)) {
                for (Flight flight : g.adjList.get(i)) {
                    System.out.println("City Name: " + g.cities.get(flight.connected).name);
                    System.out.println("Amount: " + flight.amount + " and Distance: " + flight.dis);
                }
                return; 
            }
        }
        System.out.println("City not found.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of cities: ");
        int num = sc.nextInt();
        Graph g = new Graph(num);

        System.out.println("Enter names of cities:");
        for (int i = 0; i < num; i++) {
            System.out.print("Enter name of city " + (i + 1) + ": ");
            String s = sc.next();
            g.addCity(i, s);
        }

        System.out.print("Enter number of flights: ");
        int tf = sc.nextInt();

        System.out.println("Enter flight data (u v distance amount):");
        while (tf-- > 0) {
            System.out.print("Enter city indices (0-based) and flight details: ");
            int u = sc.nextInt();
            int v = sc.nextInt();
            int dis = sc.nextInt();
            int am = sc.nextInt();
            g.addFlight(u, v, am, dis);
        }

        System.out.print("Enter the name of the city to find flight details: ");
        String city = sc.next();
        findFlightDetails(g, city);

        sc.close();
    }
}
