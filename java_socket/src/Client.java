// A Java program for a Client
//import java.nio.charset.StandardCharsets;
import java.net.*;
import java.io.*; 

public class Client 
{ 
	private Socket socket = null; 
	BufferedWriter out = null;
	BufferedReader in = null;
	BufferedReader stdIn = null; 
        
        public void ketnoi(String address, int port) throws IOException
        {
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
            
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            stdIn = new BufferedReader(new InputStreamReader(System.in));
        }
        
        public String gui(String line, String line1) throws IOException
        {
            line = stdIn.readLine();
            System.out.println("Client sent: " + line);
            out.write(line);
            out.newLine();
            out.flush();
            line1=in.readLine();
            System.out.println("Server sent: " + line1);
            return line;
        }

	public Client(String address, int port, String line) throws UnknownHostException, IOException
	{ 
                ketnoi(address, port);
                String line1="abc";
		while (!line.equals("Over")) 
		{ 
                    line=gui(line,line1);
		} 
		in.close(); 
		out.close(); 
                stdIn.close();
		socket.close(); 
	} 

	public static void main(String args[]) throws UnknownHostException, IOException 
	{ 
		Client client = new Client("127.0.0.1", 5000,""); 
	} 
}