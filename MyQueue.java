public class MyQueue {
    private int length;
    private Node front, rear;

    //  Creates an empty queue.
    public MyQueue(){
        length = 0;
        front = rear = null;
    }

    public void add (Node data){
        Node node = data;
        if (isEmpty())
            front = node;
        else
            rear.setNext (node);
        rear = node;
        length++;
    }

    //  Removes the data at the front of the queue and returns a
    //  reference to it. Throws an Exception if the
    //  queue is empty.

    public Node remove() throws Exception{
        if (isEmpty())
            throw new Exception ("queue");
        Node result = front;
        front = front.getNext();
        length--;
        if (isEmpty())
            rear = null;
        return result;
    }

    //  Returns a reference to the data at the front of the queue.
    //  The data is not removed from the queue.  Throws an
    //  Exception if the queue is empty.
    public Node peek() throws Exception{
        if (isEmpty())
            throw new Exception();

        return front;
    }

    //  Returns true if this queue is empty and false otherwise.
    public boolean isEmpty(){
        return (length == 0);
    }

    //  Returns the number of elements in this queue.
    public int size(){
        return length;
    }

    //  Returns a string representation of this queue.
    public String toString(){
        String result = "";
        Node current = front;
        while (current != null){
            result = result + current.toString() + "\n";
            current = current.getNext();
        }
        return result;
    }
}