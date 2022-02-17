package uninformedsearch;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DepthFirstSearch {
    NodeData root = new NodeData(3);

    public static void main(String[] args) {
        DepthFirstSearch search = new DepthFirstSearch();

        addRecursively(search.root,7 );
        addRecursively(search.root, 12);
        addRecursively(search.root, 5);
        addRecursively(search.root, 99);

        search.traverseInOrderWithoutRecursion();
        System.out.println("---");
        search.traverseLevelOrder();

    }

    private static NodeData addRecursively(NodeData current, int value) {
        if (current == null) {
            return new NodeData(value);
        }
        if (value < current.value) {
            current.left = addRecursively(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursively(current.right, value);
        }

        return current;
    }

    public void traverseLevelOrder() {
        if (root == null) {
            return;
        }
        Queue<NodeData> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            NodeData node = queue.remove();
            System.out.println(" " + node.value);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public void traversePreOrderWithoutRecursion() {
        Stack<NodeData> stack = new Stack<>();
        NodeData current = root;
        stack.push(root);
        while (!stack.isEmpty()) {
            current = stack.pop();
            visit(current.value);
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }

    public void traverseInOrderWithoutRecursion() {
        Stack<NodeData> stack = new Stack<>();
        NodeData current = root;
        stack.push(root);
        while (!stack.isEmpty()) {
            while (current.left != null) {
                current = current.left;
                stack.push(current);
            }
            current = stack.pop();
            visit(current.value);
            if (current.right != null) {
                current = current.right;
                stack.push(current);
            }
        }
    }

    private boolean isEmpty() {
        return root == null;
    }

    private void visit(int value) {
        System.out.println(" " + value);
    }
}

class NodeData {
    int value;
    NodeData left;
    NodeData right;

    public NodeData(int value, NodeData left, NodeData right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public NodeData(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
