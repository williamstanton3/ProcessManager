public class ReadyQueue {

    private Node<Process> header;
    private Node<Process> trailer;
    private int size;

    // creates an empty ready queue
    public ReadyQueue() {
        header = new Node<>(null);
        trailer = new Node<>(null);
        header.next = trailer;
        trailer.prev = header;
        size = 0;
    }

    // creates a new ready queue with one process
    public ReadyQueue(Process initial) {
        header = new Node<>(null);
        trailer = new Node<>(null);
        Node<Process> p1 = new Node<>(initial);
        header.next = p1;
        trailer.prev = p1;
        p1.prev = header;
        p1.next = trailer;
        size = 1;
    }
    public int getNumProcesses() {
        return size;
    }
    public void addProcess(Process c) {
        Node<Process> newNode = new Node<>(c);
        // if the queue is empty
        if (header.next == null) {
            header.next = newNode;
            trailer.prev = newNode;
        }
        else {
            Node<Process> successor = trailer.prev;
            successor.next = newNode;
            trailer.prev = newNode;
            newNode.prev = successor;
            newNode.next = trailer;
        }
        size++;
    }
    public Process getFirst() {
        return header.next.getElement();
    }
    public Process.Status removeProcess() throws NoSuchMethodException {
        if (header.next == trailer) {
            throw new NoSuchMethodException();
        }
        Node<Process> first = header.next;
        Node<Process> second = first.next;
        header.next = second;
        second.prev = header;
        size--;
        return first.getElement().getState();
    }

    // move the node at the front of the queue (
    public void contextSwitch() {
        Node<Process> front = header.next;
        Node<Process> second = front.next;
        Node<Process> last = trailer.prev;
        last.next = front;
        front.prev = last;
        trailer.prev = front;
        front.next = trailer;
        header.next = second;
        second.prev = header;
    }

    public void terminate(String name) {
        Node<Process> current = header.next;
        while (current.next != trailer) {
            if (current.getElement().getName().equals(name)) {
                current.getElement().setState(Process.Status.TERMINATED);
                break;
            }
            current = current.next;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Printing ready queue: " + size + " processes" + "\n");
        Node<Process> curr = header.next;
        while (curr != trailer) {
            sb.append(curr.getElement().toString() + "\n");
            curr = curr.next;
        }

        return sb.toString();
    }


    // example method using private inner node class
    public void method() {
        Node<String> snode = new Node<>("hi");
        Node<String> nnode = new Node<>("bye");
        snode.next = nnode;
        nnode.prev = snode;
        snode.value = "HI";
        System.out.println(snode.next.prev);  // prints: HI created 0 millis ago
        System.out.println(snode.next.prev.value); // prints: HI
    }

    public Process thirdFromBack() {
        return trailer.prev.prev.prev.getElement();
    }


    private class Node<T> {
        private T value;
        private long creationTime;
        private Node<T> next;
        private Node<T> prev;

        public Node(T value) {
            this.value = value;
            creationTime = System.currentTimeMillis();
        }
        public T getElement() {
            return value;
        }
    }

}
