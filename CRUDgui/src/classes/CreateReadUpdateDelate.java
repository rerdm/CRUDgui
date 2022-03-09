package classes;

import java.beans.Statement;
import java.util.Iterator;
import java.sql.*;

import javax.swing.JButton;

public class CreateReadUpdateDelate {
	

	
	private String url = "jdbc:mysql://localhost/users";
	private String user = "root";
	private String password = "";
	
	private Connection conn;

	public void connectToDatabase() {
		
		
		try  {
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("Connection to Database succdesfull");
			
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
		
	}
	
	
	public void readActualTableContent() throws SQLException {

		System.out.println("DB connected");

		String query = "SELECT * FROM test_db ORDER BY id ASC";
		java.sql.Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery(query);

		int colum = resultSet.getMetaData().getColumnCount();

		for (int i = 1; i <= colum; i++) {

			System.out.print(String.format("%-15s", resultSet.getMetaData().getColumnLabel(i)));
		}

		System.out.println();
		System.out.println("#############################################################");

		while (resultSet.next()) {
			for (int i = 1; i <= colum; i++)
				System.out.print(String.format("%-15s", resultSet.getString(i)));
			System.out.println();

		}

		statement.close();
		resultSet.close();

	}
	
	

	public void selectTtemsFromTable() {
		
		
		

	}
	
	public void delateItemsFromTable() {

	}

	public void updateItemFromTable() {

	}

	public void delateItemFromTable() {

	}

}
