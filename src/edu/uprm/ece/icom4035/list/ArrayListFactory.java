package edu.uprm.ece.icom4035.list;

public class ArrayListFactory<E>  implements ListFactory<E>{

	@Override
	public List<E> newInstance() {
		// TODO Auto-generated method stub
		return new ArrayList<E>();
	}

}
