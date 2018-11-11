package view;
	import java.io.*;
	import java.net.*;
	import java.awt.*;
	import java.awt.event.*;
	import javax.swing.*;
	
public class ClientA2 extends JFrame {
	  // Text field for inputing Student Number
	  private JTextField jtf = new JTextField();

	  // Text area to display contents from server
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
	    
	    // Giving it the same implementation as pressing enter on keyboard
	    btnEnter.addActionListener(new Listener()); 
	    
	    p.add(btnEnter, BorderLayout.EAST);
	    JScrollPane scrollPane = new JScrollPane(jta);
	    getContentPane().add(scrollPane, BorderLayout.CENTER);
	    scrollPane.setColumnHeaderView(jtf);
	    jtf.setHorizontalAlignment(JTextField.RIGHT);

	    // Listener
	    jtf.addActionListener(new Listener()); 

	    // Window
	    setTitle("Client");
	    setSize(500, 300);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true); // Showing the frame

	    try {
	      // Create a socket to connect to the server
	      Socket socket = new Socket("localhost", 8000);

	      // Create an input stream to receive data from the server
	      fromServer = new DataInputStream(socket.getInputStream());

	      // Create an output stream to send data to the server
	      toServer = new DataOutputStream(socket.getOutputStream());
	    }
	    //If Conneection has an error
	    catch (IOException ex) {
	      jta.append(ex.toString() + '\n');
	    }
	  }

	  private class Listener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	      try {
	        // Get the Student Number from the text field
	        int studentNu = Integer.parseInt(jtf.getText());
	        // first number =20018384 
	        // other number in db = 20081344
	        // third  = 20018484
	        // Send the student number to the server
	        toServer.writeInt(studentNu);
	        toServer.flush(); 
	        
	        // Getting fields from the servers connected database
	        int studentID = fromServer.readInt();
	        int studentNuRet = fromServer.readInt();
	        String firstName = fromServer.readUTF();
	        String secondName = fromServer.readUTF();
	    
	        // Display to the text area
	        jta.append("Student Number entered is " + studentNu + "\n");
	        jta.append("Welcome "+ studentNuRet +".. You are now connected to the Server"+ '\n');
	        jta.append( firstName + " " + secondName + "  Your student ID Is: " + studentID + "  Your Student Number is: "+ studentNuRet +"\n"); 
	      
	      }
	      // if the number is incorrect  
	      catch (IOException ex) {
	    	  String number = jtf.getText();
	    	  jta.append("Sorry "+ number+" you are not a registered student, bye " + "\n");
	    	  jtf.setText("Next time please insert a valid number");
	    	  jtf.setEditable(false);
	        System.err.println(ex);
	      }
	    }
	  }
	}

