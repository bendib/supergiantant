package Algorithme.Graphe;

public class Noeud {
	private int id;
	private String nom; // à voir
		
	public Noeud(int id) {
		this.id = id;
	}

	public int getId() { 
		return id;
	}

	public boolean compareTo(Noeud noeudArrivee) {
		
		if(this.nom == noeudArrivee.nom && this.getId() == noeudArrivee.getId())
			return true;
		return false;
	}

	/*public ArrayList<Arrete> getArretes() {
		// TODO Auto-generated method stub
		return null;
	}*/

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNom() {
		return nom;
	}

}
