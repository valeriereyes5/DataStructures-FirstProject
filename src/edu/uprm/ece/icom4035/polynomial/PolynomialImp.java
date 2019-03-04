package edu.uprm.ece.icom4035.polynomial;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uprm.ece.icom4035.list.ArrayList;
import edu.uprm.ece.icom4035.list.List;
import edu.uprm.ece.icom4035.list.ListFactory;
import edu.uprm.ece.icom4035.list.ArrayList.ArrayListIterator;

public class PolynomialImp extends TermListFactory implements Polynomial {
	private List<Term> list;
	private ListFactory<Term> factory = TermListFactory.newListFactory();



	public PolynomialImp(String string) {
		// TODO Auto-generated constructor stub
		this.list = factory.newInstance();
		this.parseTerms(string);
	}

	public PolynomialImp() {
		// TODO Auto-generated constructor stub
		this.list = factory.newInstance();
	}

	private void parseTerms(String string) {
		// TODO Auto-generated method stub
		String [] ply = string.split("\\+");
		double Coef = 0;
		int Exp = 0;
		for(int i =0; i<ply.length; i++) {
			if(ply[i].contains("x") && ply[i].contains("^")) {

				Coef = Character.getNumericValue(ply[i].charAt(ply[i].length() - 4));
				Exp = Character.getNumericValue(ply[i].charAt(ply[i].length()-1));

			}
			if(ply[i].contains("x") && !ply[i].contains("^")) {
				Coef = 1.00;
				Exp=1;
			}

			if(!ply[i].contains("x") && !ply[i].contains("^")) {

				Coef = Double.parseDouble(ply[i]);;

				Exp = 0;

			}

			this.list.add(new TermImp(Coef,Exp));
		}
	}

	@Override
	public Iterator<Term> iterator() {
		// TODO Auto-generated method stub

		return new PolynomialIterator<Term>();
	}

	@Override
	public Polynomial add(Polynomial P2) {
		// TODO Auto-generated method stub
		//ver cual tiene el elemento mayor or if they the same
		//puess todo hasta 
		PolynomialImp poly = new PolynomialImp();
		Iterator<Term> p2 = P2.iterator();
		Iterator<Term> p1 = this.iterator();

		Term p1term =p1.next();
		Term p2term = p2.next();

		while(p1.hasNext() || p2.hasNext()) {
			if(!p1.hasNext()&& p2.hasNext()) {
				while(p2.hasNext()) {
					poly.list.add(new TermImp(p2term.getCoefficient(), p2term.getExponent()));
				}
				break;
			}
			else if(p1.hasNext()&& !p2.hasNext()){
				while(p1.hasNext()) {
					poly.list.add(new TermImp(p1term.getCoefficient(), p1term.getExponent()));
				}
				break;
			}

			while(p1term.getExponent() > p2term.getExponent()) {
				poly.list.add(new TermImp(p1term.getCoefficient(), p1term.getExponent()));
				p1term=p1.next();
			}
			while(p1term.getExponent()==p2term.getExponent()) {
				poly.list.add(new TermImp(p1term.getCoefficient() + p2term.getCoefficient(), p1term.getExponent() ));

				p1term=p1.next();
				p2term=p2.next();


			}
			while(p1term.getExponent() < p2term.getExponent()){
				poly.list.add(new TermImp(p2term.getCoefficient(), p2term.getExponent()));
				p2term=p2.next();
			}


		}
		return poly;
	}


	@Override
	public Polynomial subtract(Polynomial P2) {
		// TODO Auto-generated method stub
		PolynomialImp poly = new PolynomialImp();
		Iterator<Term> p2 = P2.iterator();
		Iterator<Term> p1 = this.iterator();

		Term p1term =p1.next();
		Term p2term = p2.next();

		while(p1.hasNext() || p2.hasNext()) {
			if(!p1.hasNext()&& p2.hasNext()) {
				while(p2.hasNext()) {
					poly.list.add(new TermImp(p2term.getCoefficient(), p2term.getExponent()));
				}
				break;
			}
			else if(p1.hasNext()&& !p2.hasNext()){
				while(p1.hasNext()) {
					poly.list.add(new TermImp(p1term.getCoefficient(), p1term.getExponent()));
				}
				break;
			}

			while(p1term.getExponent() > p2term.getExponent()) {
				poly.list.add(new TermImp(p1term.getCoefficient(), p1term.getExponent()));
				p1term=p1.next();
			}
			while(p1term.getExponent()==p2term.getExponent()) {
				poly.list.add(new TermImp(p1term.getCoefficient() - p2term.getCoefficient(), p1term.getExponent() ));

				p1term=p1.next();
				p2term=p2.next();


			}
			while(p1term.getExponent() < p2term.getExponent()){
				poly.list.add(new TermImp(p2term.getCoefficient(), p2term.getExponent()));
				p2term=p2.next();
			}


		}
		return poly;	}

	@Override
	public Polynomial multiply(Polynomial P2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Polynomial multiply(double c) {
		// TODO Auto-generated method stub
		PolynomialImp poly = new PolynomialImp();
		Iterator<Term> iter = this.iterator();
		Term myterm;
		while(iter.hasNext()) {
			myterm = (Term)iter.next();
			poly.list.add(new TermImp(myterm.getCoefficient() * c, myterm.getExponent()));
		}
		return poly;
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
		Iterator<Term> p1 = this.iterator();

		Term p1term =p1.next();
		return p1term.getExponent();
	}

	@Override
	public double evaluate(double x) {
		// TODO Auto-generated method stub
		double result=0;
		Iterator<Term> iter = this.iterator();
		Term myterm;
		while(iter.hasNext()) {
			myterm = (Term)iter.next();
			result =result + (myterm.getCoefficient()*(Math.pow(x, myterm.getExponent())));
		}
		return result;
	}

	@Override
	public boolean equals(Polynomial P) {
		// TODO Auto-generated method stub
		boolean similar = false;
		Iterator<Term> p2 = P.iterator();
		Iterator<Term> p1 = this.iterator();

		Term p1term =p1.next();
		Term p2term = p2.next();

		while(p1.hasNext() && p2.hasNext()) {
			if((p1term.getCoefficient()==p2term.getCoefficient()) && (p1term.getExponent() == p2term.getExponent())) {
				similar=true;
			}
		}
		return similar;
	}
	public String toString() {
		String result = new String();
		for(int i =0; i<this.list.size(); i++) {
			if(this.list.get(i).getCoefficient() > 1) {
				result+= Double.toString(this.list.get(i).getCoefficient())+"x"+"^"+Integer.toString(this.list.get(i).getExponent());
			}
			else if(this.list.get(i).getCoefficient()==1) {
				result += Double.toString(this.list.get(i).getCoefficient())+"x";
			}
			else {
				result += Double.toString(this.list.get(i).getCoefficient());
			}
		}
		return result;

	}
	public class PolynomialIterator<E> implements Iterator<Term>{
		private int current =0;
		private int size = list.size();

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return this.current<this.size;
		}

		@Override
		public Term next() {
			// TODO Auto-generated method stub
			if(!this.hasNext()) {
				throw new NoSuchElementException();
			}
			Term t = (Term) list.get(current);
			this.current++;
			return t;

		}

	}
}
//from evaluate method: case  where a coefficient is negative
//fir iterartor class
//review tostring
//review degree method for test 2
//parse terms for coeficients like 12 etc
//poner negativos en el substarct
