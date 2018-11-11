package view;
import java.io.*;
import utils.SThread;
import java.net.*;
import java.sql.SQLException;
import java.util.*;
import java.awt.*;

import utils.Connector;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MultiThreadedServerA2 extends JFrame {
	  // connections to the database
	private Connector jdbc = new Connector();	
	
	  // Text area for displaying contents
	  private JTextArea jta = new JTextArea();

	  public MultiThreadedServerA2() {
	    // Place text area on the frame
	    getContentPane().setLayout(new BorderLayout());
	    JScrollPane scrollPane = new JScrollPane(jta);
	    getContentPane().add(scrollPane, BorderLayout.CENTER);
	    
	    JLabel lblInformationAboutServer = new JLabel("Information about server");
	    scrollPane.setColumnHeaderView(lblInformationAboutServer);
	    
	    JButton btnQuit = new JButton("QUIT");
	    //Quit button that closes the app
	    btnQuit.addActionListener(e -> System.exit(0));
	    getContentPane().add(btnQuit, BorderLayout.SOUTH);

	    setTitle("Server");
	    setSize(500, 300);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true); // It is necessary to show the frame here!

	    getConnection();
	    
	    try {
	      // Create a server socket
	      ServerSocket serverSocket = new ServerSocket(8000);
	      //information about the server
	      jta.append("Server started at " + new Date() + '\n');

	      
	      // if the socket is still connected do this loop, checking if not closed 
	      //  As prior, if wrong number entered it would close the socket but still print out processing
	      while (true) {
	    	  Socket socket = serverSocket.accept();
	    	  if (socket.isConnected()) {
	    		  jta.append("Processing ...." + '\n');
	    	  }
	    	  Thread thread = new SThread(socket);
	    	  thread.start();
	      }
	    }
	      catch(IOException ex) {
	    	     System.err.println(ex);
	    	  
	      	}
	    }
	    
	    private void getConnection(){
	    	try {
				jdbc.getConnection();
				System.out.println("Connected to Database");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	  }




