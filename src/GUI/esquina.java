package GUI;

import java.awt.Color;

public class esquina {

	private int cara1, cara2, cara3, id1, id2, id3;
	
	/*
	 * +---+ +---+
	 * | 2 | | 3 |
	 * +---+ +---+
	 *    +---+
	 *    | 1 |
	 *    +---+
	 * 
	 * */
	
	public esquina(int _cara1, int _id1, int _cara2, int _id2, int _cara3, int _id3) {
		
		cara1=_cara1;
		cara2=_cara2;
		cara3=_cara3;
		id1=_id1;
		id2=_id2;
		id3=_id3;
		
	}

	public int cara1() {
		return cara1;
	}

	public void setCara1(int cara1) {
		this.cara1 = cara1;
	}

	public int cara2() {
		return cara2;
	}

	public void setCara2(int cara2) {
		this.cara2 = cara2;
	}

	public int cara3() {
		return cara3;
	}

	public void setCara3(int cara3) {
		this.cara3 = cara3;
	}

	public int id1() {
		return id1;
	}

	public void setId1(int id1) {
		this.id1 = id1;
	}

	public int id2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	public int id3() {
		return id3;
	}

	public void setId3(int id3) {
		this.id3 = id3;
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

	public Color color3() {
		return piezas.getCara(cara3).getColor(id3);
	}

	public void setColor3(Color color3) {
		piezas.getCara(cara3).setColor(id3, color3);
	}
	
	
	
}
