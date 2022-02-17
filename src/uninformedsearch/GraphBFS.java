package uninformedsearch;

import java.util.*;

public class GraphBFS {
    public static void main(String[] args) {
        Node<Integer> start = new Node<>(10);
        Node<Integer> firstNeighbor = new Node<>(2);
        start.connect(firstNeighbor);

        Node<Integer> firstNeighborNeighbor = new Node<>(3);
        firstNeighbor.connect(firstNeighborNeighbor);
        firstNeighborNeighbor.connect(start);

        Node<Integer> secondNeighbor = new Node<>(4);
        start.connect(secondNeighbor);

        search(3, start);
    }

    public static <T> Optional<Node<T>> search(T value, Node start) {
        Queue<Node<T>> queue = new ArrayDeque<>();
        Set<Node<T>> alreadyVisited = new HashSet<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Node<T> current = queue.remove();
            System.out.println("Visited node with value: " + current.getValue());
            if (current.getValue().equals(value)) {
                return Optional.of(current);
            } else {                                //makes sure no cycles.
                alreadyVisited.add(current);
                queue.addAll(current.getNeighbors());
                queue.removeAll(alreadyVisited);
            }
        }

        return Optional.empty();
    }

}

class Node <T> {
    private T value;
    private Set<Node<T>> neighbors;

    public Node(T value) {
        this.value = value;
        this.neighbors = new HashSet<>();
    }

    public T getValue() {
        return value;
    }

    public Set<Node<T>> getNeighbors() {
        return neighbors;
    }

    public void connect(Node<T> node) {
        if (this == node) throw new  IllegalArgumentException("Cannot connect to itself");
        this.neighbors.add(node); //neighbor links node
        node.neighbors.add(this); //node links neighbor... double linked grapth, can make backtracking
    }
}
