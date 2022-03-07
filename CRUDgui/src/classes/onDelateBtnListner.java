package classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class onDelateBtnListner implements ActionListener{
	
	private JButton button;

	public onDelateBtnListner(JButton button) {
		
		this.button = button;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println("Delate pressed ");
		
	}
	
	
	

}
