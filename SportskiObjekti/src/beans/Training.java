package beans;
import utils.TrainingType;
public class Training {
	
	// Naziv
	//● Tip (grupni, personalni, teretana…)
	//● Sportski objekat kom pripada
	//● Trajanje (u minutima ili satima)
	//● Trener (ako postoji)
	//● Opis
	//● Slika
	
	private name;
	private TrainingType type;
	private SportsObject object;
	private int length;
	private Trainer trainer;
	private String description;
	private String logoPath;
}