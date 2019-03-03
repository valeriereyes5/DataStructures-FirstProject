package edu.uprm.ece.icom4035.polynomial;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uprm.ece.icom4035.list.ArrayList;
import edu.uprm.ece.icom4035.list.List;
import edu.uprm.ece.icom4035.list.ListFactory;

public class PolynomialImp extends TermListFactory implements Polynomial {
	private List<Term> list;
	private ListFactory<Term> factory = TermListFactory.newListFactory();
	
	
	
	public PolynomialImp(String string) {
		// TODO Auto-generated constructor stub
		this.list = factory.newInstance();
		this.parseTerms(string);
	}

	private void parseTerms(String string) {
		// TODO Auto-generated method stub
		String [] ply = string.split("\\+");
		double Coef = 0;
		int Exp = 0;
		for(int i =0; i<ply.length; i++) {
			if(ply[i].contains("x") && ply[i].contains("^")) {

				Coef = Double.parseDouble(ply[i]);
				Exp = Character.getNumericValue(ply[i].charAt(ply[i].length()-1));
				
			}
			if(ply[i].contains("x") && !ply[i].contains("^")) {
				Coef = Double.parseDouble(ply[i]);
			}
			if(ply[i].contains("^") && !ply[i].contains("x")) {
				Exp = Character.getNumericValue(ply[i].charAt(ply[i].length()-1));
			}
			if(!ply[i].contains("x") && !ply[i].contains("^")) {

				Coef = 1;
				Exp = 1;
				
			}
			this.list.add(new TermImp(Coef,Exp));
		}
	}

	@Override
	public Iterator<Term> iterator() {
		// TODO Auto-generated method stub
		return list.iterator();
	}

	@Override
	public Polynomial add(Polynomial P2) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Polynomial subtract(Polynomial P2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polynomial multiply(Polynomial P2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polynomial multiply(double c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polynomial derivative() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polynomial indefiniteIntegral() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double definiteIntegral(double a, double b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int degree() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double evaluate(double x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Polynomial P) {
		// TODO Auto-generated method stub
		return false;
	}
	public class PolynomialIterator<E> implements Iterator<Term>{
		private int current;
		private int size = list.size();
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
		 return current<this.size;
		}

		@Override
		public Term next() {
			// TODO Auto-generated method stub
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			Term t = (Term) list.get(current);
			this.current =+2;
			return t;

		}
		
	}
}
