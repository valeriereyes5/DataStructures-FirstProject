package edu.uprm.ece.icom4035.polynomial;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.uprm.ece.icom4035.list.ArrayList;

public class TermImp implements Term{
	private double  coef;
	private int exp;
	public TermImp(double coef, int exp) {
		// TODO Auto-generated constructor stub
	this.coef = coef;
	this.exp = exp;
	
	}
	public TermImp(String Term) {
		
	}

	@Override
	public double getCoefficient() {
		// TODO Auto-generated method stub
		return this.coef;
	}

	@Override
	public int getExponent() {
		// TODO Auto-generated method stub
		return this.exp;
	}

	@Override
	public double evaluate(double x) {
		// TODO Auto-generated method stub
		return this.getCoefficient()*(Math.pow(x, this.getExponent()));
	}
	
}
