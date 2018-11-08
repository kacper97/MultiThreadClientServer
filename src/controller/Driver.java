package controller;
import java.sql.SQLException;

import utils.Connector;
import view.ClientA2;
import view.MultiThreadedServerA2;
public class Driver {

	/**
		 * Connect to the DB
	 * @throws SQLException 
		 */
		public static void main(String[] args) throws SQLException {
			Connector jdbc = new Connector();
	        try {
	            jdbc.getConnection();
	            System.out.println("Connected to database");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        
	        //run gets the result set
	        MultiThreadedServerA2 server = new MultiThreadedServerA2();

	        jdbc.run();
	        
	        ClientA2 driver = new ClientA2();
	        
	      
		

		}
	}

