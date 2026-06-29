package com.practice.VendingMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VendingMachineSystem {
	
	private List<Shelf> shelves;
	private List<Coin> coinList;
	private VendingMachineState state;
	
	public VendingMachineSystem() {
		this.state=new IdleState();
		this.shelves=new ArrayList<>(5);
		this.coinList=new ArrayList<>();
	}

	public List<Shelf> getShelves() {
		return shelves;
	}

	public void setShelves(List<Shelf> shelves) {
		this.shelves = shelves;
	}

	public List<Coin> getCoinList() {
		return coinList;
	}

	public void setCoinList(List<Coin> coinList) {
		this.coinList = coinList;
	}

	public VendingMachineState getState() {
		return state;
	}

	public void setState(VendingMachineState state) {
		this.state = state;
	}
	
	void inputCoins(List<Integer> l) {
		List<Coin> c=l.stream().map(val->new Coin(val)).collect(Collectors.toList());
		coinList.addAll(c);
		
	}
	
	void stockShelves(int n) {
	    shelves.clear(); // reset shelves
	    for (int i = 0; i < 4; i++) {
	        Shelf sh = new Shelf(i + 1);
	        for (int t = 0; t < n; t++) {
	            Item it = new Item();
	            switch (i) {
	                case 0:
	                    it.setType(ItemType.DRINKS);
	                    it.setPrice(50);
	                    break;
	                case 1:
	                    it.setType(ItemType.CHIPS);
	                    it.setPrice(30);
	                    break;
	                case 2:
	                    it.setType(ItemType.COOKIES);
	                    it.setPrice(40);
	                    break;
	                case 3:
	                    it.setType(ItemType.DRINKS);
	                    it.setPrice(100);
	                    break;
	            }
	            sh.addItem(it);
	        }
	        shelves.add(sh);
	    }
	}

	
}
