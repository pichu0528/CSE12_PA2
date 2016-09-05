/**
* Assignment 2                List12.java             Due: 26 April 2013
* Login in: cs12sbd
**/

/**
* Class List12 - Class header comment
* This class basically contains the List<E> interface in the java.util
* package, which requires 25 different public instance methods. However, 
* for this assignment I only need to implement 9 out of 25 methods. I define
* each of the rest of the methods by throwing an UnsupportedOperationException.
* From what I leard in class, I finished this assignment only with nodes. 
* @author Pin Chu cs12sbd
*/


import java.util.*;

public class List12<E> implements java.util.List<E>
{
  // Declare the head, previous, next, selected nodes.
  private Node<E> head, previous, next, selected;
  // Boolean flag
  private boolean adding;
  private int size;
  
  
  // The structure of a node in a singly lined list.
  private static class Node<T> 
  {
    private T         data;  // The data field
    private Node<T> next;  // Link to succesor
    
    // Create an empty <tt>Node</tt> object.
    public Node()
    {
      this.data = null;
      this.next = null;
    }
    /* Create an <tt>Node</tt> that stores <tt>theData</tt> 
     * and whose successor is <tt>theSuccessor</tt>.
     * @param theData the data to store in this node
     * @param theSuccessor this node's successor
     */
    public Node(T theData, Node<T> theSuccessor)
    {
      this.data = theData;
      this.next = theSuccessor;
    }
    
    // Successor accessor
    public Node<T> getSuccessor()
    {
      return this.next;
    }
    
    // Successor mutator
    public void setSuccessor(Node<T> n)
    {
      this.next = n;
    }
    
    // Data accessor
    public T getData()
    {
      return this.data;
    }
    
    // Data mutator
    public void setData(T e)
    {
      this.data = e;
    }
  }
 
/**  
  public SinglyLinkdedList()
  {
    head = null;
    previous = null;
    next = null;
    selected = null;
    size = 0;
  }
**/
  // Showing the nodes in class List12.
  public List12()
  {
    head = new Node<E>();
    previous = new Node<E>();
    next = new Node<E>();
    selected = new Node<E>();
  }

  /* Appends the specified element to the end of the node.
   * @param o number that is going to be added
   */
  public boolean add(E o)
  {
    // Declare a temporary node.
    Node<E> aNode = new Node<E>();
    // Set data to the pass in param
    aNode.setData(o);
    // Check if there is any nodes.
    if (size == 0)
    {
      selected = head;
    }
    // If there isn't a dummy.
    while(selected.getSuccessor() != null)
    {
      selected = selected.getSuccessor();
    }
    // Add the temporary node.
    selected.setSuccessor(aNode);
    size++;    
    
    // Check if the selected node is the same as the temporary node
    if(selected.getSuccessor() == aNode)
    {
      adding = true;
    }
    else
    {
      adding = false;
    }
    return adding;
  }
  
  /* Inserts the specified element at the specified position in this list.
   * @param index
   * @param element
   */ 
  public void add(int index, E element)
  {
    /**
    if (index < 0 || index > size) 
    {
      throw new IOException();
    }*/
    // Advance cursor to point to node just before insertion point.
    Node<E> cursor = head;
    while(--index > 0) cursor = cursor.getSuccessor();
    // Create new node.
    Node<E> newnode = new Node<E>(element, null);
    // Set the new node's next to be same as cursor's next.
    newnode.setSuccessor(cursor.getSuccessor());
    // Set cursor's next to point to the new node.
    cursor.setSuccessor(newnode);
    // Increment size by 1.
    size++;
  }
  
  /* Returns true if the node contains the specified object.
   * @param o
   */ 
  public boolean contains(Object o)
  {
   Node<E> bNode = new Node<E>();
   bNode = head;
   //boolean containing = false;
   // Boolean flag
   boolean containing = true;
   // When the next of the temporary node is not empty(or dummy).
   while(bNode.getSuccessor() != null)
   {
     bNode = bNode.getSuccessor();
     // If the data sorted in the temporary node doesn't equal to the input.
     if (bNode.getData() != o)
     {
       containing = false;
     }
   }
   return containing;
  }
  
  /* Returns the element at the specified position in the node.
   * @param index
   */ 
  public E get(int index)
  {
    Node<E> cNode = new Node<E>();
    cNode = head;
    int i = 0;
    while(i < index+1 && cNode.getSuccessor() != null)
    {
      cNode = cNode.getSuccessor();
      i++;
    }
    return cNode.getData();

  }

  /* Returns an iterator over the elements in this deque in proper sequence.
   * The elements will be returned in order from the first(head) to the 
   * last.
   */ 
  public Iterator<E> iterator()
  {
    return new LLIter();
  }
  
  /* Removes the first occurence of the specified element from this deque.
   * @param o
   */ 
  public boolean remove(Object o)
  {
    // Temporary node.
    Node<E> dNode = new Node<E>();
    // Boolean flag.
    boolean removing = true;
    dNode = head;
    previous = head;
    
    // When the next of the temporary node is not empty(or dummy).
    int i = 0;
    while(dNode.getSuccessor() != null)
    {
      // Spot the previous node.
      if(i > 0)
      {
        previous = previous.getSuccessor();
      }
      dNode = dNode.getSuccessor();
      
      /* If the element in the temporary node doesn't not equal to the input,
       * than the boolean flag is false.
       */ 
      if(dNode.getData() != o)
      {
        removing = false;
      }
      else
      {
        previous.setSuccessor(dNode.getSuccessor());
        removing = true;
        size--;
      }
      i++;
    }
    return removing;
  }
  
  /* Removes the element at the specified position in the node.
   * @param index
   */ 
  public E remove(int index)
  {
    Node<E> cursor = head;
    Node<E> target = cursor.getSuccessor();
    E element = target.getData();
    cursor.setSuccessor(target.getSuccessor());
    size--;
    return element;
  }
  
  /* Sets element at the specified position.
   * @param index 
   * @param element
   */ 
  public E set(int index, E element)
  {
    // Temporary node.
    Node<E> eNode = new Node<E>();
    eNode = head;
    int i = 0;
    
    /* When i last than index and the next of the temporary node is not 
     * empty(or dummy), set the temporary node to next.
     */ 
    while(i <= index && eNode.getSuccessor() != null)
    {
      eNode = eNode.getSuccessor();
      i++;
    }
    E object = eNode.getData();
    eNode.setData(element);
    
    return object;
  }
  
  /* Returns the total size.
   */ 
  public int size()
  {
    return size;
  }
  
  /* A private class for the iterator method above.
   */ 
  private class LLIter implements java.util.Iterator<E>
  {
    private Node<E> nextNode;
    private Node<E> lastNode;
    private Node<E> predNode;
    
    /* Declaration.
   */ 
    public LLIter()
    {
      lastNode = (Node<E>) head;
      nextNode = (Node<E>) head.getSuccessor();
    }
    
    /* Returns a boolean. 
   */ 
    public boolean hasNext()
    {
      return nextNode != null;
    }
    
    /* Returns the element.
   */ 
    public E next()
    {
      if(nextNode == null) throw new NoSuchElementException();
      predNode = lastNode;
      lastNode = nextNode;
      nextNode = lastNode.getSuccessor();
      
      return lastNode.data;
    }
    
    /* Remove method in the iterator method
   */ 
    public void remove()
    {
      if (lastNode == null) throw new IllegalStateException();
      predNode.setSuccessor(nextNode);
      size--;
      lastNode = null;
    }
    
    public java.util.Iterator<E> iterator()
    {
      return new LLIter();
    }
  }
  
  
  
///////////////NOT USED METHODS//////////////////
  /*@ throws UnsupportedOperationException
   */
  public boolean addAll
    (Collection<? extends E>c) throws UnsupportedOperationException
  {throw new UnsupportedOperationException();}
  public boolean addAll
    (int index, Collection<? extends E> c) throws UnsupportedOperationException
  {throw new UnsupportedOperationException();}
  public void clear() throws UnsupportedOperationException
  {throw new UnsupportedOperationException();}
  public boolean containsAll
    (Collection<?> c) throws UnsupportedOperationException
  {throw new UnsupportedOperationException();}
  public boolean equals(Object o) throws UnsupportedOperationException
  {throw new UnsupportedOperationException();}
  public int hashCode() throws UnsupportedOperationException
  {throw new UnsupportedOperationException();}
  public int indexOf(Object o) throws UnsupportedOperationException
  {throw new UnsupportedOperationException();}
  public boolean isEmpty() throws UnsupportedOperationException
  {throw new UnsupportedOperationException();}
  public int lastIndexOf(Object o) throws UnsupportedOperationException
  {throw new UnsupportedOperationException();}
  public ListIterator<E> listIterator() throws UnsupportedOperationException
  {throw new UnsupportedOperationException();}
  public ListIterator<E> listIterator
    (int index) throws UnsupportedOperationException
  {throw new UnsupportedOperationException();}
  public boolean removeAll
    (Collection<?> c) throws UnsupportedOperationException
  {throw new UnsupportedOperationException();}
  public boolean retainAll(Collection<?> c) throws UnsupportedOperationException
  {throw new UnsupportedOperationException();}
  public List<E> subList
    (int fromIndex, int toIndex) throws UnsupportedOperationException
  {throw new UnsupportedOperationException();}
  public Object[] toArray() throws UnsupportedOperationException
  {throw new UnsupportedOperationException();}
  public <T> T[] toArray(T[] a) throws UnsupportedOperationException
  {throw new UnsupportedOperationException();} 

}
