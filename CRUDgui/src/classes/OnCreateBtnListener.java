package classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

public class OnCreateBtnListener implements ActionListener {

	private JButton button;

	public OnCreateBtnListener(JButton button) {
		
		this.button = button;
	}
	@Override
	public void actionPerformed(ActionEvent e) {

		System.out.println("Create pressed ");
		
		CreateReadUpdateDelate db = new CreateReadUpdateDelate();
		db.connectToDatabase();
		
		try {
			db.readActualTableContent();
		} catch (SQLException e1) {
		
			e1.printStackTrace();
		}
		

	}

}
