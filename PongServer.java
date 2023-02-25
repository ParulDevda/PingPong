package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 *  Cmd to run from the terminal for this class - 
 *  
 * java /Users/pdevda/Dev/ws-personal/PingPongServer/src/Server/PongServer.java 4444
 * 
 * @author pdevda
 * @since 02/24/2023
 *
 */
public class PongServer {
    
    public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("Usage: java PongServer <port number>");
            System.exit(1);
        }
        
        int portNumber = Integer.parseInt(args[0]);
        
        try (
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
            Socket clientSocket = serverSocket.accept();     
        	
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);                   
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            
            out.println(" Pong Server is Ready !!");
            
            String inputLine;            
            while ((inputLine = in.readLine()) != null) {
        	
        	if(inputLine.equalsIgnoreCase("ping")) {
        	    out.println("Pong");
        	} else if (inputLine.equalsIgnoreCase("exit") || inputLine.equalsIgnoreCase("quit")) {
        	    out.println("Exiting");
        	    break;
        	} else {
        	    out.println("Invalid input : try Ping or Exit or Quit ");
        	}                
            }
            
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
     

}
