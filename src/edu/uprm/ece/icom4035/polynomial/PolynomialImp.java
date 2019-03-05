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
		this.toTerm(string);
	}

	public PolynomialImp() {
		// TODO Auto-generated constructor stub
		this.list = factory.newInstance();
	}


	public PolynomialImp(PolynomialImp poly) {
		// TODO Auto-generated constructor stub
		this.list=poly.list;
	}
	public void toTerm(String poly){
		List<String> stringsDivided = divideByTerm(poly);
		double coef = 0;
		int exp = 0;
		String[] termStorage;

		for(int i = 0; i < stringsDivided.size(); i++){
			if(!stringsDivided.get(i).contains("+")) {
				if(stringsDivided.get(i).contains("x^")){
					termStorage = stringsDivided.get(i).split("x\\^");
					for(int j = 0; j < termStorage.length; j++){
						if(j==0){
							if((termStorage[j].equals("+")))
								coef = 1;
							else if(termStorage[j].equals("-"))
								coef = -1;
							else{
								coef = Double.parseDouble(termStorage[j]);	
							}
						}
						else{
							exp = (int)Double.parseDouble(termStorage[j]);					
						}			
					}				
				}
				else if(stringsDivided.get(i).contains("x")){
					termStorage = stringsDivided.get(i).split("x");
					exp = 1;
					if((termStorage[0].equals("+")))
						coef = 1;
					else if(termStorage[0].equals("-"))
						coef = -1;
					else
						coef = Double.parseDouble(termStorage[0]);
				}
				else{ 
					exp = 0;
					coef = Double.parseDouble(stringsDivided.get(i));
				}
				this.list.add(new TermImp(coef,exp));
			}
		}

	}

	public List<String> divideByTerm(String poly){
		List<String> strings = (List<String>) new ArrayList<String>();

		String[] str = poly.split("(?=[\\+-])");

		for(int i= 0 ; i < str.length ;i++){
			strings.add(str[i]);
		}

		return strings;
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

		Term p1term, p2term;

		while(p1.hasNext() || p2.hasNext()) {
			p1term = p1.next();
			p2term = p2.next();

			if(!p1.hasNext()&& p2.hasNext()) {
				while(p2.hasNext()) {
					poly.list.add(new TermImp(p2term.getCoefficient(), p2term.getExponent()));
					break;
				}

			}
			else if(p1.hasNext()&& !p2.hasNext()){
				while(p1.hasNext()) {
					poly.list.add(new TermImp(p1term.getCoefficient(), p1term.getExponent()));
				}
				break;
			}

			while(p1term.getExponent() > p2term.getExponent()) {
				poly.list.add(new TermImp(p1term.getCoefficient(), p1term.getExponent()));
				break;
			}
			while(p1term.getExponent()==p2term.getExponent()) {
				poly.list.add(new TermImp(p1term.getCoefficient() + p2term.getCoefficient(), p1term.getExponent() ));
				break;

			}
			while(p1term.getExponent() < p2term.getExponent()){
				poly.list.add(new TermImp(p2term.getCoefficient(), p2term.getExponent()));
				break;
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
		Term p1term, p2term;

		while(p1.hasNext() || p2.hasNext()) {
			p1term = p1.next();
			p2term = p2.next();

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
				break;
			}
			while(p1term.getExponent()==p2term.getExponent()) {
				poly.list.add(new TermImp(p1term.getCoefficient() - p2term.getCoefficient(), p1term.getExponent() ));
				break;

			}
			while(p1term.getExponent() < p2term.getExponent()){
				poly.list.add(new TermImp(-p2term.getCoefficient(), p2term.getExponent()));
				break;
			}


		}
		return poly;	}

	@Override
	public Polynomial multiply(Polynomial P2) {
		// TODO Auto-generated method stub
		PolynomialImp poly = new PolynomialImp();
		Iterator<Term> p1 = this.iterator();
		Iterator<Term> p2 = P2.iterator();

		Term p1term, p2term;
		while(p1.hasNext() || p2.hasNext()) {
			p1term = p1.next();
			p2term = p2.next();
			poly.list.add(new TermImp(p1term.getCoefficient() * p1term.getCoefficient(), p1term.getExponent() + p2term.getExponent()));
		}
		return poly;
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
		PolynomialImp poly = new PolynomialImp();
		//List<Term> poly = TermListFactory.newListFactory().newInstance();
		Iterator<Term> iter = this.iterator();
		Term myterm;

		while(iter.hasNext()) {
			myterm = (Term)iter.next();
			poly.list.add(new TermImp(myterm.getCoefficient()*(myterm.getExponent()), myterm.getExponent()-1));
		}
		poly.list.add(new TermImp(1.0,0));
		//String newpoly=poly.toString();
		return new PolynomialImp(poly);
	}

	@Override
	public Polynomial indefiniteIntegral() {
		// TODO Auto-generated method stub
		PolynomialImp poly = new PolynomialImp();
		//List<Term> poly = TermListFactory.newListFactory().newInstance();
		Iterator<Term> iter = this.iterator();
		Term myterm;

		while(iter.hasNext()) {
			myterm = (Term)iter.next();
			poly.list.add(new TermImp(myterm.getCoefficient()/( myterm.getExponent()+1), myterm.getExponent()+1));
		}
		poly.list.add(new TermImp(1.0,0));
		String newpoly=poly.toString();
		return new PolynomialImp(newpoly);
	}

	@Override
	public double definiteIntegral(double a, double b) {
		// TODO Auto-generated method stub
		Polynomial poly = new PolynomialImp();
		poly=this.indefiniteIntegral();
		return (poly.evaluate(b))-(poly.evaluate(a));
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


		while(p1.hasNext() && p2.hasNext()) {
			Term p1term =p1.next();
			Term p2term = p2.next();

			if((p1term.getCoefficient()==p2term.getCoefficient()) && (p1term.getExponent() == p2term.getExponent())) {
				similar=true;
			}
		}
		return similar;
	}
	public String toString() {
		String result = new String();
		for(int i =0; i<this.list.size(); i++) {
			if(this.list.get(i).getExponent() > 1) {
				result+=String.format("%.2f", this.list.get(i).getCoefficient()) +"x"+"^"+Integer.toString(this.list.get(i).getExponent() );
			}
			else if(this.list.get(i).getExponent()==1) {
				result +="+" +String.format("%.2f", this.list.get(i).getCoefficient())+"x";
			}
			else {
				result +="+"+ String.format("%.2f", this.list.get(i).getCoefficient());
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
//parse with 2 digits and parse that takes in the negative
//verify the equals method
//fix tostring
