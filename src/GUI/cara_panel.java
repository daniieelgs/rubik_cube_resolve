package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class cara_panel extends JPanel{

	private static final long serialVersionUID = 1L;
	private Color color;
	private JButton[] botones_color;
	private int caraID;
	private int dimension;
	private logPanel log;
	
	public cara_panel(Color _color, int _id, int _dimension, logPanel _log) {
		
		this.setLayout(new GridLayout(3, 3, 2, 2));
		
		color=_color;
		caraID=_id;		
	
		log=_log;
		
		dimension=(_dimension/3)-2;
	
		boton_color();
	}
	
	private void boton_color() {
		
		botones_color=new JButton[9];
		
		for(int i=0; i<botones_color.length; i++) {
			
			botones_color[i]=new JButton();
			
			botones_color[i].setBackground(color);
			
			botones_color[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			
			if(i!=4) {
				botones_color[i].addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent arg0) {
						
						JFrame frame=new JFrame();
						frame.setSize(198, 50);
						frame.setUndecorated(true);
						
						frame.addWindowFocusListener(new WindowFocusListener() {
							
							public void windowLostFocus(WindowEvent arg0) {
								frame.setVisible(false);
							}
							
							public void windowGainedFocus(WindowEvent arg0) {}
						});
						
						JButton boton=(JButton) arg0.getSource();
						
						Point cursor=MouseInfo.getPointerInfo().getLocation (); 
						
						int cursorX=cursor.x;
						int cursorY=cursor.y;
						
						frame.setLocation(cursorX-100, cursorY);
						
						JPanel select_color=new JPanel();
						
						select_color.setLayout(null);
						
						select_color.setBackground(Color.GRAY.brighter());
						
						JButton colores[]=new JButton[6];
						
						for(int i=0; i<colores.length; i++) {
							
							colores[i]=new JButton();
							
						}
						
						colores[0].setBackground(Color.WHITE);
						colores[1].setBackground(Color.GREEN);
						colores[2].setBackground(Color.ORANGE);
						colores[3].setBackground(Color.BLUE);
						colores[4].setBackground(Color.RED);
						colores[5].setBackground(Color.YELLOW);
						
						for(int i=0; i<colores.length; i++) {
							
							colores[i].setBounds((i*32) + 4, 2, 28, 46);
							
							colores[i].addActionListener(new ActionListener() {
								
								public void actionPerformed(ActionEvent arg0) {

									JButton color=(JButton) arg0.getSource();
									
									boton.setBackground(color.getBackground());
									
									frame.setVisible(false);
									
								}
							});
							
							select_color.add(colores[i]);
							
						}
						
						frame.add(select_color);
						
						frame.setVisible(true);
											
					}
				});
				
				botones_color[i].addMouseListener(new MouseAdapter() {
					
					int id_button;
					int PIEZA;
					cara_panel cara1, cara2, cara3;
					arista arista;
					esquina esquina;
					
					public void mouseEntered(MouseEvent e) {
						
						JButton boton=(JButton) e.getSource();
						
						id_button=0;
						
						for(int i=0; i<botones_color.length; i++) {
							
							if(boton==botones_color[i]) {
								id_button=i;
								break;
							}
							
						}
						
						botones_color[id_button].setCursor(new Cursor(Cursor.HAND_CURSOR));
							
						if(id_button==1||id_button==3||id_button==5||id_button==7) {
							
							PIEZA=piezas.ARISTA;
							
							arista=piezas.getArista(caraID, id_button);
							
							cara1=piezas.getCara(arista.cara1());						
							cara1.setColor(arista.id1(), cara1.getColor(arista.id1()).darker());
							
							cara2=piezas.getCara(arista.cara2());						
							cara2.setColor(arista.id2(), cara2.getColor(arista.id2()).darker());
						
						}else if(id_button==0||id_button==2||id_button==6||id_button==8) {
							
							PIEZA=piezas.ESQUINA;
							
							esquina=piezas.getEsquinas(caraID, id_button);
							
							cara1=piezas.getCara(esquina.cara1());
							cara1.setColor(esquina.id1(), cara1.getColor(esquina.id1()).darker());
							
							cara2=piezas.getCara(esquina.cara2());
							cara2.setColor(esquina.id2(), cara2.getColor(esquina.id2()).darker());
							
							cara3=piezas.getCara(esquina.cara3());
							cara3.setColor(esquina.id3(), cara3.getColor(esquina.id3()).darker());
							
						}
																
					}
					
					public void mouseExited(MouseEvent e) {
											
						if(PIEZA==piezas.ARISTA){
							
							cara1.setColor(arista.id1(), cara1.getColor(arista.id1()).brighter());						
							cara2.setColor(arista.id2(), cara2.getColor(arista.id2()).brighter());
							
						}
						
						if(PIEZA==piezas.ESQUINA) {
							
							cara1.setColor(esquina.id1(), cara1.getColor(esquina.id1()).brighter());
							cara2.setColor(esquina.id2(), cara2.getColor(esquina.id2()).brighter());
							cara3.setColor(esquina.id3(), cara3.getColor(esquina.id3()).brighter());
							
						}
						
					}
					
				});
			}
			
						
			add(botones_color[i]);
			
		}
			
		botones_color[4].setLayout(null);
						
		set_girar_derecha(false);
				
		set_girar_izquierda(false);
				
		botones_color[4].add(new JLabel());
		
		botones_color[4].addMouseListener(new MouseAdapter() {

	        public void mouseClicked(MouseEvent e) {

	            
	            if(e.getButton()==MouseEvent.BUTTON3) girar_derecha(false);
	            else girar_izquierda(false);
	        }
			
		});
	}
	
	public void setColor(int id, Color c) {
		
		botones_color[id].setBackground(c);
		
	}
	
	public Color getColor() {
		
		return botones_color[4].getBackground();
		
	}
	
	public Color getColor(int id) {
		
		return botones_color[id].getBackground();
		
	}
	
	public void set_girar_derecha(boolean visible) {
		
		if(visible) {
			BufferedImage icon=null;		
			try {
				icon=ImageIO.read(cara_panel.class.getResource("/GUI/Images/girar-derecha.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println(dimension + " - " + (dimension-35));
			botones_color[4].setIcon(new ImageIcon(icon.getScaledInstance((int) (dimension*0.47), (int) (dimension*0.47), Image.SCALE_DEFAULT)));

		}
		else botones_color[4].setIcon(null);
		
	}
	
	public void set_girar_izquierda(boolean visible) {
		
		if(visible) {
			BufferedImage icon=null;
			try {
				icon=ImageIO.read(cara_panel.class.getResource("/GUI/Images/girar-izquierda.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			botones_color[4].setIcon(new ImageIcon(icon.getScaledInstance((int) (dimension*0.47), (int) (dimension*0.47), Image.SCALE_DEFAULT)));

		}
		else botones_color[4].setIcon(null);
		
	}
	
	public void girar_derecha(boolean mensaje) {
		
    	set_girar_izquierda(false);
    	set_girar_derecha(true);
		
		if(mensaje) {
			for(int i=0; i<6; i++) {
				if(i!=caraID) {
					piezas.getCara(i).set_girar_derecha(false);
					piezas.getCara(i).set_girar_izquierda(false);
				}
			}
			
			if(log!=null) {
				
				log.print("Gira a la derecha la cara " + piezas.nombre_cara.get(caraID), color);
				while(!log.isContinuar()) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				log.continuar(false);
				
			}
						
		}
		
		/*
		 * 1-->3
		 * 2-->1
		 * 3-->2
		 * 
		 * 
		 */
		
		esquina[] esquinas={piezas.getEsquinas(caraID, 0),
							piezas.getEsquinas(caraID, 2),
							piezas.getEsquinas(caraID, 8),
							piezas.getEsquinas(caraID, 6)};
		
		arista[] aristas={piezas.getArista(caraID, 1),
							piezas.getArista(caraID, 5),
							piezas.getArista(caraID, 7),
							piezas.getArista(caraID, 3)};
		
		if(caraID!=0&&caraID!=5) {
			
			Color[] colores1=new Color[4];
			Color[] colores2=new Color[4];
			Color[] colores3=new Color[4];
			
			colores1[0]=esquinas[3].color2();
			colores1[1]=esquinas[0].color2();
			colores1[2]=esquinas[1].color3();
			colores1[3]=esquinas[2].color3();
			
			colores2[0]=esquinas[3].color1();
			colores2[1]=esquinas[0].color3();
			colores2[2]=esquinas[1].color2();
			colores2[3]=esquinas[2].color1();
			
			colores3[0]=esquinas[3].color3();
			colores3[1]=esquinas[0].color1();
			colores3[2]=esquinas[1].color1();
			colores3[3]=esquinas[2].color2();
			
			for(int i=0; i<esquinas.length; i++) {
				
				esquinas[i].setColor1(colores1[i]);
				esquinas[i].setColor2(colores2[i]);
				esquinas[i].setColor3(colores3[i]);
				
			}
			
			colores1[0]=aristas[3].color1();
			colores1[1]=aristas[0].color2();
			colores1[2]=aristas[1].color2();
			colores1[3]=aristas[2].color1();
			
			colores2[0]=aristas[3].color2();
			colores2[1]=aristas[0].color1();
			colores2[2]=aristas[1].color1();
			colores2[3]=aristas[2].color2();
			
			for(int i=0; i<aristas.length; i++) {
				
				aristas[i].setColor1(colores1[i]);
				aristas[i].setColor2(colores2[i]);
				
			}
			
		}else {
			
			Color[] colores1=new Color[4];
			Color[] colores2=new Color[4];
			Color[] colores3=new Color[4];
			
			for(int i=0; i<esquinas.length; i++) {
				
				int id=(i+1);
				if(i==3) id=0;
				
				colores1[id]=esquinas[i].color1();
				colores2[id]=esquinas[i].color2();
				colores3[id]=esquinas[i].color3();
				
			}
			
			for(int i=0; i<esquinas.length; i++) {
				
				esquinas[i].setColor1(colores1[i]);
				esquinas[i].setColor2(colores2[i]);
				esquinas[i].setColor3(colores3[i]);
				
			}
			
			for(int i=0; i<aristas.length; i++) {
				
				int id=(i+1);
				if(i==3) id=0;
				
				colores1[id]=aristas[i].color1();
				colores2[id]=aristas[i].color2();
				
			}
			
			for(int i=0; i<aristas.length; i++) {
				
				aristas[i].setColor1(colores1[i]);
				aristas[i].setColor2(colores2[i]);
				
			}
			
		}
		
		updateUI();
				
	}
	
	public void girar_izquierda(boolean mensaje) {
		
    	set_girar_derecha(false);
    	set_girar_izquierda(true);
		
		if(mensaje) {
			for(int i=0; i<6; i++) {
				if(i!=caraID) {
					piezas.getCara(i).set_girar_derecha(false);
					piezas.getCara(i).set_girar_izquierda(false);
				}
			}
			if(log!=null) {
				
				log.print("Gira a la izquierda la cara " + piezas.nombre_cara.get(caraID), color);
				while(!log.isContinuar()) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				log.continuar(false);
				
			}
		}
		
		for(int i=0; i<3; i++) {
			girar_derecha(false);
		}
		
    	set_girar_derecha(false);
    	set_girar_izquierda(true);
				
	}
	
}
