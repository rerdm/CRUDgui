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

public class Gui extends JFrame {

	private JLabel lblHeader;
	private JTable table;
	private JButton btnCreate, btnSelect, btnUpdate, btnDelate;
	private JLabel lblName, lblLastName, lblEmail;
	private JTextField textFieldName, textFieldLastName, textFieldEmail;
	private JTextArea dbOutput;
	private JScrollPane scrollTable;
	private JPanel leftPanel, pnlAdd, pnlButtons;

	private static final int lenOfInputChars = 10;

	public Gui() {

		setTitle("CRUD GUI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		createWidgets();
		addWidgets();

		pack();
		setLocation(200, 200);

		configureInterfaces();
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
		btnCreate.setEnabled(false);
		btnSelect = new JButton("SELECT");
		btnUpdate = new JButton("UPDATE");
		btnDelate = new JButton("DELETE");

		textFieldName = new JTextField(lenOfInputChars);
		textFieldLastName = new JTextField(lenOfInputChars);
		textFieldEmail = new JTextField(lenOfInputChars);

		pnlAdd = new JPanel();
		pnlAdd.setLayout(new GridLayout(0, 2, 10, 10));

		leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(0, 1, 40, 5));

	}

	private void configureInterfaces() {

		btnCreate.addActionListener(new OnCreateBtnListener(btnCreate));
		btnSelect.addActionListener(new OnSelectBtnListener(btnSelect));
		btnUpdate.addActionListener(new OnUpdateBtnListener(btnUpdate));
		btnDelate.addActionListener(new OnDelateBtnListner(btnDelate));

		textFieldName.addCaretListener(new nameListener(btnCreate));

	}
	
	

}
