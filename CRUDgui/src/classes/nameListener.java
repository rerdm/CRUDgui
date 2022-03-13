package classes;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;


public class nameListener implements CaretListener{
	
	
	private JButton button;
	
	public nameListener(JButton button) {
		
		this.button = button;
		
	}
	
	@Override
	public void caretUpdate(CaretEvent e) {
		
		JTextField field =(JTextField)e.getSource();
		String nameFiledtext = field.getText();

		button.setEnabled(!nameFiledtext.trim().isEmpty());
		
	
		
		
	}

}
