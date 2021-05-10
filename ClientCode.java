
// Maglupay, Jemuel - Estañero, Cesar Jr. - Marsamolo, Ralph Ryan

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientCode {

	public static void main(String[] args) {
		Socket s;
		try {
			InputStreamReader ir = new InputStreamReader(System.in); // take input from user from the client 
			BufferedReader b1 = new BufferedReader(ir);		
			s = new Socket("127.0.0.1", 8821);			// create socket 
			InputStreamReader in = new InputStreamReader(s.getInputStream());
			BufferedReader br = new BufferedReader(in);
			PrintWriter pn = new PrintWriter(s.getOutputStream(), true); // To get the data from the server 
			System.out.println("***Welcome to Echo Server***");
			System.out.print("Client: ");
			String get;
			do {                // does a do while loop and only stops when the client inputs 'end' word
				get = b1.readLine(); // get string from the user
				pn.println(get); // sending the string to the server
				System.out.println("Server: "+br.readLine());  // Prints the string from the server
				System.out.print("Client: ");
				
			}while(get.compareTo("exit")!=0); // compares the string and stops the loop when condition is true
			System.out.println("Connection Ended");
		}catch(UnknownHostException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}