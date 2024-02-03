public class NodeTraverse<T> {
    private T data;
    private NodeTraverse<T> next;

    public NodeTraverse(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public NodeTraverse<T> getNext() {
        return next;
    }

    public void setNext(NodeTraverse<T> next) {
        this.next = next;
    }
}