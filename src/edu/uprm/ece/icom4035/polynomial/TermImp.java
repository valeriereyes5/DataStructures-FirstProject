package edu.uprm.ece.icom4035.polynomial;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uprm.ece.icom4035.list.ArrayList;

public class TermImp implements Term{

	public Object list;
	public TermImp(double coef, int exp) {
		// TODO Auto-generated constructor stub
	}



	@Override
	public double getCoefficient() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getExponent() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double evaluate(double x) {
		// TODO Auto-generated method stub
		return 0;
	}
	public class TermImpIterator<E> implements Iterator<Term>{
		private int current=0;	
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
		 return current< list.size;
		}

		@Override
		public Term next() {
			// TODO Auto-generated method stub
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			return  list.get(current++);
		}
		public int getcurrent(){
			return current++;
		}
	}
}
