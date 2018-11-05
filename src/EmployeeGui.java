//imports
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class EmployeeGui {
 
	//Text Field Initialization
	public JFrame frame;
	private JTextField txtSSn;
	private JTextField txtDOB;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtSalary;
	private JTextField txtGender;
	
	//conn is new connector class, result set variable settin
	private Connector conn = new Connector();
	private ResultSet rs;

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public EmployeeGui() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//SSN label creation + addition
		JLabel lblNewLabel = new JLabel("SSn");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblNewLabel.setBounds(49, 90, 56, 16);
		frame.getContentPane().add(lblNewLabel);
		
		// DOB label creation + addition
		JLabel lblDob = new JLabel("DOB");
		lblDob.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblDob.setBounds(49, 119, 56, 16);
		frame.getContentPane().add(lblDob);
		
		//Name label creation + addition
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblName.setBounds(49, 190, 56, 16);
		frame.getContentPane().add(lblName);
		
		//Address label creation + addition
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblAddress.setBounds(49, 216, 56, 16);
		frame.getContentPane().add(lblAddress);

		//Salary label creation + addition
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblSalary.setBounds(49, 263, 56, 16);
		frame.getContentPane().add(lblSalary);
		
		//Gender label creation + addition
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("SansSerif", Font.BOLD, 13));
		lblGender.setBounds(49, 292, 56, 16);
		frame.getContentPane().add(lblGender);
		
		//SSN Text field creation
		txtSSn = new JTextField();
		txtSSn.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtSSn.setText("SSn");
		txtSSn.setBounds(104, 87, 170, 22);
		frame.getContentPane().add(txtSSn);
		txtSSn.setColumns(10);
		
		//Dob Text field creation
		txtDOB = new JTextField();
		txtDOB.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtDOB.setText("DOB");
		txtDOB.setBounds(104, 116, 170, 22);
		frame.getContentPane().add(txtDOB);
		txtDOB.setColumns(10);
		
		//Name Text field creation
		txtName = new JTextField();
		txtName.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtName.setText("Name");
		txtName.setBounds(104, 187, 300, 22);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		//Salary Text field creation
		txtSalary = new JTextField();
		txtSalary.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtSalary.setText("Salary");
		txtSalary.setBounds(104, 260, 116, 22);
		frame.getContentPane().add(txtSalary);
		txtSalary.setColumns(10);
		
		//Address Text field creation
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtAddress.setText("Address");
		txtAddress.setBounds(104, 213, 300, 22);
		frame.getContentPane().add(txtAddress);
		txtAddress.setColumns(10);
		
		//Gender Text field creation
		txtGender = new JTextField();
		txtGender.setFont(new Font("SansSerif", Font.PLAIN, 13));
		txtGender.setText("Gender");
		txtGender.setBounds(104, 289, 116, 22);
		frame.getContentPane().add(txtGender);
		txtGender.setColumns(10);
		
		//Add Button Creation
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnAdd.setBounds(49, 341, 97, 25);
		frame.getContentPane().add(btnAdd);
		
		//Delete Button Creation
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnDelete.setBounds(158, 341, 97, 25);
		frame.getContentPane().add(btnDelete);
		
		//Update Button Creation
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnUpdate.setBounds(267, 341, 97, 25);
		frame.getContentPane().add(btnUpdate);
		
		//Previous Button Creation
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnPrevious.setBounds(452, 90, 97, 25);
		frame.getContentPane().add(btnPrevious);
		
		//Next Button Creation
		JButton btnNext = new JButton("Next");
		btnNext.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnNext.setBounds(452, 139, 97, 25);
		frame.getContentPane().add(btnNext);
		
		//Clear Button Creation
		JButton btnClear = new JButton("Clear");
		btnClear.setFont(new Font("SansSerif", Font.BOLD, 13));
		btnClear.setBounds(452, 288, 97, 25);
		frame.getContentPane().add(btnClear);
		
		/*
		 * Label for heading and footer
		 */
		JLabel lblEmployeeDetails = new JLabel("EMPLOYEE DETAILS");
		lblEmployeeDetails.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblEmployeeDetails.setBounds(198, 44, 203, 16);
		frame.getContentPane().add(lblEmployeeDetails);
		
		JLabel lblByKacperWoloszyn = new JLabel("By Kacper Woloszyn");
		lblByKacperWoloszyn.setBounds(452, 387, 130, 16);
		frame.getContentPane().add(lblByKacperWoloszyn);
		
	 	//gets the sql records and populates the fields
    	getSQL();

	
    	/*
    	 * Action listeners, these respond to the buttons pressed
    	 */
    	
    	//when add is pressed
    	// result sets moves into a new row not to overwrite data
    	// updates strings from values inserted and puts them in new row
    	// difference between update and add is the moving into new row
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				System.out.println("You pressed Add");
	    		try {
	    			rs.moveToInsertRow();
	    			rs.updateString("id", txtSSn.getText());
	    			rs.updateString("dob", txtDOB.getText());
	    			rs.updateString("name", txtName.getText());
	    			rs.updateString("address", txtAddress.getText());
	    			rs.updateInt("salary", Integer.parseInt(txtSalary.getText()));
	    			rs.updateString("gender", txtGender.getText());
	    			rs.insertRow();
	    		}catch(SQLException e1) {
	    			e1.printStackTrace();
	    		}
			}
		});
		
	   	//if delete is pressed, the result set deletes the entries from the row
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("You pressed Delete");
		           try {
		        	rs.deleteRow();
		        	getSQL();
		           }catch(SQLException e1) {
		        	   e1.printStackTrace();         
		        	   }
			}
		});
		
    	//when update is pressed the id is checked to see for primary keys and duplicates
    	// then the new updated values are placed in the row of the sql database
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("You pressed Update");
	    		try {
	    			Objects.equals(txtSSn.getText().toLowerCase(), rs.getString("id").toLowerCase());
	    			rs.updateString("id", txtSSn.getText());
	    			rs.updateString("dob", txtDOB.getText());
	    			rs.updateString("name", txtName.getText());
	    			rs.updateString("address",txtAddress.getText());
	    			rs.updateInt("salary", Integer.parseInt(txtSalary.getText()));
	    			rs.updateString("gender",txtGender.getText());
	    			rs.updateRow();

	    		}catch(SQLException e1) {
	    			e1.printStackTrace();
	    		}
			}
			});
		
		//// when previous is pressed, previous entry is taken from sql
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("You pressed PREVIOUS");
				try {	
							rs.previous();
			            setText (rs.getString("id"),
			                    rs.getString("dob"),
			                    rs.getString("name"),
			                    rs.getString("address"),
			                    rs.getInt("salary"),
			                    rs.getString("gender"));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		// when next is pressed, next entry is taken from sql
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("You pressed NEXT");
				try {
					rs.next();
					setText(rs.getString("id"),
			                rs.getString("dob"),
			                rs.getString("name"),
			                rs.getString("address"),
			                rs.getInt("salary"),
			                rs.getString("gender"));
				   	
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
    	// Here the fields are cleared, so that it is possible to type something in
    	//Retrieves whether the cursor is before the first row inthis ResultSet object. 
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	    		System.out.println("You pressed Clear");
	    		try {
	    			rs.isBeforeFirst();
	    			txtSSn.setText("");
	    			txtDOB.setText("");
	    			txtName.setText("");
	    			txtAddress.setText("");
	    			txtSalary.setText("");
	    			txtGender.setText("");
	    		}catch(SQLException e1){
	    			e1.printStackTrace();
	    		}
			}
		});
		}
		
	    /*
	    Sets text fields
	         */
	    private void setText(String id, String dob, String name, String address, int salary, String gender){
	        txtSSn.setText(id);
	        txtDOB.setText(dob);
	        txtName.setText(name);
	        txtAddress.setText(address);
	        txtSalary.setText(String.valueOf(salary));
	        txtGender.setText(gender);
	    }
		
	    // gets the result set
		private void getSQL() throws SQLException{
			rs = conn.run();
			if(rs.next()) {
				setText(rs.getString("id"),
						rs.getString("dob"),
						rs.getString("name"),
						rs.getString("address"),
						rs.getInt("salary"),
						rs.getString("gender"));
			}
		}
}
