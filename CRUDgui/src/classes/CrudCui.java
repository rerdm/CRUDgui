package classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.invoke.ConstantBootstraps;
import java.sql.*;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.Box;
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
import javax.swing.border.Border;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.mysql.cj.xdevapi.Statement;

public class CrudCui extends JFrame {

	private JLabel lblHeader;
	private JTable table;
	private JButton btnCreate, btnSelect, btnUpdate, btnDelate;
	private JLabel lblName, lblLastName, lblEmail;
	private JTextField textFieldName, textFieldLastName, textFieldEmail;
	private JTextArea dbOutput;
	private JScrollPane scrollTable;
	private JPanel leftPanel, pnlAdd, pnlButtons;

	private static final int lenOfInputChars = 10;
	
	private String dbName = "users";
	private String talbeName = "test_db";
	private String url = "jdbc:mysql://localhost/"+dbName;
	private String user = "root";
	private String password = "";
	
	

	private Connection conn;
	private String headerItens, lineItems;
	
	private int lenOfTableTtems;

	private String name;
	private String lastName;
	private String email;
	
	private String selectedId;
	
	private int currentLengthOfTable;

	public CrudCui() {

		setTitle("CRUD GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createWidgets();
		addWidgets();

		pack();
		setLocation(200, 200);

		configureInterfaces();
		
		CreateReadUpdateDelate db = new CreateReadUpdateDelate();
		db.connectToDatabase();

		try {
			db.readActualTableContent();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
	}

	private void addWidgets() {

		getContentPane().setLayout(new BorderLayout(10, 10));
		getContentPane().add(BorderLayout.PAGE_START, lblHeader);
		getContentPane().add(BorderLayout.CENTER, scrollTable);
		getContentPane().add(BorderLayout.PAGE_END, dbOutput);
		getContentPane().add(BorderLayout.WEST, leftPanel);

		// will be set in box-layout container
		pnlAdd.add(lblName);
		pnlAdd.add(textFieldName);
		pnlAdd.add(lblLastName);
		pnlAdd.add(textFieldLastName);
		pnlAdd.add(lblEmail);
		pnlAdd.add(textFieldEmail);

		leftPanel.add(pnlAdd);

		leftPanel.add(btnCreate);
		leftPanel.add(btnSelect);
		leftPanel.add(btnUpdate);
		leftPanel.add(btnDelate);

		pnlAdd.setMaximumSize(pnlAdd.getPreferredSize());

	}

	private void createWidgets() {

		lblHeader = new JLabel("CRUD GUI");
		lblHeader.setFont(lblHeader.getFont().deriveFont(Font.BOLD + Font.ITALIC, 30));
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setOpaque(true);

		lblHeader.setBackground(Color.BLACK);
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);

		dbOutput = new JTextArea();
		dbOutput.setPreferredSize(new Dimension(0, 60));

		Border border = BorderFactory.createLineBorder(Color.BLACK);
		dbOutput.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		table = new JTable(100, 4);
		scrollTable = new JScrollPane(table);

		lblName = new JLabel(" Name :");
		lblLastName = new JLabel(" Last Name :");
		lblEmail = new JLabel("  Email :");

		btnCreate = new JButton("CREATE");
		//btnCreate.setEnabled(false);
		btnSelect = new JButton("SELECT");
		
		btnUpdate = new JButton("UPDATE");
		//btnUpdate.setEnabled(false);
		
		btnDelate = new JButton("DELETE");
		btnDelate.setEnabled(false);
		

		textFieldName = new JTextField(lenOfInputChars);
		textFieldLastName = new JTextField(lenOfInputChars);
		textFieldEmail = new JTextField(lenOfInputChars);

		pnlAdd = new JPanel();
		pnlAdd.setLayout(new GridLayout(0, 2, 10, 10));

		leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(0, 1, 40, 5));

	}

	private void configureInterfaces() {

		btnCreate.addActionListener(new OnCreateBtn());
		btnSelect.addActionListener(new OnSelectBtn());
		btnUpdate.addActionListener(new OnUpdateBtn());
		btnDelate.addActionListener(new OnDelateBtn());

		textFieldName.addCaretListener(new nameListener(btnCreate));

	}
	
	
	private class OnSelectBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			System.out.println("select row ");
//			System.out.println("Selected Column :"+table.getSelectedColumn());
//			System.out.println("Selected Row    :"+table.getSelectedRow());
			
			int selectedRow = table.getSelectedRow();

			//selectedId = selectedRow;

			textFieldName.setText((String) table.getValueAt(selectedRow,1));
			textFieldLastName.setText((String) table.getValueAt(selectedRow,2));
			textFieldEmail.setText((String) table.getValueAt(selectedRow,3));
			
			table.getValueAt(selectedRow,0);
			
			
			
			name = (String) table.getValueAt(selectedRow,1);
			lastName = (String) table.getValueAt(selectedRow,2);
			email = (String) table.getValueAt(selectedRow,3);
			
			
			selectedId = (String) table.getValueAt(selectedRow,0);
			
			System.out.println("ID       : "+selectedId);
			System.out.println("name     : "+name);
			System.out.println("lastname : "+lastName);
			System.out.println("lastname : "+email);
			
			System.out.print("\n");
			
			
			btnDelate.setEnabled(true);
			}

		

	}
	
	
	
	
	
	private class OnCreateBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			name = textFieldName.getText();
			lastName = textFieldLastName.getText();
			email = textFieldEmail.getText();

			System.out.println("Create pressed ");
			
			CreateReadUpdateDelate db = new CreateReadUpdateDelate();
			db.connectToDatabase();
			
			try {
				db.insertItemsInTable();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			try {
				db.readActualTableContent();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			

		}

	}
	
	private class OnUpdateBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			System.out.println("Update pressed ");
			
			CreateReadUpdateDelate db = new CreateReadUpdateDelate();
			db.connectToDatabase();
			
			try {
				db.updateTableItems();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			try {
				db.readActualTableContent();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

		}

	}
	
	private class OnDelateBtn implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			System.out.println("Delate pressed ");
			
			CreateReadUpdateDelate db = new CreateReadUpdateDelate();
			db.connectToDatabase();
			
			try {
				db.delateTableItems();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
			
			try {
				db.readActualTableContent();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

		}

	}
	
	private class CreateReadUpdateDelate {
		
		private int headerLines;
		private int tableContentLines;
		

		public void connectToDatabase() {

			try {
				conn = DriverManager.getConnection(url, user, password);
				System.out.println("Connection to Database succdesfull");

			} catch (Exception e) {

				System.out.println(e.getMessage());
			}

		}

		public void readActualTableContent() throws SQLException {

			String query = "SELECT * FROM "+talbeName+" ORDER BY id ASC";
			java.sql.Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			int colum = resultSet.getMetaData().getColumnCount();

			for (headerLines = 1; headerLines <= colum; headerLines++) {

				System.out.print(String.format("%-25s", resultSet.getMetaData().getColumnLabel(headerLines)));
				headerItens = resultSet.getMetaData().getColumnLabel(headerLines);
				table.setValueAt(headerItens, 0, headerLines - 1);

			}

			lenOfTableTtems = headerLines - 1;
			System.out.println();
			System.out.println("Len of Header :" + lenOfTableTtems);
			System.out.println("###############################################################################");

			while (resultSet.next()) {

				tableContentLines = tableContentLines + 1;

				for (int i = 1; i <= colum; i++) {

					// debugging
					System.out.print(String.format("%-25s", resultSet.getString(i)));

					lineItems = resultSet.getString(i);
					table.setValueAt(lineItems, tableContentLines, i - 1);

				}

				System.out.println();

			}
			statement.close();
			resultSet.close();
		}

		public void insertItemsInTable() throws SQLException {

			String query = "INSERT INTO `"+talbeName+"` (`id`, `name`, `nachname`, `email`) VALUES (NULL, '"+name+"', '"+lastName+"', '"+email+"')";
			
			java.sql.Statement statement = conn.createStatement();
			statement.execute(query);
			statement.close();
		
		}
		
		public void updateTableItems() throws SQLException {

			String currentName = textFieldName.getText();
			String currentLastName = textFieldLastName.getText();
			String currentEmail = textFieldEmail.getText();
				
			//UPDATE `test_db` SET `name` = 'Rene555888', `nachname` = 'Erdmann555888', `email` = 'mail.de558885' WHERE `test_db`.`id` = 1;
			String query = "UPDATE `"+talbeName+"` SET `name` = '"+currentName+"', `nachname` = '"+currentLastName+"', `email` = '"+currentEmail+"' WHERE `"+talbeName+"`.`id` = "+selectedId;
			
			System.out.println(query);
			
			dbOutput.setText(query);
			
			java.sql.Statement statement = conn.createStatement();
			statement.execute(query);
			statement.close();
			
			
	
			
		}
		
		public void delateTableItems() throws SQLException {
			

			String query = "DELETE FROM `"+talbeName+"` WHERE `"+talbeName+"`.`id` = "+selectedId;
			
			System.out.println(query);
			
			dbOutput.setText(query);
			
			java.sql.Statement statement = conn.createStatement();
			statement.execute(query);
			statement.close();
			
			btnDelate.setEnabled(false);
	
			
		}
		
		
		
		
	}

}
