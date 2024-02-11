import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    SimpleWeightedGraph<Person, DefaultWeightedEdge> grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
    Person juan = new Person("Juan", 5.53528, -73.36778);// Tunja
    Person maria = new Person("Maria", 5.8245, -73.03408); // Duitama
    Person carlos = new Person("Carlos", 7.12539, -73.1198); // Bogotá
    Person ana = new Person("Ana", 6.25184, -75.56359); // Medellin
    Person pedro = new Person("Pedro", 6.33732, -75.55795); // Bello

    public Controller() {
        grafo.addVertex(juan);
        grafo.addVertex(maria);
        grafo.addVertex(carlos);
        grafo.addVertex(ana);
        grafo.addVertex(pedro);
    }

    public void testDistance(){
        addEdgeWithDistance(juan, maria);
        addEdgeWithDistance(juan, carlos);
        addEdgeWithDistance(juan, ana);
        addEdgeWithDistance(juan, pedro);
        addEdgeWithDistance(maria, pedro);
        addEdgeWithDistance(carlos, pedro);
        addEdgeWithDistance(ana, pedro);
        addEdgeWithDistance(ana, carlos);
        addEdgeWithDistance(maria, carlos);
        addEdgeWithDistance(maria, ana);



    }

    private void addEdgeWithDistance(Person personOne, Person personTwo) {
        DefaultWeightedEdge edge = grafo.addEdge(personOne, personTwo);
        grafo.setEdgeWeight(edge, calculateDistance(personOne, personTwo));
    }

    private double calculateDistance(Person personOne, Person personTwo) {
        double dLat = Math.toRadians(personTwo.getLatitude() - personOne.getLatitude());
        double dLon = Math.toRadians(personTwo.getLength() - personOne.getLength());

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(personOne.getLatitude())) * Math.cos(Math.toRadians(personTwo.getLatitude()))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6371 * c; // Radio medio de la Tierra en kilómetros
    }

    public List<Person> recommendationsFriendsDistance(Person person, double maxDistance) {

        DijkstraShortestPath<Person, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(grafo);

        List<Person> suggestions = new ArrayList<>();

        for (Person neighbour : grafo.vertexSet()) {
            if (!neighbour.equals(person)) {
                GraphPath<Person, DefaultWeightedEdge> path = dijkstra.getPath(person, neighbour);
                if (path != null && (maxDistance <= 0 || path.getWeight() <= maxDistance)) {
                    suggestions.add(neighbour);
                }
            }
        }

        return suggestions;
    }

}
