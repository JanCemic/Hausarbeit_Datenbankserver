/**
 * Hiermit wird die Person beschrieben
 * @author Jan Cemic
 */
public class Mensch {

	private String name;
	private String plz;
	private String strasse;
	private String hausnummer;
	private String telefonnummer;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	public String getHausnummer() {
		return hausnummer;
	}
	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}
	public String getTelefonnummer() {
		return telefonnummer;
	}
	public void setTelefonnummer(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}
	
	/**
	 * Konstruktor des Menschen
	 * Mit Name, Postleitzahl, Strasse, Hausnummer, Teefonnummer -> alles vom Typ String
	 */
	public Mensch(String name, String plz, String strasse, String hausnummer, String telefonnummer) {
		this.name = name;
		this.plz = plz;
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.telefonnummer = telefonnummer;
	}
}