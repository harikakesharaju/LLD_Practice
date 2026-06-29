package com.practice.VendingMachine;

import java.util.ArrayList;
import java.util.List;

public class Shelf {
	
	List<Item> items;
	int code;
	
	public Shelf(int c) {
		this.code=c;
		items=new ArrayList<>();
	}
	
	void addItem(Item i) {
		items.add(i);
	}
	void removeItem(Item i) {
		items.remove(i);
	}

	void removeFromShelf() {
		items.remove(items.size()-1);
	}
	
	boolean isAvailable() {
		return !items.isEmpty();
	}
}
