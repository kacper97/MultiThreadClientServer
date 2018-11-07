package view;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;

import javax.swing.*;

public class MultiThreadedServerA2 extends JFrame {

	  // Text area for displaying contents
	  private JTextArea jta = new JTextArea();

	  public static void main(String[] args) {
	    new MultiThreadedServerA2();
	  }

	  public MultiThreadedServerA2() {
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

	        // Compute area
	        double area = studentNu * studentNu* Math.PI;

	        // Send area back to the client
	        // outputToClient.writeDouble(area);
	        jta.append("Processing ...." + '\n');
	        jta.append("Student Number received from client: " + studentNu + '\n');
	      //  jta.append("Area found: " + area + '\n');
	      }
	    }
	    catch(IOException ex) {
	      System.err.println(ex);
	    }
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

