package GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

public class rubikPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private cara_panel[] caras;
	
	public rubikPanel(int with, int heigh, logPanel log) {
						
		setLayout(new GridBagLayout());
				
		int pad= (with > heigh ? heigh/3 : with/4) - 40; 
		
		caras=new cara_panel[6];
		
		caras[0]=new cara_panel(Color.WHITE, 0, pad, log);
		caras[1]=new cara_panel(Color.GREEN, 1, pad, log);
		caras[2]=new cara_panel(Color.ORANGE, 2, pad, log);
		caras[3]=new cara_panel(Color.BLUE, 3, pad, log);
		caras[4]=new cara_panel(Color.RED, 4, pad, log);
		caras[5]=new cara_panel(Color.YELLOW, 5, pad, log);
				
		GridBagConstraints[] constraints=new GridBagConstraints[6];
		
		GridBagConstraints c=new GridBagConstraints();
		
		c.gridx=1;
		c.gridy=0;
		c.ipadx=pad;
		c.ipady=pad;
		c.insets=new Insets(5, 5, 5, 5);
		c.fill=GridBagConstraints.BOTH;
		
		constraints[5]=c;
		
		
		c=new GridBagConstraints();
		
		c.gridx=0;
		c.gridy=1;
		c.ipadx=pad;
		c.ipady=pad;
		c.insets=new Insets(5, 5, 5, 5);
		c.fill=GridBagConstraints.BOTH;
		
		constraints[1]=c;
		
		
		c=new GridBagConstraints();
		
		c.gridx=1;
		c.gridy=1;
		c.ipadx=pad;
		c.ipady=pad;
		c.insets=new Insets(5, 5, 5, 5);
		c.fill=GridBagConstraints.BOTH;
		
		constraints[2]=c;
		
		
		c=new GridBagConstraints();
		
		c.gridx=2;
		c.gridy=1;
		c.ipadx=pad;
		c.ipady=pad;
		c.insets=new Insets(5, 5, 5, 5);
		c.fill=GridBagConstraints.BOTH;
		
		constraints[3]=c;
		
		
		c=new GridBagConstraints();
		
		c.gridx=3;
		c.gridy=1;
		c.ipadx=pad;
		c.ipady=pad;
		c.insets=new Insets(5, 5, 5, 5);
		c.fill=GridBagConstraints.BOTH;
		
		constraints[4]=c;
		
		
		c=new GridBagConstraints();
		
		c.gridx=1;
		c.gridy=2;
		c.ipadx=pad;
		c.ipady=pad;
		c.insets=new Insets(5, 5, 5, 5);
		c.fill=GridBagConstraints.BOTH;
		
		constraints[0]=c;
		
		piezas.montarAristas();
		piezas.montarEsquinas();
		piezas.montarCubo(caras);
		
		
		for(int i=0; i<caras.length; i++) {
			
			caras[i].set_girar_derecha(true);
			add(caras[i], constraints[i]);
			
		}				
	}
	
	public rubikPanel(int with, int heigh) {
		this(with, heigh, null);
	}
	
	public void cargar() {


		caras[0].setColor(0, Color.RED);
		caras[0].setColor(1, Color.WHITE);
		caras[0].setColor(2, Color.BLUE);
		caras[0].setColor(3, Color.GREEN);
		caras[0].setColor(5, Color.GREEN);
		caras[0].setColor(6, Color.YELLOW);
		caras[0].setColor(7, Color.BLUE);
		caras[0].setColor(8, Color.GREEN);
		
		caras[1].setColor(0, Color.YELLOW);
		caras[1].setColor(1, Color.RED);
		caras[1].setColor(2, Color.YELLOW);
		caras[1].setColor(3, Color.ORANGE);
		caras[1].setColor(5, Color.YELLOW);
		caras[1].setColor(6, Color.BLUE);
		caras[1].setColor(7, Color.ORANGE);
		caras[1].setColor(8, Color.WHITE);
		
		caras[2].setColor(0, Color.ORANGE);
		caras[2].setColor(1, Color.YELLOW);
		caras[2].setColor(2, Color.ORANGE);
		caras[2].setColor(3, Color.ORANGE);
		caras[2].setColor(5, Color.BLUE);
		caras[2].setColor(6, Color.BLUE);
		caras[2].setColor(7, Color.RED);
		caras[2].setColor(8, Color.WHITE);
		
		caras[3].setColor(0, Color.YELLOW);
		caras[3].setColor(1, Color.WHITE);
		caras[3].setColor(2, Color.RED);
		caras[3].setColor(3, Color.YELLOW);
		caras[3].setColor(5, Color.RED);
		caras[3].setColor(6, Color.ORANGE);
		caras[3].setColor(7, Color.WHITE);
		caras[3].setColor(8, Color.ORANGE);

		
		caras[4].setColor(0, Color.WHITE);
		caras[4].setColor(1, Color.YELLOW);
		caras[4].setColor(2, Color.GREEN);
		caras[4].setColor(3, Color.GREEN);
		caras[4].setColor(5, Color.WHITE);
		caras[4].setColor(6, Color.WHITE);
		caras[4].setColor(7, Color.ORANGE);
		caras[4].setColor(8, Color.RED);

		
		caras[5].setColor(0, Color.RED);
		caras[5].setColor(1, Color.GREEN);
		caras[5].setColor(2, Color.GREEN);
		caras[5].setColor(3, Color.BLUE);
		caras[5].setColor(5, Color.BLUE);
		caras[5].setColor(6, Color.BLUE);
		caras[5].setColor(7, Color.RED);
		caras[5].setColor(8, Color.GREEN);
		
	}
	
}
