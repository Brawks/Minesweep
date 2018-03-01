// Author: Samuel Saint-Fleur, Aden Li
// Student number: 300008314, 300022628
// Course: ITI 1121-C
// Assignment: 2
// Question: ArrayStack

/**
* This is a GenericArrayStack.
* @author Samuel Saint-Fleur, Aden Li
*/
public class GenericArrayStack<E> implements Stack<E> {
   
   // ADD YOUR INSTANCE VARIABLES HERE
    private E[] elems;
    private int top;

   // Constructor
    @SuppressWarnings("unchecked")
    public GenericArrayStack( int capacity ) {
        
    // ADD YOU CODE HERE
        elems = (E[]) new Object[capacity];
        top = 0;
    }

    // Returns true if this ArrayStack is empty
    public boolean isEmpty() {
        
    // ADD YOU CODE HERE
        return top == 0;
    }

    public void push( E elem ) {
        
    // ADD YOU CODE HERE
        elems[top] = elem;
        top++;
    }
    public E pop() {
        
    // ADD YOU CODE HERE
        if (isEmpty()) {
            return null;
        } 
        else {
        E tmp = elems[top-1];
        elems[top-1] = null;
        top--;
        return tmp;
        }
    }

    public E peek() {
        
    // ADD YOU CODE HERE
        return elems[top-1];
    }
}
