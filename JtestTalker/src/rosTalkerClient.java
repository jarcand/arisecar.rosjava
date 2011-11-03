import java.io.*;
import java.net.Socket;


public class rosTalkerClient {
	
	Socket clientSocket;
	
	 public boolean connect(String host, int port)
	 {
		 try {
		 clientSocket = new Socket(host, port);
		  return true;
		 } catch(IOException e)
		 {
			 return false;
		 }
		 
	 } 
		
	 public void Disconnect()
	 {
		 try {
			clientSocket.close();
		} catch (IOException e) 
		{
		}
	 }
	 
	 public boolean send(String message)
	 {
		 try {
			ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
			outToServer.writeObject(message);
			return true;
		} catch (IOException e) {
			return false;
		}
	 }
}
