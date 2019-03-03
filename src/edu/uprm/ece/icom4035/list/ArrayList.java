package edu.uprm.ece.icom4035.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<E> implements List<E>{
	private static final int INITCAP = 1;
	private static final int CAPTOAR = 1;
	private static final int MAXEMPTYPOS = 2;
	private E[] element;
	private int size;

	@SuppressWarnings("unchecked")
	public ArrayList() {
		element = (E[]) new Object[INITCAP];
		size=0;
	}
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		Iterator<E> iter = new ArrayListIterator<E>();
		while(iter.hasNext()) {
			iter.next();
		}
		return iter;
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
		if(this.size == element.length) {this.changeCapacity(CAPTOAR);}
		element[size] = (E) obj;
		this.size++;
	}

	//creates new array thats bigger than old array.
	public void changeCapacity(int change) {
		int newCapacity = element.length + change;
		E[] newElement = (E[]) new Object[newCapacity];
		for (int i=0; i<size ; i++) {
			newElement[i]=element[i];
			element[i]=null;
		}
		element=newElement;
	}
	@Override
	public void add(int index, Object obj) {
		// TODO Auto-generated method stub
		if(index<0 || index>this.size) {//maybe try catch
		}
		if(this.size==element.length) {this.changeCapacity(CAPTOAR);}
		moveDataOnePositionTR(index, size-1);
		element[index]=(E) obj;
		size++;

	}

	@Override
	public boolean remove(Object obj) {
		// TODO Auto-generated method stub
		


		//*******************REVIEW************************//
		
		
		int index = FindFirstCopy(obj);
		if(index<0|| index>=size) {
			throw new IndexOutOfBoundsException();
		}
		boolean isremoved=false;
		E e = element[index];
		moveDataOnePositionTL(index+1, size-1);
		if(MAXEMPTYPOS<=(element.length - this.size)) {
			this.changeCapacity(-CAPTOAR);
		}
		isremoved=true;
		this.size--;
		return isremoved;
	}

	@Override
	public boolean remove(int index) {
		// TODO Auto-generated method stub

		if(index<0|| index>=size) {
			throw new IndexOutOfBoundsException();
		}
		boolean isremoved=false;
		E e = element[index];
		moveDataOnePositionTL(index+1, size-1);
		if(MAXEMPTYPOS<=(element.length - this.size)) {
			this.changeCapacity(-CAPTOAR);
		}
		isremoved=true;
		this.size--;
		return isremoved;
	}

	@Override
	public int removeAll(Object obj) {
		// TODO Auto-generated method stub
		int counter =0;
		for(int i=0; i<this.size; i++) {
			if(obj.equals(element[i])){
			this.remove(i);
			counter++;
			}
			this.size = this.size -counter;
		}
		return counter;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		if(index>this.size || index<0) {throw new IndexOutOfBoundsException();}
		return element[index];
	}

	@Override
	public Object set(int index, Object obj) {
		// TODO Auto-generated method stub
		if(index>=size||index<0) {throw new IndexOutOfBoundsException();}
		element[index]=(E) obj;
		return obj;
	}

	@Override
	public E first() {
		// TODO Auto-generated method stub

		return element[0];
	}

	@Override
	public E last() {
		// TODO Auto-generated method stub
		return element[size-1];
	}

	@Override
	public int firstIndex(Object obj) {
		// TODO Auto-generated method stub
		int index = FindFirstInst(obj);
		return index;
	}

	@Override
	public int lastIndex(Object obj) {
		// TODO Auto-generated method stub
		int index = FindLastInst(obj);
		return index;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size==0;
	}

	@Override
	public boolean contains(Object obj) {
		// TODO Auto-generated method stub
		boolean contains = false;
		for(int i = 0;i<size; i++) {
			if(element[i].equals(obj)) {
				contains = true;
				break;
			}
		}
		return contains;
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for(int i=0; i<size; i++) {
			element[i]=null;
			size--;
		}

	}
	//finds the index where the obj is at 
	public int FindFirstInst(Object obj){
		int index = 0;
		for(int i = 0;i<size; i++) {
			if(element[i].equals(obj)) {
				index = i;
				break;
			}

		}
		return index;
	}
	public int FindFirstCopy(Object obj) {
		int index = 0;
		int counter = 0;
		for(int i = 0;i<size; i++) {
			if(element[i].equals(obj)) {
				index = i;
				counter++;
			if(counter==2) {
				break;
			}
			}

		}
		return index;
	}
	public int FindLastInst(Object Obj) {
		int index= 0;
		for(int i=0; i<size; i++) {
			if(element[i].equals(Obj)) {
				index=i;
			}
		}
		return index;


	}
	private void moveDataOnePositionTR(int low, int sup) {
		for(int pos = sup; pos>=low; pos--) {
			element[pos-1]=element[pos];

		}
	}
	private void moveDataOnePositionTL(int low, int sup) {
		for(int pos = low; pos>=sup; pos++) {
			element[pos-1]=element[pos];

		}
	}
	
	public class ArrayListIterator<E> implements Iterator<E>{
		private int current;
		private boolean canRemove;
		private ArrayList<E> list;
		
		public ArrayListIterator() {
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
