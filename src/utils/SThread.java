package utils;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

//SThread = Server Thread
public class SThread extends Thread {
	public Socket socket;

    private Connector jdbc = new Connector();

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
        
            while (true) {
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
