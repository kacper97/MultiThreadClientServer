package controller;
import java.sql.SQLException;

import view.ClientA2;
import view.MultiThreadedServerA2;
import javax.swing.*;
import java.awt.*;

public class Driver extends JFrame {
	// Button for client creation
    private JButton btn_client = new JButton("Click for Client");

    private Driver() {
 // Create frame
    setLayout(new BorderLayout());
    add(btn_client, BorderLayout.CENTER);

    setTitle("Sockets and Threads APP");
    setSize(500,300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    // Add action listener for launching client
    btn_client.addActionListener(e -> new ClientA2());
}

		public static void main(String[] args) throws SQLException  {
			 new Driver();
			 new MultiThreadedServerA2();
	        }

	}

