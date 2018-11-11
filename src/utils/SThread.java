package utils;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

//SThread = Server Thread
public class SThread extends Thread {
	
	//init of socket and connector
	public Socket socket;

    private Connector jdbc = new Connector();

    //Constructor
    public SThread(Socket socket) {
    	this.socket=socket;
    }
    
    public void run(){
        // Create data input and output streams
        try {

  	      // Listen for a connection request
            DataInputStream inputFromClient = new DataInputStream(
                  socket.getInputStream());
            DataOutputStream outputToClient = new DataOutputStream(
                socket.getOutputStream());
        
            //When there is a connection
            while (true) {
    	        // Receive Student number from the client
    	        int studentNu = inputFromClient.readInt();
    	        // Result set from data base obtains the results of all rows in table of Student Number
    	        ResultSet rs = jdbc.returnRecord(studentNu);
    	       
    	        //if found result set 
               if (rs.next()) {
    			   	int studentID=  rs.getInt("SID");	
    				outputToClient.writeInt(studentID);
    				String firstName = rs.getString("FNAME");
    				String secondName = rs.getString("SNAME");
    				outputToClient.writeInt(studentNu);
    				outputToClient.writeUTF(firstName);
    				outputToClient.writeUTF(secondName);
    	  	        
    	      }
               else {
            	   // close socket
                   socket.close(); 
               }
    	      }
    	    }
    	    catch(IOException | SQLException ex) {
    	      System.err.println(ex);
    	    }
    }
}
