import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items = null;
    private int N = 0;

    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        // construct an empty randomized queue
        items = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        // is the queue empty?
        return N == 0;
    }

    public int size() {
        // return the number of items on the queue
        return N;
    }

    public void enqueue(Item item) {
        // add the item
        if (item == null) {
            throw new NullPointerException("Cannot add a null item");
        }
        
        if (N == items.length)
            resize(2 * items.length);
        items[N++] = item;
    }

    public Item dequeue() {
        // delete and return a random item
        if (isEmpty()) {
            throw new NoSuchElementException("No element to remove");
        }

        // find random item and exchange with last item
        int index = StdRandom.uniform(N);
        exchange(items, index, N-1);

        Item item = items[--N];
        items[N] = null;
        if (N > 0 && N == items.length / 4)
            resize(items.length / 2);
        return item;
    }

    public Item sample() {
        // return (but do not delete) a random item
        if (isEmpty()) {
            throw new NoSuchElementException("No element to sample");
        }

        return items[StdRandom.uniform(N)];
    }

    public Iterator<Item> iterator() {
        // return an independent iterator over items in random order
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] _items = null;
        private int _N = 0;

        @SuppressWarnings("unchecked")
        public RandomizedQueueIterator() {
            _items = (Item[])new Object[N];
            for (int i = 0; i < N; i++) {
                _items[i] = items[i];
            }
            StdRandom.shuffle(_items);
        }
        public boolean hasNext() {
            return _N < N-1;
        }

        public void remove() { /* not supported */
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return _items[_N++];
        }
    }
        
    private void exchange(Item[] a, int i, int j) {
        Item swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
    private void resize(int capacity) {
        @SuppressWarnings("unchecked")
        Item[] copy = (Item[]) new Object[capacity];

        for (int i = 0; i < N; i++)
            copy[i] = items[i];
        items = copy;
    }
}