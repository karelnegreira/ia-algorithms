package heuristic5;

import java.util.Comparator;
import java.util.Objects;

public class Vertex implements Comparator<Vertex> {
    int node;
    int heuristicValue;

    public Vertex() {
    }

    public Vertex(int node, int heuristicValue) {
        this.node = node;
        this.heuristicValue = heuristicValue;
    }


    @Override
    public int compare(Vertex t1, Vertex t2) {
        if (t1.heuristicValue < t2.heuristicValue) {
            return -1;
        } else if (t1.heuristicValue > t2.heuristicValue) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return node == vertex.node && heuristicValue == vertex.heuristicValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(node, heuristicValue);
    }
}
