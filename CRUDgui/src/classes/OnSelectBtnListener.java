package classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class OnSelectBtnListener implements ActionListener{

	private JButton button;

	public OnSelectBtnListener(JButton button) {
		
		this.button = button;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.out.println("Select pressed ");
	}

}
