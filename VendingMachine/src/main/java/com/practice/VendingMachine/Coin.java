package com.practice.VendingMachine;

public class Coin {
	int value;
	CoinType type;
	
	Coin(int v){
		this.value=v;
		switch(value) {
		case 10:this.type=CoinType.TEN;
		case 50:this.type=CoinType.FIFTY;
		case 100:this.type=CoinType.HUNDRED;
		break;
		}
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public CoinType getType() {
		return type;
	}

	public void setType(CoinType type) {
		this.type = type;
	}
}
