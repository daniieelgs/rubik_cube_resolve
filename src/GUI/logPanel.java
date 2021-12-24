package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class logPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private boolean continuar;
	private JPanel printer;
	private JScrollPane scroll_printer;
	
	public logPanel() {
		
		setLayout(new BorderLayout());
				
		continuar=false;
		
		printer=new JPanel();
		
		printer.setLayout(new BoxLayout(printer, BoxLayout.Y_AXIS));
		
		printer.setBackground(Color.BLACK);
		
		scroll_printer=new JScrollPane(printer);
		
		scroll_printer.setOpaque(false);
		
		add(scroll_printer, BorderLayout.CENTER);
				
		JButton cont=new JButton("Continuar");
		
		add(cont, BorderLayout.SOUTH);
		
		cont.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				continuar=true;
			}
		});
		
	}
	
    public void print(String msg, Color c) {
    	JLabel l=new JLabel(msg);
    	l.setForeground(c);
    	//l.setFont(new Font("Lucida Console", Font.PLAIN, l.getFont().getSize()));
    	printer.add(l);
    	scroll_printer.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {  
    	    public void adjustmentValueChanged(AdjustmentEvent e) {  
    	        e.getAdjustable().setValue(e.getAdjustable().getMaximum());  
    	    }
    	});
    	updateUI();
    }
    
    public void clean() {
    	printer.removeAll();
    	updateUI();
    }
    
    public boolean isContinuar() {
    	return continuar;
    }
    
    public void continuar(boolean b) {
    	continuar=b;
    }
}
