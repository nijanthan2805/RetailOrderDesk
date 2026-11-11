
public class MyQueue<T> {

    Object[] queue = new Object[100];
    int front = 0;
    int rear = 0;
    int size = 0;

    public void enqueue(T value) {

        if (size == queue.length) {
            System.out.println("Queue is full");
            return;
        }
        queue[rear] = value;
        rear = (rear + 1) % queue.length;

        size++;
    }
    @SuppressWarnings("unchecked")
    public T dequeue() {

        if (size == 0) {
            return null;
        }
        T value = (T) queue[front];
        front = (front + 1) % queue.length;
        size--;
        return value;
    }
    public boolean isEmpty() {

        return size == 0;
    }
}
