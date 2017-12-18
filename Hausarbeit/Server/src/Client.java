/**
 * @author Jan Cemic
 * Der Client tut nichts außer den Server fernzusteuern
 * Der Server liest den Input ein, bis er ein Schlüselwort empängt,
 * dann liest er einen Befehl ein und sendet diesen an den Server
 */

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
	writer =new PrintWriter(out, true);
	reader = new BufferedReader(new InputStreamReader(in));
	scanner = new Scanner(System.in);
	String input = " ";
	String output = " ";
	
//		System.out.println("los");
	do {	
		System.out.println("lese...");
			while(!input.equals("7789")){
				input = reader.readLine();
				
				if(!input.equals("7789")) {
					System.out.println(input);
				}
			}
//			System.out.println("schreibe...");
	output = scanner.nextLine();
		System.out.println(output);
	input = " ";
	writer.write(output + "\n");
	writer.flush();
			
	}while(!output.equals("exit"));
	writer.write("10\n");
		System.out.println("Beendet!");
	client.close();
	scanner.close();
	
	}catch(IOException e) {
		e.printStackTrace();
		}	
	}
}