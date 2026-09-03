class CustomStack {

    private int[] stack;
    private int top;

    public CustomStack(int maxSize) {
        stack = new int[maxSize];
        top = 0;
    }

    public void push(int x) {
        if (top < stack.length) {
            stack[top] = x;
            top++;
        }
    }

    public int pop() {
        if (top == 0) {
            return -1;
        }

        top--;
        return stack[top];
    }

    public void increment(int k, int val) {
        int limit = Math.min(k, top);

        for (int i = 0; i < limit; i++) {
            stack[i] += val;
        }
    }
}