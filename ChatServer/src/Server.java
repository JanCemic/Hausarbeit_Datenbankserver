import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
private ArrayList<Client> clienten = new ArrayList<Client>();
private ServerSocket server;
private BufferedReader brs;
private InputStreamReader isr;

public ArrayList<Client> getClienten() {
	return clienten;
}

public void setClienten(ArrayList<Client> clienten) {
	this.clienten = clienten;
}

public ServerSocket getServer() {
	return server;
}

public void setServer(ServerSocket server) {
	this.server = server;
}

public BufferedReader getBrs() {
	return brs;
}

public void setBrs(BufferedReader brs) {
	this.brs = brs;
}

public InputStreamReader getIsr() {
	return isr;
}

public void setIsr(InputStreamReader isr) {
	this.isr = isr;
}


public Server(ServerSocket server, BufferedReader brs, InputStreamReader isr) {
	this.server = server;
}


/**
 *fügt einen neuen Server hinzu
 */
public void addClient() {
	System.err.println("add Client...");
	
	System.err.println("fertig: add Client!");
}

/**
 * starten den Server
 */
public void start(){
	System.err.println("start...");	
	try {
		server = new ServerSocket(8989);
	} catch (IOException e) {
		e.printStackTrace();
	}
	System.err.println("fertig: start!");
	}	
}
