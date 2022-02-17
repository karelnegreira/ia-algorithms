package uninformedsearch;

import java.util.*;

public class TreeBFS<T>{
    private T value;
    private List<TreeBFS<T>> children;

    private TreeBFS(T value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public static <T> TreeBFS<T> of(T value) {
        return new TreeBFS<>(value);
    }

    public TreeBFS<T> addChild(T value) {
        TreeBFS<T> newChild = new TreeBFS<>(value);
        children.add(newChild);
        return newChild;
    }

    public T getValue() {
        return value;
    }

    public List<TreeBFS<T>> getChildren() {
        return children;
    }

    public static <T> Optional<TreeBFS<T>> search(T value, TreeBFS<T> root) {
        Queue<TreeBFS<T>> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeBFS<T> current = queue.remove();

            if (current.getValue().equals(value)) {
                return Optional.of(current);
            } else {
                queue.addAll(current.getChildren());
            }
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        TreeBFS<Integer> root = TreeBFS.of(10);
        TreeBFS<Integer> rootFirstChild = root.addChild(2);
        TreeBFS<Integer> depthMostChild = rootFirstChild.addChild(3);
        TreeBFS<Integer> rootSecondChild = root.addChild(4);

        System.out.println(search(3, root));
    }

}


