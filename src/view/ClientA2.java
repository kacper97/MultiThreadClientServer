package view;
	import java.io.*;
	import java.net.*;
	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	
public class ClientA2 extends JFrame {
	

	  // Text field for receiving radius
	  private JTextField jtf = new JTextField();

	  // Text area to display contents
	  private JTextArea jta = new JTextArea();

	  // IO streams
	  private DataOutputStream toServer;
	  private DataInputStream fromServer;
	  private final JButton btnEnter = new JButton("ENTER");

	  public static void main(String[] args) {
	    new ClientA2();
	  }

	  public ClientA2() {
	    // Panel p to hold the label and text field
	    JPanel p = new JPanel();
	    p.setLayout(new BorderLayout());
	    p.add(new JLabel("Enter Student Number Below Please"), BorderLayout.WEST);

	    getContentPane().setLayout(new BorderLayout());
	    getContentPane().add(p, BorderLayout.NORTH);
	    
	    btnEnter.addActionListener(new Listener());
	    
	    p.add(btnEnter, BorderLayout.EAST);
	    JScrollPane scrollPane = new JScrollPane(jta);
	    getContentPane().add(scrollPane, BorderLayout.CENTER);
	    scrollPane.setColumnHeaderView(jtf);
	    jtf.setHorizontalAlignment(JTextField.RIGHT);

	    jtf.addActionListener(new Listener()); // Register listener

	    setTitle("Client");
	    setSize(500, 300);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true); // It is necessary to show the frame here!

	    try {
	      // Create a socket to connect to the server
	      Socket socket = new Socket("localhost", 8000);
	      // Socket socket = new Socket("130.254.204.36", 8000);
	      // Socket socket = new Socket("drake.Armstrong.edu", 8000);

	      // Create an input stream to receive data from the server
	      fromServer = new DataInputStream(socket.getInputStream());

	      // Create an output stream to send data to the server
	      toServer = new DataOutputStream(socket.getOutputStream());
	    }
	    catch (IOException ex) {
	      jta.append(ex.toString() + '\n');
	    }
	  }

	  private class Listener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	      try {
	        // Get the radius from the text field
	        int studentNu = Integer.parseInt(jtf.getText());
	        	// first number =20018384 
	        // Send the radius to the server
	        toServer.writeInt(studentNu);
	        //server does get
	       
	        toServer.flush();
	        System.out.println("printed");
	        // Get area from the server
	        int studentID = fromServer.readInt();
	        int studentNuRet = fromServer.readInt();
	        String firstName = fromServer.readUTF();
	        String secondName = fromServer.readUTF();
	        

	        // Display to the text area
	        jta.append("Student Number entered is " + studentNu + "\n");
	        jta.append("Welcome "+ studentID +".. You are now connected to the Server"+ '\n');
	       /*
	        if (studentNu != studentID) {
	    	
	    	jta.append(" Sorry "+ studentNu + ". You are not a registered student. Bye.");
	      }
	      */
	        jta.append(firstName + secondName + studentNuRet + studentID); 
	      }
	      catch (IOException ex) {
	        System.err.println(ex);
	      }
	    }
	  }
	}

	/*  Validate Communication by
		displaying HostName and IPAddress
		in the Client/Server windows with all messages sent
	 */

