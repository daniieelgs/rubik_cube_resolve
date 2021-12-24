package GUI;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

public class piezas {

	private static arista aristas[];
	private static esquina esquinas[];
	private static cara_panel[] caras;
	final static int CENTRO=0;
	final static int ARISTA=1;
	final static int ESQUINA=2;
	final static Map<Integer, String> nombre_cara=new HashMap<Integer, String>(){

		private static final long serialVersionUID = 1L;

		{
			put(0,"blanca");
			put(1,"verde");
			put(2,"naranja");
			put(3,"azul");
			put(4,"roja");
			put(5,"amarilla");
			
			
		}
	};
	
	public static void montarAristas() {
	
		aristas=new arista[12];
		
		aristas[0]=new arista(0,1, 2,7);
		aristas[1]=new arista(0,3, 1,7);
		aristas[2]=new arista(0,5, 3,7);
		aristas[3]=new arista(0,7, 4,7);
		
		
		aristas[4]=new arista(1,5, 2,3);
		aristas[5]=new arista(2,5, 3,3);
		
		aristas[6]=new arista(3,5, 4,3);
		aristas[7]=new arista(4,5, 1,3);
		
		
		aristas[8]=new arista(5,1, 4,1);
		aristas[9]=new arista(5,3, 1,1);
		aristas[10]=new arista(5,5, 3,1);
		aristas[11]=new arista(5,7, 2,1);
	
	}
	
	public static void montarEsquinas() {
		
		esquinas=new esquina[8];
		
		esquinas[0]=new esquina(0,0, 1,8, 2,6);
		esquinas[1]=new esquina(0,2, 2,8, 3,6);
		esquinas[2]=new esquina(0,6, 4,8, 1,6);
		esquinas[3]=new esquina(0,8, 3,8, 4,6);
		
		esquinas[4]=new esquina(5,0, 4,2, 1,0);
		esquinas[5]=new esquina(5,2, 3,2, 4,0);
		esquinas[6]=new esquina(5,6, 1,2, 2,0);
		esquinas[7]=new esquina(5,8, 2,2, 3,0);
		
	}
	
	public static void montarCubo(cara_panel[] _caras) {
		caras=_caras;
	}
	
	public static arista getArista(int cara, int id) {
		
		arista arista=null;
		
		for(arista a: aristas) {
			
			int _cara=a.cara1();
			int _id=a.id1();
			
			if(cara==_cara&&id==_id) {
				
				arista=a;
				break;
				
			}
			
			_cara=a.cara2();
			_id=a.id2();
			
			if(cara==_cara&&id==_id) {
				
				arista=a;
				break;
				
			}			
		}
		
		return arista;
		
	}
	
	public static esquina getEsquinas(int cara, int id) {
		
		esquina esquina=null;
		
		for(esquina e: esquinas) {
			
			int _cara=e.cara1();
			int _id=e.id1();
			
			if(cara==_cara&&id==_id) {
				esquina=e;
				break;
			}
			
			_cara=e.cara2();
			_id=e.id2();
			
			if(cara==_cara&&id==_id) {
				esquina=e;
				break;
			}
			
			_cara=e.cara3();
			_id=e.id3();
			
			if(cara==_cara&&id==_id) {
				esquina=e;
				break;
			}
			
		}
		
		return esquina;
		
	}
	
	public static cara_panel getCara(int id) {
		
		return caras[id];
		
	}
	
	public static int getID_Color(Color c) {
		
		int id=-1;
		
		if(c.getRed()>=250&&c.getRed()<=255&&c.getBlue()>=250&&c.getBlue()<=255&&c.getGreen()>=250&&c.getGreen()<=255) id=0;
		if(c.getRed()>=0&&c.getRed()<=5&&c.getBlue()>=0&&c.getBlue()<=5&&c.getGreen()>=250&&c.getGreen()<=255) id=1;
		if(c.getRed()>=250&&c.getRed()<=255&&c.getBlue()>=0&&c.getBlue()<=5&&c.getGreen()>=10&&c.getGreen()<=245) id=2;
		if(c.getRed()>=0&&c.getRed()<=5&&c.getBlue()>=250&&c.getBlue()<=255&&c.getGreen()>=0&&c.getGreen()<=5) id=3;
		if(c.getRed()>=250&&c.getRed()<=255&&c.getBlue()>=0&&c.getBlue()<=5&&c.getGreen()>=0&&c.getGreen()<=5) id=4;
		if(c.getRed()>=250&&c.getRed()<=255&&c.getBlue()>=0&&c.getBlue()<=5&&c.getGreen()>=250&&c.getGreen()<=255) id=5;
		
		if(id==-1) {
			
			JOptionPane.showMessageDialog(null, "ID_Color: " + id + " Color: " + c.toString());
			
		}
		
		return id;
		
	}
	
}
