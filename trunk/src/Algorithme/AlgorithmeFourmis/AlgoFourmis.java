package Algorithme.AlgorithmeFourmis;

import java.util.ArrayList;

import Algorithme.Graphe.Graphe;
import Algorithme.Graphe.Noeud;

/**
 *  Class AlgoFourmis : 
 */
public class AlgoFourmis{

	private int nbreFourmis;
	private int nbreIterations;
	//private int[][] pheromone;    // Utilisation du type graphe ?
	private Graphe probleme;
	private Graphe resultant;//c'est dans ce graphe que l'on d�pose le ph�romone
	
	public AlgoFourmis(int nbreFourmis, int nbreIterations, Graphe probleme)
	{
		this.nbreFourmis = nbreFourmis;
		this.nbreIterations = nbreIterations;
		this.setProbleme(probleme);
		
		resultant = probleme;
		//On efface tous les poids des arr�tes afin de d�poser le ph�romone
		resultant.vider();
	}
	
	/*Fonctions � coder
	 * 
	 * Cr�er toutes les fourmis et les affecter � un noeud d�part
	 * R�partir les fourmis sur l'ensemble des chemins possibles depuis le noeud de d�part
	 * Relancer les fourmis (une fois qu'elles sont arriv�es)
	 * Mettre � jour les pheromones (syst�me d'�vaporation)
	 * 
	 */
	
	public void traiterProbleme()
	{
		ArrayList<Fourmis> listeFourmis = new ArrayList<Fourmis>();
		Noeud noeudDepart = null;
		//Cr�ation des fourmis
		for(int j=0; j < nbreFourmis;j++)
		{
			Fourmis f = new Fourmis(10,5,this);

			f.ajouterNoeudVisite(noeudDepart);
			listeFourmis.add(f);
		}
		
		for(int i=0; i < nbreIterations;i++)
		{
			
			
			
			/*if(i == vitesseEvaporationPheromone)
				this.misAJourPheromone();
			*/
		}
		
	}
	
	
	
	
	public void deposerPheromone(Noeud noeudDepart, Noeud noeudArrivee, int nbrePheromones)
	{
		resultant.setPoids(noeudDepart, noeudArrivee, nbrePheromones);
	}
	
		
	public void misAJourPheromone()
	{
		
		
	}
	
	public int getNbreFourmis() {
		return nbreFourmis;
	}
	public void setNbreFourmis(int nbreFourmis) {
		this.nbreFourmis = nbreFourmis;
	}
	public void setNbreIterations(int nbreIterations) {
		this.nbreIterations = nbreIterations;
	}
	public int getNbreIterations() {
		return nbreIterations;
	}
	public void setProbleme(Graphe probleme) {
		this.probleme = probleme;
	}
	public Graphe getProbleme() {
		return probleme;
	}
	public int getNbreNoeuds()
	{
		return probleme.getNbreNoeuds();
	}
	public void setResultant(Graphe resultant) {
		this.resultant = resultant;
	}
	public Graphe getResultant() {
		return resultant;
	}
	
}
