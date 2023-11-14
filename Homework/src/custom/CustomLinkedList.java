package custom;

public class CustomLinkedList<T> {
    private Node<T> head;
    private int length = 0;

    public CustomLinkedList() {
        this.head = null;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        length++;
    }

    public int size() {
        return this.length;
    }

    @Override
    public String toString() {

        String list = "{ ";

        Node<T> element = this.head;

        if (element == null)
            return list + " }";

        while (element.next != null) {
            list += String.valueOf(element.data) + " -> ";
            element = element.next;
        }

        list += String.valueOf(element.data);
        return list + " }";
    }
}