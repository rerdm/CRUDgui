package classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class onUpdateBtnListener implements ActionListener {
	
	private JButton button;

	public onUpdateBtnListener(JButton button) {
		
		this.button = button;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println("Update pressed ");
		
		
		
	}

}
