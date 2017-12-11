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
	fillCom();
		System.out.println("Bereit!");
	
	//String name, String plz, String strasse, String hausnummer, String telefonnummer
	personen.add(new Mensch("asdf", "123", "asdf", "asdf", "asdf"));	
	personen.add(new Mensch("asdrtrt4tf", "123", "asdf", "asdf", "asdf"));	
	personen.add(new Mensch("asd2342f", "123", "asdf", "asdf", "asdf"));	
	while(true) {
		menu();
	}	
}

/**
 * neuene Eintrag hinzufügen
 */
public static void add() {
		System.out.println("hinzufügen...");
	String name;
	String plz;
	String strasse;
	String hausnummer; 
	String telefonnummer;
	try {
		writer.write("Gib den Namen ein: \n");
		stop();
		name = reader.readLine();
	
		writer.write("Gib die Postleitzahl ein: \n");
		writer.flush();
		stop();
		plz = reader.readLine();
	
		writer.write("Gib die Strasse ein: \n");
		stop();
		strasse = reader.readLine();
		
		writer.write("Gib die Hausnummer ein: \n");
		stop();
		hausnummer = reader.readLine();
	
		writer.write("Gib die Telefonnummer ein: \n");
		stop();
		telefonnummer = reader.readLine();
		
		System.out.println("erstelle neuen Eintrag...");

		personen.add(new Mensch(name, plz, strasse, hausnummer, telefonnummer));
		
		}catch(IOException e) {
			e.printStackTrace();
		}
	System.out.println("fertig : hinzufügen!");
	}

/**
 * alle Einträge ausgeben
 */
public static void ausgeben() {
		System.out.println("Gebe aus..");
	writer.write("Alle Eintraege\n");
	writer.write("Index\tName\t\t Plz\t\t Strase\t\t Nr.\t Telefonnummer\n");
	writer.flush();
	for(int i = 0; i < personen.size(); i++) {
		writer.write(i + ":\t" + personen.get(i).getName() + "\t\t");
		writer.write(personen.get(i).getPlz() + "\t\t");
		writer.write(personen.get(i).getStrasse() + "\t\t");
		writer.write(personen.get(i).getHausnummer() + "\t\t");
		writer.write(personen.get(i).getTelefonnummer() + "\t\t\n");
		writer.flush();
	}
	System.out.println("fertig: ausgeben!");
}

/**
 * bestimmten Eintrag ausgeben
 * 	ausgben(int StelleDesObejktesImArray)
*/
public static void ausgeben(int i) {
		System.out.println("gebe aus...");
	writer.write("Alle Eintraege");
	writer.write("Index\tName\t\t Plz\t\t Strase\t\t Nr.\t Telefonnummer\n");
	writer.flush();
		
	writer.write(i + ":\t" + personen.get(i).getName() + "\t\t");
	writer.write(personen.get(i).getPlz() + "\t\t");
	writer.write(personen.get(i).getStrasse() + "\t\t");
	writer.write(personen.get(i).getHausnummer() + "\t\t");
	writer.write(personen.get(i).getTelefonnummer() + "\t\t\n");
		System.out.println("fertig: ausgeben!");
}

/**
 * bestimmten Eintrag löschen
 */
public static void loeschen(String s) {	
		System.out.println("lösche Index: " + s);
	int	i = Integer.valueOf(s);
	personen.remove(i);	
		System.out.println("fertig: löschen Index: " + s + " !");
}

/**
 * besteheneden Eintrag wählen und löschen
 */
public static void loeschen() {	
		System.out.println("löschen...");
	String s  = " ";
		System.out.println("liste Einträge auf...");
	ausgeben();
	writer.write("Welcher Eintrag soll gelöscht werden?\n");
	stop();
	try{
		s = reader.readLine();
	}catch(IOException e) {
		e.printStackTrace();
	}
	int i = Integer.valueOf(s);
	personen.remove(i);	
		System.out.println("fertig: löschen!");
	}

/**
 * bestehendne Eintrag bearbeiten
 */
public static void bearbeiten() {
		System.out.println("bearbeiten...");
	writer.write("Welchen Eintrag willst du bearbeiten?");
	// !TODO! nur einträge bearbeiten die existieren!
	ausgeben();
	stop();
	String s = " ";
	try {
		s = reader.readLine();
	}catch(IOException e) {
		e.printStackTrace();
	}
	add();
	loeschen(s);
		System.out.println("fertig: bearbeiten!");
}

/**
 * listet alle Koandos auf
 */
public static void listCom() {
		System.out.println("Liste Komandos auf...");
	writer.write("Was willst du tun?\n");
	writer.flush();
	for(int i = 0; i < com.size(); i++){
		writer.write(i+1 + ". " + com.get(i)+ "\n");
		writer.flush();
	}
	writer.write("Exit zum verlassen!\n");
	writer.flush();
		System.out.println("fertig: auflisten!");
}

/**
 * füllt die Liste mit allen Kommandos
 */
public static void fillCom(){
		System.out.println("fülle die Komandos...");
	com.add("Ausgeben\n");
	com.add("Hinzufügen\n");
	com.add("Bearbeiten\n");
	com.add("Löschen\n");
		System.out.println("fertig: auffüllen!");
}

/**
 * startet den Server
 */
public static void start() {
		System.out.println("starte...");
	try {
	server = new ServerSocket(5555);
		System.out.println("Warte auf Verbindung.");
	client = server.accept();
	out = client.getOutputStream();
	writer = new PrintWriter(out, true);
	in = client.getInputStream();
	reader = new BufferedReader(new InputStreamReader(in));
	}catch(IOException e) {
		e.printStackTrace();
	}
		System.out.println("fertig: starten!");
}

/**
 * das Menü anzeigen
 */
public static void menu() {
		System.out.println("gebe menü aus...");
	try {
		listCom();
		stop();
		String s = "0";
		int i;
		s = reader.readLine();
			System.out.println(s);
		i = Integer.valueOf(s);
		switch(i) {
			case(1):
				ausgeben();
				break;
			case(2):
				add();
				break;
			case(3):
				bearbeiten();
				break;
			case(4):
				loeschen();	
			default:
				writer.write("Try again!\n");
				menu();
				break;	
			}
		System.out.println("menü durchlaufen!");
	}catch(IOException e) {
		e.printStackTrace();
		}
		System.out.println("fertig: menü!");
}

/**
 * Schlüsselwort an den Client das dieser sendet
 */
public static void stop() {
	writer.write("7789\n");
	writer.flush();
	}

}