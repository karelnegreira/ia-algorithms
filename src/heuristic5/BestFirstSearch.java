package heuristic5;

import java.util.PriorityQueue;
import java.util.Scanner;

/*
* The below's implementation in Java of the best-first algorithm, which is
* as per authors the search of a goal-node by expanding the most
* promising of those as per a given rule.
* */
public class BestFirstSearch {
    private PriorityQueue<Vertex> priorityQueue;
    private int heuristicValues[];
    private int numberOfNodes;

    public static final int MAX_VALUE = 999;

    public BestFirstSearch(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
        this.priorityQueue = new PriorityQueue<Vertex>(numberOfNodes, new Vertex());
    }

    public void bestFirstSearch(int adjMatrix[][], int[] heuristicValues, int source) {
        int evaluationNode;
        int destinationNode;
        int visited[] = new int[numberOfNodes + 1];
        this.heuristicValues = heuristicValues;

        priorityQueue.add(new Vertex(source, this.heuristicValues[source]));
        visited[source] = 1;

        while (!priorityQueue.isEmpty()) {
            evaluationNode = getNodeWithMinimalHeuristicValue();
            destinationNode = 1;

            System.out.print(evaluationNode + "\t");
            while (destinationNode <= numberOfNodes) {
                Vertex vertex = new Vertex(destinationNode, this.heuristicValues[destinationNode]);
                if ((adjMatrix[evaluationNode][destinationNode] != MAX_VALUE)
                        && (evaluationNode != destinationNode)
                        && (visited[destinationNode] == 0)) {
                    priorityQueue.add(vertex);
                    visited[destinationNode] = 1;
                }
                destinationNode++;
            }
        }
    }

    private int getNodeWithMinimalHeuristicValue() {
        Vertex vertex = priorityQueue.remove();
        return vertex.node;
    }

    public static void main(String... args) {
        int adjancency_matrix[][];
        int number_of_vertex;
        int source = 0;
        int heuristicValue[];

        Scanner scan = new Scanner(System.in);
        try {
            System.out.println("Enter the number of vertex");
            number_of_vertex = scan.nextInt();
            adjancency_matrix = new int[number_of_vertex + 1][number_of_vertex + 1];
            heuristicValue = new int[number_of_vertex + 1];

            System.out.println("Enter the Weighted Matrix of the graph");
            for (int i = 1; i <= number_of_vertex; i++) {
                for (int j = 1; j <= number_of_vertex; j++) {
                    adjancency_matrix[i][j] = scan.nextInt();
                    if (i == j) {
                        adjancency_matrix[i][j] = 0;
                        continue;
                    }
                    if (adjancency_matrix[i][j] == 0) {
                        adjancency_matrix[i][j] = MAX_VALUE;
                    }
                }
            }
            for (int i = 1; i <= number_of_vertex; i++) {
                for (int j = 1; j <= number_of_vertex; j++) {
                    if (adjancency_matrix[i][j] == 1 && adjancency_matrix[j][i] == 0) {
                        adjancency_matrix[j][i] = 1;
                    }
                }
            }
            System.out.println("Enter the heuristic value of the nodes");
            for (int vertex = 1; vertex <= number_of_vertex; vertex++) {
                System.out.print(vertex + ".");
                heuristicValue[vertex] = scan.nextInt();
                System.out.println();
            }
            System.out.println("Enter the source");
            source = scan.nextInt();

            System.out.println("The graph is explored as follows");
            BestFirstSearch bfs = new BestFirstSearch(number_of_vertex);
            bfs.bestFirstSearch(adjancency_matrix, heuristicValue, source);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        scan.close();
    }
}
