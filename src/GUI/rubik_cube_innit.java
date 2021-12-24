package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class rubik_cube_innit {
	
	public static void main(String[] args) {
		
		JFrame frame=new JFrame("Rubik Cube Solve v2.0");
		
		Image icon;

		try {
			
			icon = new ImageIcon(rubik_cube_innit.class.getResource("Images/icon.png")).getImage();
			
	        frame.setIconImage(icon);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		int ancho=Toolkit.getDefaultToolkit().getScreenSize().width;
		int alto=Toolkit.getDefaultToolkit().getScreenSize().height;
						
		frame.setBounds(0, 0, ancho, alto);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setResizable(false);
		
		logPanel log=new logPanel();
		
		rubikPanel panel_principal=new rubikPanel(ancho - ancho/7-12, alto-25, log);
		
		frame.add(panel_principal, BorderLayout.CENTER);
		
		frame.add(log, BorderLayout.WEST);
		
		JPanel panel_botones=new JPanel();
		
		JButton cargar=new JButton("Provar");
		JButton solve=new JButton("Resolver");
		
		panel_botones.add(cargar);
		panel_botones.add(solve);
		
		frame.add(panel_botones, BorderLayout.SOUTH);
		
		frame.setVisible(true);
		
		log.setPreferredSize(new Dimension(ancho/7+12, log.getHeight()));
		log.setSize(new Dimension(ancho/7+12, log.getHeight()));
		
		log.print("Instrucciones", Color.YELLOW);
		log.print("Click derecho en el centro para girar la cara a la derecha", Color.WHITE);
		log.print("Click izquierdo en el centro para girar la cara a la izquierda", Color.WHITE);
		log.print("Click izquierdo en cualquier pieza para seleccionar el color", Color.WHITE);
		log.print("Pulse continuar para seguir con la resolución", Color.WHITE);
		
		cargar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {

				panel_principal.cargar();
				
			}
		});
		
		solve.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				log.clean();
				
				new resolver().solve();
				
			}
			
		});
		
	}

}

/*
									ID de cada cara

									--- --- ---
								   | 0 | 1 | 2 |
									--- --- ---
								   | 3 | 4 | 5 |
								    --- --- ---
								   | 6 | 7 | 8 |
								    --- --- --- 	
								    
								    ID de las caras
								    
								    
							   ---------
							  |         |							0 --> WHITE
						      |    5    |							1 --> GREEN
						      |         |							2 --> ORANGE
					    	   ---------							3 --> BLUE  									
	  			   ---------   ---------    ---------   ---------		4 --> RED					                                              
				  |		    | |   	    |  |         | |         |		5 --> YELLOW
				  |	   1    | |	   2    |  |    3    | |    4    |
				  |		    | |	        |  |         | |         |
				   ---------   ---------    ---------   ---------
				  			   ---------
				  			  |			|
				  			  |    0    |
				  			  |         |
				  			   ---------
				  					  
				  					  

*/