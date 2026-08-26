import java.util.*;

class FreqStack {

    private Map<Integer, Integer> freq;
    private Map<Integer, Stack<Integer>> group;
    private int maxFreq;

    public FreqStack() {
        freq = new HashMap<>();
        group = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int val) {

        // Increase frequency of val
        int newFreq = freq.getOrDefault(val, 0) + 1;
        freq.put(val, newFreq);

        // Create stack for this frequency if needed
        if (!group.containsKey(newFreq)) {
            group.put(newFreq, new Stack<>());
        }

        // Add val to the stack of its new frequency
        group.get(newFreq).push(val);

        // Update maximum frequency
        maxFreq = Math.max(maxFreq, newFreq);
    }

    public int pop() {

        // Get stack containing elements
        // with maximum frequency
        Stack<Integer> stack = group.get(maxFreq);

        // Most recently pushed among them
        int val = stack.pop();

        // Decrease frequency
        freq.put(val, freq.get(val) - 1);

        // If no elements remain at this frequency,
        // decrease maxFreq
        if (stack.isEmpty()) {
            maxFreq--;
        }

        return val;
    }
}