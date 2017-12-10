package v1_Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	static OutputStream out;
	static InputStream in;
	static PrintWriter writer;
	static BufferedReader reader;
	static Scanner scanner;
	
	public static void main(String ags[]) {
	
	try {
	Socket client = new Socket("localhost", 5555);
	System.out.println("Gestartet");
	
	out = client.getOutputStream();
	in = client.getInputStream();
	writer =new PrintWriter(out);
	reader = new BufferedReader(new InputStreamReader(in));
	scanner = new Scanner(System.in);
	String nachricht = " ";
	String auftrag = " ";
	
	nachricht = reader.readLine();

	do {	
			while(!nachricht.equals("fertig")){
				nachricht = reader.readLine();
					System.out.println(nachricht);
			}
			
	nachricht = scanner.nextLine();		
	writer.write(auftrag);
	stop();
			
	}while(!nachricht.equals("exit"));
	client.close();
	scanner.close();
	
	}catch(IOException e) {
		e.printStackTrace();
	}	
	}
	public static void stop() {
		writer.write("fertig\n");
	}
}