package controller;
import java.sql.SQLException;

import utils.Connector;
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
	        jdbc.run();
	        //MultiThreadedServerA2 driver = new MultiThreadedServerA2();
		    //driver.frame.setVisible(true);
		}
	}

