package classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CRUD extends JFrame{
	
	private JLabel lblHeader;
	private JTable table;
	private JButton btnCreate, btnSelect, btnUpdate, btnDelate;
	private JLabel lblName, lblLastName, lblEmail;
	private JTextField textFieldName,textFieldLastName,textFieldEmail;
	private JTextArea dbOutput;
	private JScrollPane scrollTable;
	private JPanel leftPanel, pnlAdd;
	
	
	
	public CRUD() {
		
		setTitle("CRUD GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		createWidgets();
		addWidgets();
		
		pack();
		setLocation(200,200);
		
	}
	
	private void addWidgets() {
		
		getContentPane().setLayout(new BorderLayout(10, 10));
		getContentPane().add(BorderLayout.PAGE_START,lblHeader);
		getContentPane().add(BorderLayout.CENTER,scrollTable);
		getContentPane().add(BorderLayout.PAGE_END,dbOutput);
		getContentPane().add(BorderLayout.WEST, leftPanel);
		
		// will be set in box-layout container
		pnlAdd.add(lblName);
		pnlAdd.add(lblLastName);
		pnlAdd.add(lblEmail);
		
		pnlAdd.add(textFieldEmail);
		pnlAdd.add(textFieldLastName);
		pnlAdd.add(textFieldEmail);
		
		leftPanel.add(pnlAdd);
		
		leftPanel.add(btnCreate);
		leftPanel.add(btnSelect);
		leftPanel.add(btnUpdate);
		leftPanel.add(btnDelate);
		
	}

	private void createWidgets() {
			
		lblHeader = new JLabel("CRUD GUI");
		lblHeader.setFont(lblHeader.getFont().deriveFont(Font.BOLD + Font.ITALIC, 30));
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setOpaque(true);
		
		lblHeader.setBackground(Color.BLACK);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
	

		dbOutput = new JTextArea();
		dbOutput.setPreferredSize(new Dimension(0,60));
		
		table = new JTable(100, 4);
		scrollTable = new JScrollPane(table);
		
		
		lblName = new JLabel();
		lblLastName = new JLabel();
		lblEmail = new JLabel();
		
		
		btnCreate = new JButton("CREATE");
		btnSelect = new JButton("SELECT");
		btnUpdate = new JButton("UPDATE");
		btnDelate = new JButton("DELETE");
		
		textFieldName = new JTextField();
		textFieldLastName = new JTextField();
		textFieldEmail = new JTextField();
		
		pnlAdd = new JPanel();
		pnlAdd.setLayout(new GridLayout(0, 2, 5, 5));
		
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));


		
	}
	
	
	

}
