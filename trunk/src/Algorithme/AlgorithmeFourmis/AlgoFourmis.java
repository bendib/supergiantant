package Algorithme.AlgorithmeFourmis;

import java.util.ArrayList;

import Algorithme.Graphe.ArreteList;
import Algorithme.Graphe.Graphe;
import Algorithme.Graphe.NoeudList;

/**
 *  Class AlgoFourmis : 
 */
public class AlgoFourmis{

	private int nbreFourmis;
	private int nbreIterations;
	private ArrayList<Fourmis> listeFourmis;
	private Graphe probleme;
	private Graphe resultant;//c'est dans ce graphe que l'on d�pose le ph�romone
	
	public AlgoFourmis(int nbreFourmis, int nbreIterations, Graphe probleme)
	{
		listeFourmis = new ArrayList<Fourmis>();
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
	
	/*
	 * Fonction permettant de cr�er l'ensemble des fourmis qui seront utilis�es pour l'algorithme
	 */
	public void creerFourmis(NoeudList noeudDepart, int vitesseEvaporation,int nbrePheromoneADeposer)
	{
		for(int j=0; j < nbreFourmis;j++)
		{
			Fourmis f = new Fourmis(nbrePheromoneADeposer,vitesseEvaporation,this);
			f.ajouterNoeudVisite(noeudDepart);
			listeFourmis.add(f);
		}
	
	}
	
	/*
	 * Fonction affectant les fourmis al�atoirement sur les diff�rents noeuds suivants le d�part
	 */
	public void affecterPremierNoeud()
	{
		NoeudList noeudDepart = listeFourmis.get(0).getListeNoeudsVisites().get(0);
		ArrayList<ArreteList> listeDestinations = noeudDepart.getDestinations();
		int random = -1;//permet de d�finir al�atoirement le chemin qui doit �tre pris
		
		for(int j=0; j < listeFourmis.size();j++)
		{
			random = (int)(Math.random() * (listeDestinations.size()));
			ArreteList chemin = listeDestinations.get(random);
			listeFourmis.get(j).ajouterNoeudVisite((NoeudList) chemin.getArrivee());
		}
			
	}
	
	
	public void traiterProbleme()
	{
				
		for(int i=0; i < nbreIterations;i++)
		{
			
			
			
			/*if(i == vitesseEvaporationPheromone)
				this.misAJourPheromone();
			*/
		}
		
	}
	
	
	
	
	public void deposerPheromone(NoeudList noeudDepart, NoeudList noeudArrivee, int nbrePheromones)
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

	public void setListeFourmis(ArrayList<Fourmis> listeFourmis) {
		this.listeFourmis = listeFourmis;
	}

	public ArrayList<Fourmis> getListeFourmis() {
		return listeFourmis;
	}
	
}
