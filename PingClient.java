package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 
 *  Client for PingPong App  
 *  

Cmd to run from the terminal for this class - 

java /Users/pdevda/Dev/ws-personal/PingPongClientrc/Client/PingClient.java Paruls-MacBook-Pro.local 4444

Server:  Pong Server is Ready !!
ping
Client: ping
Server: Pong
ping
Client: ping
Server: Pong
pang
Client: pang
Server: Invalid input : try Ping or Exit or Quit 
exit
Client: exit
Server: Exiting

 *  
 * 
 * @author pdevda
 * @since 02/24/2023
 *
 */
public class PingClient {

    public static void main(String[] args) throws IOException {

	if (args.length != 2) {
	    System.err.println( "Usage: java PingClient <host name> <port number>");
	    System.exit(1);
	}

	String hostName = args[0];
	int portNumber = Integer.parseInt(args[1]);

	try (
		Socket clientSocket = new Socket(hostName, portNumber);
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		) {

	    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
	    String fromServer;
	    String fromUser;

	    // 
	    while ((fromServer = in.readLine()) != null) {
		System.out.println("Server: " + fromServer);
		if (fromServer.equals("Exiting"))
		    break;

		fromUser = stdIn.readLine();
		if (fromUser != null) {
		    System.out.println("Client: " + fromUser);
		    out.println(fromUser);
		}
	    }

	} catch (UnknownHostException e) {
	    System.err.println("Don't know about host " + hostName);
	    System.exit(1);
	} catch (IOException e) {
	    System.err.println("Couldn't get I/O for the connection to " + hostName);
	    System.exit(1);
	} 
    }

}
