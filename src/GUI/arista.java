package GUI;

import java.awt.Color;

public class arista {
	
	private int cara1, id1, cara2, id2;	
	
	/*
	 * +---+ +---+
	 * | 1 | | 2 |
	 * +---+ +---+
	 * 
	 * */
	
	public arista(int _cara1, int _id1, int _cara2, int _id2) {
		
		cara1=_cara1;
		id1=_id1;
		//color1=_color1;
		cara2=_cara2;
		id2=_id2;
		//color2=_color2;
		
	}
	
	public int cara1() {
		
		return cara1;
		
	}
	
	public int cara2() {
		
		return cara2;
		
	}
	
	public int id1() {
		
		return id1;
		
	}
	
	public int id2() {
		
		return id2;
		
	}
	
		
	public void setCara1(int cara1) {
		this.cara1 = cara1;
	}

	public void setId1(int id1) {
		this.id1 = id1;
	}

	public void setCara2(int cara2) {
		this.cara2 = cara2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	public Color color1() {
		
		return piezas.getCara(cara1).getColor(id1);
		
	}
	
	public void setColor1(Color color1) {
		piezas.getCara(cara1).setColor(id1, color1);
	}


	public Color color2() {
		
		return piezas.getCara(cara2).getColor(id2);
		
	}
	
	public void setColor2(Color color2) {
		piezas.getCara(cara2).setColor(id2, color2);
	}

	
	public void printInfo() {
		
		System.out.println("[cara1: " + cara1 + " id1: " + id1 + " - cara2: " + cara2 + " id2: " + id2 + "]");
		
	}
}
