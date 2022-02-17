package heuristics;

import java.util.*;

public class HillClimbing {
    public static void main(String[] args) {
        HillClimbing hillClimbing = new HillClimbing();
        String blockArr[] = { "B", "C", "D", "A" };
        Stack<String> startState = hillClimbing.getStackWithValues(blockArr);
        String goalBlockArr[] = { "A", "B", "C", "D" };
        Stack<String> goalState = hillClimbing.getStackWithValues(goalBlockArr);
        try {
            List<State> solutionSequence = hillClimbing.getRouteWithHillClimbing(startState, goalState);
            solutionSequence.forEach(HillClimbing::printEachStep);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printEachStep(State state) {
        List<Stack<String>> stackList = state.getState();
        System.out.println("---------------------");
        stackList.forEach(stack -> {
            while (!stack.isEmpty()) {
                System.out.println(stack.pop());
            }
            System.out.println();
        });
    }

    private Stack<String> getStackWithValues(String[] blocks) {
        Stack<String> stack = new Stack<>();
        for (String block: blocks) {
            stack.push(block);
        }
        return stack;
    }
    /*
    * This method prepares
    * the path from init state till
    * goal state
    * */
    public List<State> getRouteWithHillClimbing(Stack<String> initStateStack, Stack<String> goalStateStack) throws Exception {
        List<Stack<String>> initStateStackList  = new ArrayList<>();
        initStateStackList.add(initStateStack);
        int inistateheuristic = getHeuristicValue(initStateStackList, goalStateStack);
        State initState = new State(initStateStackList, inistateheuristic);

        List<State> resultPath = new ArrayList<>();
        resultPath.add(new State(initState));

        State currentState = initState;
        boolean nostatefound = false;
        while (!currentState.getState().get(0).equals(goalStateStack) || nostatefound) {
            nostatefound = true;
            State nextState = findNextState(currentState, goalStateStack);
            if (nextState != null) {
                nostatefound = false;
                currentState = nextState;
                resultPath.add(new State(nextState));
            }
        }

        return resultPath;
    }

    private State findNextState(State currentState, Stack<String> goalStateStack) {
        List<Stack<String>> listofStacks = currentState.getState();
        int currentStateHeuristic = currentState.getHeuristic();

        return listofStacks.stream()
                .map(stack -> {
                    return applyOperationsOnState(listofStacks, stack, currentStateHeuristic, goalStateStack);
                })
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
    }

    /*
     * This method applies operations on the current state to get a new state
     */
    public State applyOperationsOnState(List<Stack<String>> listOfStacks, Stack<String> stack, int currentStateHeuristics, Stack<String> goalStateStack) {
        State tempState;
        List<Stack<String>> tempStackList = new ArrayList<>(listOfStacks);
        String block = stack.pop();
        if (stack.size() == 0) {
            tempStackList.remove(stack);
        }
        tempState = pushElementToNewStack(tempStackList, block, currentStateHeuristics, goalStateStack);
        if (tempState == null) {
            tempState = pushElementToExistingStacks(stack, tempStackList, block, currentStateHeuristics, goalStateStack);
        }
        if (tempState == null) {
            stack.push(block);
        }
        return tempState;
    }
    /*
     * Operation to be applied on a state in order to find new states. This
     * operation pushes an element into a new stack
     */
    private State pushElementToNewStack(List<Stack<String>> currentStackList, String block, int currentStateHeuristics, Stack<String> goalStateStack) {
        State newState = null;
        Stack<String> newStack = new Stack<>();
        newStack.push(block);

        currentStackList.add(newStack);
        int newStateHeuristics = getHeuristicValue(currentStackList, goalStateStack);
        if (newStateHeuristics > currentStateHeuristics) {
            newState = new State(currentStackList, newStateHeuristics);
        } else {
            currentStackList.remove(newStack);
        }
        return newState;
    }

    /*
     * Operation to be applied on a state in order to find new states. This
     * operation pushes an element into one of the other stacks to explore new
     * states
     */
    private State pushElementToExistingStacks(Stack currentStack, List<Stack<String>> currentStackList, String block, int currentStateHeuristic, Stack<String> goalStateStack) {
        Optional<State> newState = currentStackList.stream()
                .filter(stack -> stack != currentStack)
                .map(stack -> {
                    return pushElementToStacK(stack, block, currentStackList, currentStateHeuristic, goalStateStack);
                })
                .filter(Objects::nonNull)
                .findFirst();
        return newState.orElse(null);
    }

    private State pushElementToStacK(Stack stack, String block, List<Stack<String>> currentStackList, int currentStateHeuristics, Stack<String> goalStateStack) {
        stack.push(block);
        int newStateHeuristics = getHeuristicValue(currentStackList, goalStateStack);
        if (newStateHeuristics > currentStateHeuristics) {
            return new State(currentStackList, newStateHeuristics);
        }
        stack.pop();
        return null;
    }
    /*
    * This methods return heuristic values
    * for given state with respect to goal
    * */
    public int getHeuristicValue(List<Stack<String>> currentState, Stack<String> goalStateStack) {
        Integer heuristicValue;
        heuristicValue = currentState.stream()
                .mapToInt(stack -> {
                    return getHeuristicValueFromStack(stack, currentState, goalStateStack);
                })
                .sum();
        return heuristicValue;
    }

    /*
    * This method returns heuristic value for  a particular stack
    * */

    public int getHeuristicValueFromStack(Stack<String> stack, List<Stack<String>> currentStack, Stack<String> goalStack) {
        int stackHeuristic = 0;
        boolean isPositionCorrect = true;
        int goalStartIndex = 0;
        for (String currentBlock: stack) {
            if (isPositionCorrect && currentBlock.equals(goalStack.get(goalStartIndex))) {
                stackHeuristic += goalStartIndex;
            } else {
                stackHeuristic -= goalStartIndex;
                isPositionCorrect = false;
            }
            goalStartIndex++;
        }
        return stackHeuristic;
    }

}
