package view;
import java.io.*;
import utils.SThread;
import java.net.*;
import java.sql.SQLException;
import java.util.*;
import java.awt.*;
import utils.Connector;
import javax.swing.*;

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
	    
	    JLabel lblInformationAboutServer = new JLabel("Information about Server");
	    scrollPane.setColumnHeaderView(lblInformationAboutServer);
	    
	    //Quit button init/ action listener / Location on Frame
	    JButton btnQuit = new JButton("QUIT");
	    btnQuit.addActionListener(e -> System.exit(0));  //Quit button that closes the app
	    getContentPane().add(btnQuit, BorderLayout.SOUTH);

	    //Window
	    setTitle("Server");
	    setSize(500, 300);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true); // It is necessary to show the frame here!

	    //Gets the database
	    getConnection();
	    
	    try {
	      // Create a server socket
	      ServerSocket serverSocket = new ServerSocket(8000);
	      //information about the server
	      jta.append("Server started at " + new Date() + '\n');
	      // if the socket is connected start print out Processing string
	      // if connection accepted, start a new thread
	      while (true) {
	    	  Socket socket = serverSocket.accept();
	    	  if (socket.isConnected()) {
	    		  jta.append("Processing new request to server ...." + '\n');
	    	  }
	    	  Thread thread = new SThread(socket);
	    	  thread.start();
	      }
	    }
	      catch(IOException ex) {
	    	     System.err.println(ex);
	      	}
	    }
	    
	  	//Gets the connection on the server side
	    private void getConnection(){
	    	try {
				jdbc.getConnection();
				jta.append("Connected to Database" + "\n");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    	
	    }
}