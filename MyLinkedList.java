public class MyLinkedList<T> {
    private NodeTraverse<T> head;

    public NodeTraverse<T> getHead() {
        return head;
    }

    public void add(NodeTraverse<T> node) {
        if (head == null) {
            head = node;
        } else {
            NodeTraverse<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(node);
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void remove() {
        if (!isEmpty()) {
            head = head.getNext();
        }
    }
}