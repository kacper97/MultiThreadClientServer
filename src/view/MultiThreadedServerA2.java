package view;
import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import utils.Connector;

import javax.swing.*;

public class MultiThreadedServerA2 extends JFrame {
	Connector jdbc = new Connector();	
     private ResultSet rs;

	  // Text area for displaying contents
	  private JTextArea jta = new JTextArea();

	  public static void main(String[] args) throws SQLException {
	    new MultiThreadedServerA2();
	  }

	  public MultiThreadedServerA2() throws SQLException {
	         jdbc.getConnection();
	         System.out.println("Connected to database");
	     
	     
	     jdbc.run();
	    // Place text area on the frame
	    getContentPane().setLayout(new BorderLayout());
	    JScrollPane scrollPane = new JScrollPane(jta);
	    getContentPane().add(scrollPane, BorderLayout.CENTER);
	    
	    JLabel lblInformationAboutServer = new JLabel("Information about server");
	    scrollPane.setColumnHeaderView(lblInformationAboutServer);

	    setTitle("Server");
	    setSize(500, 300);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true); // It is necessary to show the frame here!
	    
    	getSQL();


	    try {
	      // Create a server socket
	      ServerSocket serverSocket = new ServerSocket(8000);
	      jta.append("Server started at " + new Date() + '\n');

	      // Listen for a connection request
	      Socket socket = serverSocket.accept();

	      // Create data input and output streams
	      DataInputStream inputFromClient = new DataInputStream(
	        socket.getInputStream());
	      DataOutputStream outputToClient = new DataOutputStream(
	        socket.getOutputStream());

	      while (true) {
	        // Receive radius from the client
	        int studentNu = inputFromClient.readInt();
	        
	       // int studentNuRet = rs.getInt("STUD_ID");
	        int studentID=  rs.getInt("SID");
			outputToClient.writeInt(studentID);
				int studentNuRet = rs.getInt("STUD_ID");
				String firstName = rs.getString("FNAME");
				String secondName = rs.getString("SNAME");
				outputToClient.writeInt(studentNuRet);
				outputToClient.writeUTF(firstName);
				outputToClient.writeUTF(secondName);
	        	jta.append("Processing ...." + '\n');
	  	        jta.append("Student Number received from client: " + studentNu + studentID + '\n');    
		        jta.append("Info found: " + studentID + " " + studentNuRet + " " + firstName + " " + secondName + '\n');
	      }
	    }
	    catch(IOException ex) {
	      System.err.println(ex);
	    }
	    
	  }
	  
		private void getSQL() throws SQLException{
			rs = jdbc.run();
			if(rs.next()) {
					setText(	rs.getInt("SID"),
						rs.getInt("STUD_ID"),
						rs.getString("FNAME"),
						rs.getString("SNAME"));
			}
		}

		private void setText(int SID, int STUD_ID, String FNAME, String SNAME) {

		}
	}



	
/* 
 * New thread created for client 
 * Validation of request compared to database
 * 

 *	Validate Communication by
 *  Displaying HostName and IPAddress
 * 	In the Client/Server windows with all messages sent
 */

