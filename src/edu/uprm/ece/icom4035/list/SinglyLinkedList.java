package edu.uprm.ece.icom4035.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.sun.org.apache.regexp.internal.RE;



public class SinglyLinkedList<E> implements List<E> {
	private SNode<E> dumheader; 
	private int length; 

	public SinglyLinkedList() {
		length = 0;
		dumheader= new SNode();
		dumheader.setNext(null);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub

		Iterator<E> iter = new SinglyLinkedListIterator<E>();
		while(iter.hasNext()) {
			iter.next();
		}
		return iter;
	}

	@Override
	public void add(E obj) {
		// TODO Auto-generated method stub
		SNode<E> last = (SNode<E>) this.getLastNode();
		SNode<E> newnode = new SNode();
		newnode.setElement(obj);
		last.setNext(newnode);
		length++;
	}
	public Node<E> getLastNode() throws NodeOutOfBoundsException 
	{
		if (dumheader.getNext() == null)
			throw new NodeOutOfBoundsException("getLastNode(): Empty list."); 
		else { 
			SNode<E> curr = dumheader.getNext(); 
			while (((SNode<E>) curr).getNext() != null)
				curr = curr.getNext(); 
			return curr; 
		}
	}
	//make method that finds the node that is the references

	@Override
	public void add(int index, E obj) {
		// TODO Auto-generated method stub
		SNode<E> currnode = this.findNode(index);
		SNode<E> newnode = new SNode();
		newnode.setElement(obj);
		currnode.setNext(newnode);
		length++;
	}
	private Node<E> findNodePrevTo(Node<E> target) {
		// Pre: target is a node in the list
		if (target == dumheader.getNext()) 
			return null; 
		else { 
			SNode<E> prev = dumheader.getNext(); 
			while (prev != null && prev.getNext() != target) 
				prev = prev.getNext();  
			return prev; 
		}
	}
	public Node<E> getNodeBefore(Node<E> target) 
			throws NodeOutOfBoundsException 
	{
		// Pre: target is a node in the list
		if (target == dumheader.getNext())  
			throw new NodeOutOfBoundsException("getPrevNode(...) : target is the first node."); 
		else 
			return findNodePrevTo(target);
	}
	public void removeNode(Node<E> target) {
		// Pre: target is a node in the list

		if (target == dumheader) {
			// the list is not empty....
			SNode<E> ntr = dumheader; 
			dumheader = dumheader.getNext(); 
			ntr.setNext(null);      // notice that the node keeps its data..
		}
		else { 
			SNode<E> prevNode = (SNode<E>) this.getNodeBefore(target); 
			prevNode.setNext(((SNode<E>) target).getNext()); 
		}
		length--; 
	}
	///////make fucking return boolean
	@Override
	public boolean remove(E e) {
		// TODO Auto-generated method stub
		boolean removed =false;

		SNode<E> curr = this.findNode(e);
		if(this.contains(curr.getElement())) {
			removeNode(curr);
			removed =true;} 
		return removed;
	}
	/////////////////////////////////////////make ther4eturn bolena
	@Override
	public boolean remove(int index) {
		// TODO Auto-generated method stub
		boolean removed =false;
		SNode<E> curr = this.findNode(index);
		if(this.contains(curr.getElement())) {
		removeNode(curr);
		removed =true;}
		return removed;
	}

	@Override
	public int removeAll(Object obj) {
		// TODO Auto-generated method stub
		SNode<E> nodes = dumheader.getNext();
		int counter =0;
		while(nodes.getNext()!=null) {
			if(nodes.getElement().equals(obj)) {
				removeNode(nodes);
				counter++;
			}
		}
		return counter;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return findNode(index).getElement();
	}

	@Override
	public Object set(int index, Object obj) {
		// TODO Auto-generated method stub
		SNode<E> ntr = findNode(index);
		E etr = ntr.getElement()
				;		ntr.setElement((E) obj);
				return etr;
	}

	@Override
	public E first() {
		// TODO Auto-generated method stub

		if ( dumheader.getNext().equals(null)) {
			throw new NodeOutOfBoundsException();
		}		
		// the linked list is not empty....
		return dumheader.getNext().getElement();
	}

	@Override
	public E last() {
		// TODO Auto-generated method stub
		SNode<E> nodes = dumheader;
		while(!nodes.getNext().equals(null)) {
			nodes = nodes.getNext();
		}
		return nodes.getElement();
	}
	//returns first instance of that object in the list
	@Override
	public int firstIndex(Object obj) {
		// TODO Auto-generated method stub
		Node<E> nodes= findNode(obj);
		return findIndex(nodes);
	}
	//return last instance of that object in the list
	@Override
	public int lastIndex(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.length;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return this.length==0;
	}

	@Override
	public boolean contains(Object obj) {
		// TODO Auto-generated method stub
		return findNode(obj)!=null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		dumheader.setNext(null);
	}

	public SNode<E> findNode(int index){
		int counter = -1;
		SNode<E> nodes = new SNode<E>();
		nodes = dumheader;
		while(iterator().hasNext()) {
			nodes.getNext();
			counter++;
			if(counter==index) {
				break;
			}
		}
		return nodes;

	}
	public SNode<E> findNode(Object obj){
		SNode<E> nodes = new SNode<E>();
		nodes = dumheader;
		while(iterator().hasNext()) {
			nodes.getNext();
			if(nodes.getElement().equals(obj)) {
				break;
			}
		}
		return nodes;

	}
	public int findIndex(Node<E> nodes) {
		int index=-1;

		SNode<E> basen = new SNode<E>();
		basen = dumheader;
		while(iterator().hasNext()) {
			basen.getNext();
			index++;
			if(basen.getElement().equals(nodes.getElement())){
				break;
			}

		}

		return index;
	}
	private static class SNode<T> implements Node<T> {
		private T element; 
		private SNode<T> next; 
		public SNode() { 
			element = null; 
			next = null; 
		}
		public SNode(T data, SNode<T> next) { 
			this.element = data; 
			this.next = next; 
		}
		public SNode(T data)  { 
			this.element = data; 
			next = null; 
		}
		public T getElement() {
			return element;
		}
		public void setElement(T data) {
			this.element = data;
		}
		public SNode<T> getNext() {
			return next;
		}
		public void setNext(SNode<T> next) {
			this.next = next;
		}
	}

	public class SinglyLinkedListIterator<E> implements Iterator<E>{
		private int current;
		private boolean canRemove;
		private SinglyLinkedList<E> list;

		public SinglyLinkedListIterator() {
			current=0;
			canRemove =false;
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return current<list.size();
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			else {
				canRemove = true;
				return list.get(current++);
			}
		}
		public void remove(){
			if(!canRemove) {
				throw new IllegalStateException();
			}
			list.remove(current);
			canRemove=false;

		}
	}
}
