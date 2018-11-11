package view;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
	
public class ClientA2 extends JFrame {
	  // Text field for inputing Student Number
	  private JTextField jtf = new JTextField();

	  // Text area to display contents obtained from server
	  private JTextArea jta = new JTextArea();
	  
	  // IO streams
	  private DataOutputStream toServer;
	  private DataInputStream fromServer;
	  private final JButton btnEnter = new JButton("ENTER");
	  private final JButton btnQuit = new JButton("QUIT");
	  private final JLabel lblInsertStudent = new JLabel("Insert 8* Student Number Below Please");

	  public static void main(String[] args) {
	    new ClientA2();
	  }

	  public ClientA2() {
	    // Panel p to hold the labels and text fields and buttons
	    JPanel p = new JPanel();
	    p.setLayout(new BorderLayout());
	    p.add(new JLabel("Client"), BorderLayout.WEST);
	    getContentPane().setLayout(new BorderLayout());
	    getContentPane().add(p, BorderLayout.NORTH);
	    p.add(lblInsertStudent, BorderLayout.SOUTH);
	    JScrollPane scrollPane = new JScrollPane(jta);
	    getContentPane().add(btnQuit, BorderLayout.SOUTH);
	    getContentPane().add(scrollPane, BorderLayout.CENTER);
	    scrollPane.setColumnHeaderView(jtf);
	    jtf.setHorizontalAlignment(JTextField.RIGHT);
	    getContentPane().add(btnEnter, BorderLayout.EAST);
	    
	    //Buttons + Actions
	    btnEnter.addActionListener(new Listener()); // Giving it the same implementation as pressing enter on keyboard
	    btnQuit.addActionListener(e -> setVisible(false));//Quit button that closes the app (just makes it invisible)
	    jtf.addActionListener(new Listener()); 
	  
	    // Window
	    setTitle("Client");
	    setSize(530, 300);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    setVisible(true); // Showing the frame

	    try {
	      // Create a socket to connect to the server
	      Socket socket = new Socket("localhost", 8000);
	      // Create an input stream to receive data from the server
	      fromServer = new DataInputStream(socket.getInputStream());
	      // Create an output stream to send data to the server
	      toServer = new DataOutputStream(socket.getOutputStream());
	    }
	    //If Connection has an error
	    catch (IOException ex) {
	      jta.append(ex.toString() + '\n');
	    }
	  }

	  //When the Enter button is pressed /  or when Keyboard enter is pressed
	  private class Listener implements ActionListener {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	      try {
	        // Get the Student Number from the text field
	        int studentNu = Integer.parseInt(jtf.getText());

	        // Send the student number to the server
	        toServer.writeInt(studentNu);
	        toServer.flush(); 
	        
	        // Getting fields from the servers connected database
	        int studentID = fromServer.readInt();
	        int studentNuRet = fromServer.readInt();
	        String firstName = fromServer.readUTF();
	        String secondName = fromServer.readUTF();
	    
	        // Display to the text area
	        jta.setEditable(false);
	        jta.append("Student Number entered is " + studentNu + "\n");
	        jta.append("Welcome "+ studentNuRet +".. You are now connected to the Server"+ '\n');
	        jta.append( firstName + " " + secondName + "  Your student ID Is: " + studentID + "  Your Student Number is: "+ studentNuRet +"\n"); 
	      
	      }
	      // if the number is incorrect  
	      catch (IOException ex) {
	    	  String number = jtf.getText();
	    	  jta.append("Sorry, The Number "+ number+" is incorrect. You are not a registered student " + "\n");
	    	  jtf.setText("Next time please insert a valid number");
	    	  jtf.setEditable(false);
	        System.err.println(ex);
	      }
	    }
	  }
}