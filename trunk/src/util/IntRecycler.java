package src.util;

import java.util.LinkedList;

public class IntRecycler {
	private LinkedList< Integer > pool = new LinkedList< Integer >();
	private int value = 0;

	public void free(int id) {
		pool.push( id );
	}

	public int next() {
		if ( pool.size() > 0 )
			return pool.pop();
		return ++value;
	}

}