public class BinaryHeap {

    private int[] heap;
    private int size;

    /**
     * Constructor
     */
    public BinaryHeap() {
        heap = new int[10];
        size = 0;
    }

    /**
     * Used for testing
     *
     * @param args
     */
    public static void main(String[] args) {
        BinaryHeap bin = new BinaryHeap();
        for (int i = 0; i < 20; i++) {
            bin.add((int) (Math.random() * 100));
        }
        bin.print();

        bin.remove();
        bin.print();
    }

    /**
     * Adds a new number at the last point in the array and then shifts it forward
     * if it is smaller than its parent
     *
     * @param add The number to add to the array
     */
    public void add(int add) {
        //Checks if it needs to grow the array
        if (size == heap.length) {
            growArray();
        }
        heap[size] = add;
        int loc = size;
        //Repeatedly shifts the added number forward in the array by swapping with parent
        while (loc > 0 && heap[loc] < heap[(loc - 1) / 2]) {
            swap(loc, (loc - 1) / 2);
            loc = (loc - 1) / 2;
        }
        size++;
    }

    /**
     * Removes the first number from the array by swapping it with the last number
     * than decriminting the size by 1. Repeatedly swaps the first number with its
     * lowest child if the child is smaller
     *
     * @return the removed number
     * @throws NullPointerException if the array is empty
     */
    public int remove() throws NullPointerException {
        if (size == 0) {
            throw new NullPointerException();
        } else {
            swap(0, size - 1);
            size--;
            //Calls the function to shift the parent and child over
            if (heap[0] > heap[1] || heap[0] > heap[2]) {
                shiftDown();
            }
        }
        return heap[size];
    }

    /**
     * Repeatedly swaps the child and parent if the child is smaller
     */
    public void shiftDown() {
        int loc = 0;
        while (((2 * loc) + 2 <= size) && (heap[loc] > heap[(2 * loc) + 1] || heap[loc] > heap[(2 * loc) + 2])) {
            if ((2 * loc) + 2 >= size) {
                swap(loc, (2 * loc) + 1);
                loc = (2 * loc) + 1;
            } else if (heap[(2 * loc) + 1] < heap[(2 * loc) + 2]) {
                swap(loc, (2 * loc) + 1);
                loc = (2 * loc) + 1;
            } else {
                swap(loc, (2 * loc) + 2);
                loc = (2 * loc) + 2;
            }
        }
    }

    /**
     * Swaps 2 numbers in an array
     *
     * @param a the index of the first number
     * @param b the index of the second number
     */
    public void swap(int a, int b) {
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    /**
     * Prints the array
     */
    public void print() {
        String temp = "";
        for (int i = 0; i < size; i++) {
            temp += heap[i] + ", ";
        }
        System.out.println(temp);
    }

    /**
     * Doubles the size of the array
     */
    public void growArray() {
        int[] temp = new int[2 * heap.length];
        for (int i = 0; i < heap.length; i++) {
            temp[i] = heap[i];
        }
        heap = temp;
    }
}
