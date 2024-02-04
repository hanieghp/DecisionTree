public class MyLinkedList<T> {
    private Node head;

    public Node getHead() {
        return head;
    }

    public void add(Node node) {
        if (head == null) {
            head = node;
        } else {
            Node current = head;
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