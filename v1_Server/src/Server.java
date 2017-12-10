import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Jan Cemic
 */

public class Server{
	
	static ArrayList<Mensch> personen = new ArrayList<Mensch>();
	static ArrayList<String> com = new ArrayList<String>();
	static ServerSocket server;
	static Socket client;
	static OutputStream out;
	static PrintWriter writer;
	static InputStream in;
	static BufferedReader reader;
	static Boolean arbeitend = false;
	
/**
 * Startet den Server und bereitet ihn vor
 * @param ags
 */
public static void main(String ags[]) {
	start();	
	System.out.println("Bereit!");
	try {	
		String s;
		
		while(true) {
			menu();
			s = reader.readLine();
				System.out.print("Arbeitsauftrag: " + s);
				
			writer.write(s + "\n");
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

/**
 * neuene Eintrag hinzufügen
 */
public static void add() {
	Scanner scanner = new Scanner(System.in);
	String name;
	String plz;
	String strasse;
	String hausnummer; 
	String telefonnummer;
	
		writer.write("Gib den Namen ein: ");
	name = scanner.next();
		writer.write("Gib die Postleitzahl ein: ");
	plz = scanner.next();
		writer.write("Gib die Strasse ein: ");
	strasse = scanner.next();
		writer.write("Gib die Hausnummer ein: ");
	hausnummer = scanner.next();
		writer.write("Gib die Telefonnummer ein: \n");
	telefonnummer = scanner.next();
	scanner.close();
	
	stop();
	
	Mensch mensch = new Mensch(name, plz, strasse, hausnummer, telefonnummer);
	personen.add(mensch);
	
	}

/**
 * alle Einträge ausgeben
 */
public static void ausgeben() {
		writer.write("Alle Eintraege");
		writer.write("Index\tName\t\t Plz\t\t Strase\t\t Nr.\t Telefonnummer");
		for(int i = 0; i < personen.size(); i++) {
			writer.write(i + ":\t" + personen.get(i).getName() + "\t\t");
			writer.write(personen.get(i).getPlz() + "\t\t");
			writer.write(personen.get(i).getStrasse() + "\t\t");
			writer.write(personen.get(i).getHausnummer() + "\t\t");
			writer.write(personen.get(i).getTelefonnummer() + "\t\t\n");
}
}

/**
 * bestimmten Eintrag ausgeben
 * 	ausgben(int StelleDesObejktesImArray)
*/
public static void ausgeben(int i) {
	writer.write("Alle Eintraege");
	writer.write("Index\tName\t\t Plz\t\t Strase\t\t Nr.\t Telefonnummer");
		
	writer.write(i + ":\t" + personen.get(i).getName() + "\t\t");
	writer.write(personen.get(i).getPlz() + "\t\t");
	writer.write(personen.get(i).getStrasse() + "\t\t");
	writer.write(personen.get(i).getHausnummer() + "\t\t");
	writer.write(personen.get(i).getTelefonnummer() + "\t\t\n");
}

/**
 * besteheneden Eintrag löschen
 */
public static void loeschen() {	
		writer.write("Welchen der einträge willst du löschen?");
	ausgeben();
	Scanner scanner = new Scanner(System.in);
	int i = scanner.nextInt();
		writer.write("Den Eintrag "+ personen.get(i).toString() + "löschen?\n y/n");
	String s = scanner.next();
	if(s.equals("y")) {
		String deleted = personen.get(i).toString();
		personen.remove(i);
			writer.write(deleted + " wurde gelöscht\n");
	}
	scanner.close();
	}

/**
 * bestehendne Eintrag bearbeiten
 */
public static void bearbeiten() {
		writer.write("Welchen Eintrag willst du bearbeiten?");
	ausgeben();
	Scanner scanner = new Scanner(System.in);
	int i = scanner.nextInt();
	ausgeben(i);
		writer.write("Schreibe den Eintrag neu: \n");
	add();
	scanner.close();
}

/**
 * listet alle Kommandos auf
 * übergibt als erstes die Anzahl der Kommandos
 */
public static void listCom() {
	fillCom();
	writer.write(comSize());
	writer.flush();
	writer.write("Was willst du tun?\n");
	writer.flush();
	for(int i = 0; i < com.size(); i++){
		writer.write(i + com.get(i)+ "\n");
		writer.flush();
	}
	writer.write("Exit zum verlassen!\n");
	writer.flush();
}

/** 
* Übergibt die Anzahl der Kommandos
 * @return
 */
public static int comSize() {
	return com.size();
}

/**
 * füllt die Liste mit allen Kommandos
 */
public static void fillCom(){
	com.add("Ausgeben\n");
	com.add("Hinzufügen\n");
	com.add("Bearbeiten\n");
	com.add("Löschen\n");
}

/**
 * startet den Server
 */
public static void start() {
	try {
	server = new ServerSocket(5555);
		System.out.println("Warte auf Verbindung.");
	client = server.accept();
	out = client.getOutputStream();
	writer = new PrintWriter(out);
	in = client.getInputStream();
	reader = new BufferedReader(new InputStreamReader(in));
	reader = null;
	writer.write("ping\n");
	}catch(IOException e) {
		e.printStackTrace();
	}
}

/**
 * teilt dem Clienten mit, dass der Server fertig gesendet hat
 */
public static void stop() {
	writer.write("fertig\n");
}

/**
 * das Menü anzeigen
 */
public static void menu() {
	try {
		listCom();
		int i;
		i = reader.read();
		
		switch(i) {
			case(0):
				ausgeben();
				break;
			case(1):
				add();
				break;
			case(2):
				bearbeiten();
				break;
			case(3):
				loeschen();
			default:
				writer.write("Try again!\n");
				break;	
			}
		stop();
	}catch(IOException e) {
		e.printStackTrace();
		}
	}
}