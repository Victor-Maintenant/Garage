package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final LinkedList<Stationnement> myStationnements = new LinkedList<>();

	public Voiture(String i) {
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}

		this.immatriculation = i;
	}

	public LinkedList<Stationnement> getMyStationnements() {
		return myStationnements;
	}


	public String getImmatriculation() {
		return immatriculation;
	}

	/**
	 * Fait rentrer la voiture au garage 
         * Précondition : la voiture ne doit pas être déjà dans un garage
	 *
	 * @param g le garage où la voiture va stationner
	 * @throws java.lang.Exception Si déjà dans un garage
	 */
	public void entreAuGarage(Garage g) throws Exception {
		if (this.estDansUnGarage()) {
			throw new Exception ("La voiture est déjà au garage");
		}
		Stationnement s = new Stationnement(this, g);
		myStationnements.add(s);
	}

	/**
	 * Fait sortir la voiture du garage 
     * Précondition : la voiture doit être dans un garage
	 *
	 * @throws java.lang.Exception si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws Exception {
		if (this.estDansUnGarage() == false) {
			throw new Exception ("La voiture n'est plus dans un garage");
		}
		this.myStationnements.getLast().terminer();
	}

	/**
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public Set<Garage> garagesVisites() {
		Set<Garage> garage = new HashSet<Garage>();
		for (int i = 0;i < this.myStationnements.size();i++) {
			garage.add(this.myStationnements.get(i).getGarage());
		}
		return garage;
	}

	/**
	 * @return vrai si la voiture est dans un garage, faux sinon 
	 */
	public boolean estDansUnGarage(){
		boolean res = false;
		if (this.myStationnements.isEmpty()) {
			res = false;
		}
		else {
			if (this.myStationnements.getLast().getFin() == null) {
			res = true;
			}
		}
		return res;
	}
	
	

	/**
	 * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste des dates d'entrée / sortie dans ce
	 * garage
	 * <br>Exemple :
	 * <pre>
	 * Garage Castres:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 *		Stationnement{ entree=28/01/2019, en cours }
	 *  Garage Albi:
	 *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
	 * </pre>
	 *
	 * @param out l'endroit où imprimer (ex: System.out)
	 */
	public void imprimeStationnements(PrintStream out) {
		String nomG;
		for (Garage g : this.garagesVisites()) {
			nomG = g.getName();
			System.out.println(g.toString()+" :");
			for(int i = 0;i < this.myStationnements.size();i++) {
				if (this.myStationnements.get(i).getGarage().getName() == nomG){
					System.out.println("	"+this.myStationnements.get(i).toString());
				}
			}
		}
	}
}
