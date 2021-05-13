
import java.io.*;
import java.net.*;

public class ServerCode {
	static ServerSocket socket; //declares socket 
	int cl;
	public static void main(String[] args) {
		ServerCode sc = new ServerCode(); //creates object for server class
		sc.server(); // function to be called
		
	}
		void server() {
			try {
				socket = new ServerSocket(8821); //creates socket server at port 8821
				cl = 0; // initialized for number of clients connected
				while(true) {
					Socket s = socket.accept(); //accepts connections
					thread t = new thread(s); //creates a new thread object
					t.start(); //invoking the start() method
					cl++; //increments client connected				
				}		
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		class thread extends Thread{ //this creates new entry of thread 
			Socket sl;
			int i;
			
			public thread(Socket s) {
				sl = s;
				i=0;
			}
			public void run() { 
				try {
					String get = "y ";
					InputStreamReader  in = new InputStreamReader(sl.getInputStream()); //obtaining input and output streams
					BufferedReader bl = new BufferedReader(in); //reads the content of the socket
					PrintWriter pn = new PrintWriter(sl.getOutputStream(), true); //writes the same message coming from the client 
					while(true) {
						get = bl.readLine(); //read contents from the socket
						if(get.equals("exit")) {
							pn.println(get);
							break;
						}
						if(get!=null) {
							pn.println(get); //prints the same message from the client
							i++; //counts the number of client 
						}
					}
					Thread.sleep(2000); //suspends execution for 2 seconds
					cl--; //decrement the value of cl
					System.out.println("Connected Client/s: "+i+" \nRemaining Client/s: " +cl);				
				}catch(IOException e) {
					e.printStackTrace();
				}
				catch (InterruptedException e) {
                    e.printStackTrace();
				}
			}	
		}
  
}