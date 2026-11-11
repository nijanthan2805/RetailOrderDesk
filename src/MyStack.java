
public class MyStack<T> {
    Object[] stack = new Object[100];
    int top = -1;
    public void push(T value) {

        if (top == stack.length - 1) {
            System.out.println("Stack is full");
            return;
        }

        stack[++top] = value;
    }
    @SuppressWarnings("unchecked")
    public T pop() {

        if (top == -1) {
            return null;
        }
        return (T) stack[top--];
    }
    public boolean isEmpty() {

        return top == -1;
    }
}
