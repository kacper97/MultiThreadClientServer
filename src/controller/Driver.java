package controller;
import java.sql.SQLException;
import view.ClientA2;
import view.MultiThreadedServerA2;
import javax.swing.*;
import java.awt.*;

/*
 * GITHUB to the project, showing all commits and working around the assignment
 * Over a time of a week.
 * https://github.com/kacper97/MultiThreadClientServer
 * Created by Kacper Woloszyn. 
 */

public class Driver extends JFrame {
	// Button for client creation
    private JButton btn_client = new JButton("Click for a new Client");

    private Driver() {
    // Creates a frame
    setLayout(new BorderLayout());
    add(btn_client, BorderLayout.CENTER);

    setTitle("Sockets and Threads APP");
    setSize(500,300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    // when button pressed launch client
    btn_client.addActionListener(e -> new ClientA2());
}

		public static void main(String[] args) throws SQLException  {
			 new Driver();
			 new MultiThreadedServerA2();
	        }
}