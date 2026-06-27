class MinStack {

    private Stack<Long> st;
    private long mini;

    public MinStack() {
        st = new Stack<>();
    }

    public void push(int val) {

        if (st.isEmpty()) {
            mini = val;
            st.push((long) val);
            return;
        }

        if (val >= mini) {
            st.push((long) val);
        } else {
            st.push(2L * val - mini);
            mini = val;
        }
    }

    public void pop() {

        long x = st.pop();

        if (x < mini) {
            mini = 2 * mini - x;
        }
    }

    public int top() {

        long x = st.peek();

        if (x >= mini)
            return (int) x;

        return (int) mini;
    }

    public int getMin() {
        return (int) mini;
    }
}