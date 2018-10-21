import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    // private inner class Node
    private class Node{
        Item item;
        Node next;
    }

    // atributes
    private Node first;
    private Node last;
    private int numOfItems;

    // constructor
    public Deque(){
        first = null;
        last = null;
        numOfItems = 0;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return numOfItems;
    }

    public void addFirst(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;

        if(isEmpty())
            last = first;
        numOfItems++;
    }

    public void addLast(Item item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;

        if(isEmpty())
            first = last;
        else
            oldLast.next = last;
        numOfItems++;
    }

    public Item removeFirst(){
        if(isEmpty())
            throw new NoSuchElementException();
        else {
            Item item = first.item;
            first = first.next;
            --numOfItems;
            return item;
        }
    }

    public Item removeLast(){
        if(isEmpty())
            throw new NoSuchElementException();
        else{
            Item item = last.item;
            last = null;
            --numOfItems;
            return item;
        }
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }

    // iterator inner class
    private class DequeIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){
            return current != null;
        }

        public void remove(){
            throw new UnsupportedOperationException();
        }

        public Item next(){
            if (!hasNext())
                throw new NoSuchElementException();
            else{
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    }


    public static void main(String[] args){

    }
}