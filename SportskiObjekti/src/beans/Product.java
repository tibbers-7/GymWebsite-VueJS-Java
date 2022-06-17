package beans;

public class Product {
	
	private String sifra;
	private String polaziste;
	private String odrediste;
	private int mesta;
	private int trajanje;
	private String presedanje;
	private double cena;
	
	public Product() {
		
	}

	public Product(String sifra, String polaziste, String odrediste, int mesta, int trajanje, String presedanje,
			double cena) {
		super();
		this.sifra = sifra;
		this.polaziste = polaziste;
		this.odrediste = odrediste;
		this.mesta = mesta;
		this.trajanje = trajanje;
		this.presedanje = presedanje;
		this.cena = cena;
	}



	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getPolaziste() {
		return polaziste;
	}

	public void setPolaziste(String polaziste) {
		this.polaziste = polaziste;
	}

	public String getOdrediste() {
		return odrediste;
	}

	public void setOdrediste(String odrediste) {
		this.odrediste = odrediste;
	}

	public int getMesta() {
		return mesta;
	}

	public void setMesta(int mesta) {
		this.mesta = mesta;
	}

	public int getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(int trajanje) {
		this.trajanje = trajanje;
	}

	public String getPresedanje() {
		return presedanje;
	}

	public void setPresedanje(String presedanje) {
		this.presedanje = presedanje;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double d) {
		this.cena = d;
	}

	

}
