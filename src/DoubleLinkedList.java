import java.util.Collection;

/**
 * This is not intended to duplicate {@code LinkedList}, it just checks the candidates being able to produce
 * a data structure with defined contract
 * @param <T>
 */


public class DoubleLinkedList<T> implements LinkedList<T>{

    private int size = 0;

    private Node<T> first;
    
    private Node<T> last;

    @Override
    public boolean add(T element){
        final Node<T> l = last;
        final Node<T> newNode = new Node(last, element, null);
        if (last == null){
            first = newNode;
        } else {
            last.next = newNode;
        }
        size++;
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean addAll(Collection<? extends T> source) {

        /*
        stream and lambdas can be used
        {@code source.stream().forEach(el -> add(el));}
         */

        for (T element : source)
            this.add(element);

        return true;
    }


    static class Node <T> {
        T value;
        Node<T> next;
        Node<T> previous;

        Node (Node<T> prev, T value, Node<T> next){
            this.next = next;
            this.previous = prev;
            this.value = value;
        }
    }
}
