package classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class onCreateBtnListener implements ActionListener {

	private JButton button;

	public onCreateBtnListener(JButton button) {
		
		this.button = button;
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		System.out.println("Create pressed ");

	}

}
