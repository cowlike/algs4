import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first = null;
    private Node last = null;

    private class Node {
        Node(final Item item, final Node prev, final Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        Item item;
        Node prev;
        Node next;
    }

    public Deque() {
        // construct an empty deque
    }

    public boolean isEmpty() {
        // is the deque empty?
        return first == null;
    }

    public int size() {
        // return the number of items on the deque
        int size = 0;

        for (Node i = first; i != null; i = i.next) {
            size++;
        }

        return size;
    }

    public void addFirst(Item item) {
        // insert the item at the front
        if (item == null) {
            throw new NullPointerException("Cannot add a null item");
        }
        Node oldFirst = first;
        first = new Node(item, null, oldFirst);
        if (oldFirst == null) {
            last = first;
        }
        else {
            oldFirst.prev = first;
        }
    }

    public void addLast(Item item) {
        // insert the item at the end
        if (item == null) {
            throw new NullPointerException("Cannot add a null item");
        }
        Node oldLast = last;
        last = new Node(item, oldLast, null);
        if (oldLast == null) {
            first = last;
        }
        else {
            oldLast.next = last;
        }
    }

    public Item removeFirst() {
        // delete and return the item at the front
        if (first == null) {
            throw new NoSuchElementException("No element to remove");
        }

        Item retval = first.item;
        first = first.next;
        return retval;
    }

    public Item removeLast() {
        // delete and return the item at the end
        if (last == null) {
            throw new NoSuchElementException("No element to remove");
        }

        Item retval = last.item;
        last = last.prev;
        return retval;
    }

    public Iterator<Item> iterator() {
        // return an iterator over items in order from front to end
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = null;

        public DequeIterator() {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() { /* not supported */
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item retval = current.item;
            current = current.next;
            return retval;
        }
    }
}