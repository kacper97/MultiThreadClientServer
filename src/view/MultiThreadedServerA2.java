package view;
import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.awt.*;

import utils.Connector;

import javax.swing.*;

public class MultiThreadedServerA2 extends JFrame {
	private Connector jdbc = new Connector();	
     private ResultSet rs;

	  // Text area for displaying contents
	  private JTextArea jta = new JTextArea();

	  public static void main(String[] args) throws SQLException {
	    new MultiThreadedServerA2();
	  }

	  public MultiThreadedServerA2() throws SQLException {
		  // connections to the database
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

	    try {
	      // Create a server socket
	      ServerSocket serverSocket = new ServerSocket(8000);
	      //information about the server
	      jta.append("Server started at " + new Date() + '\n');

	      // Listen for a connection request
	      Socket socket = serverSocket.accept();

	      // Create data input and output streams
	      DataInputStream inputFromClient = new DataInputStream(
	        socket.getInputStream());
	      DataOutputStream outputToClient = new DataOutputStream(
	        socket.getOutputStream());
	      
	      // if the socket is still connected do this loop, checking if not closed 
	      //  As prior, if wrong number entered it would close the socket but still print out processing
	      while (true) {
	    	  if (socket.isConnected() && !socket.isClosed()) {
	    		  jta.append("Processing ...." + '\n');
	    	  }
	        // Receive Student number from the client
	        int studentNu = inputFromClient.readInt();
	        ResultSet rs = jdbc.returnRecord(studentNu);
	       	
           if (rs.next()) {
			   	int studentID=  rs.getInt("SID");	
				outputToClient.writeInt(studentID);
				String firstName = rs.getString("FNAME");
				String secondName = rs.getString("SNAME");
				outputToClient.writeInt(studentNu);
				outputToClient.writeUTF(firstName);
				outputToClient.writeUTF(secondName);
	  	        jta.append("Student Number received from client: " + studentNu + '\n');    
		        jta.append("Info found: " + studentID + " " + studentNu + " " + firstName + " " + secondName + '\n');
	      }
           else {
        	   Boolean error = rs.isClosed();
        	   outputToClient.writeBoolean(error);
        	   jta.append("Not found in Database");
        	   // close socket
               socket.close(); 
           }
	      }
	    }
	    catch(IOException ex) {
	      System.err.println(ex);
	    }
	    
	  }
	}



