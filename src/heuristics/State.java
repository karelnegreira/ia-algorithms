package heuristics;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class State {
    private List<Stack<String>> state;
    private int heuristic;

    public State(List<Stack<String>> state, int heuristic) {
        this.state = state;
        this.heuristic = heuristic;
    }

    State(State state) {
        if (state != null) {
            this.state = new ArrayList<>();
            for (Stack s : state.getState()) {
                Stack s1;
                s1 = (Stack) s.clone();
                this.state.add(s1);
            }
            this.heuristic = state.getHeuristic();
        }
    }

    public List<Stack<String>> getState() {
        return state;
    }

    public void setState(List<Stack<String>> state) {
        this.state = state;
    }

    public int getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }
}
