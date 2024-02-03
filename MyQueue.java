public class MyQueue<T> {
    private MyLinkedList<T> linkedList;

    public MyQueue() {
        this.linkedList = new MyLinkedList<>();
    }

    public void enqueue(NodeTraverse<T> node) {
        linkedList.add(node);
    }

    public NodeTraverse<T> dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        NodeTraverse<T> node = linkedList.getHead();
        linkedList.remove();
        return node;
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}