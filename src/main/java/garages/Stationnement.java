package garages;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Stationnement {

	private final Voiture myCar;
	private final Garage myGarage;
	private final Date entree; // Aujourd'hui
	private Date fin;

	public Stationnement(Voiture v, Garage g) {
		this.myCar = v;
		this.myGarage = g;
		this.entree = new Date();
	}

	public Voiture getCar() {
		return myCar;
	}

	public Garage getGarage() {
		return myGarage;
	}

	public Date getEntree() {
		return entree;
	}

	public Date getFin() {
		return fin;
	}

	public void terminer() {
		fin = new Date();
	}

	public boolean estEnCours() {
		return (fin == null);
	}

	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
		return String.format("Stationnement{ entree=%s, %s }",
			dateFormat.format(entree),
			estEnCours() ? "en cours" : "sortie=" + dateFormat.format(fin)
		);
	}

}
