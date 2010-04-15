package Algorithme.AlgorithmeFourmis;

import java.util.ArrayList;

import Algorithme.AlgorithmeFourmis.Fourmis.Etat;
import Algorithme.Graphe.ArreteList;
import Algorithme.Graphe.Graphe;
import Algorithme.Graphe.NoeudList;

/**
 *  Class AlgoFourmis : 
 */
public class AlgoFourmis{

	private int nbreFourmis;
	private int nbreIterations;
	private int vitesseEvapPheromone;
	private ArrayList<Fourmis> listeFourmis;
	private Graphe probleme;
	private Graphe resultant;//c'est dans ce graphe que l'on d�pose le ph�romone
	
	/*
	 * Constructeur de la classe fourmis
	 */
	public AlgoFourmis(int nbreFourmis, int nbreIterations, int vitesseEvaporationPheromone, Graphe probleme)
	{
		listeFourmis = new ArrayList<Fourmis>();
		this.nbreFourmis = nbreFourmis;
		this.nbreIterations = nbreIterations;
		this.setProbleme(probleme);
		this.vitesseEvapPheromone = vitesseEvaporationPheromone;
		
		resultant = (Graphe) probleme.copierGraphe();//probl�me les deux �l�ments sont modifi�s en m�me temps
		
		resultant.viderInformations();//On efface tous les poids des arr�tes afin de d�poser le ph�romone

	}
	
	/*
	 * Fonction permettant de cr�er l'ensemble des fourmis qui seront utilis�es pour l'algorithme
	 */
	public void creerFourmis(NoeudList noeudDepart, int nbrePheromoneADeposer)
	{
		for(int j=0; j < nbreFourmis;j++)
		{
			Fourmis f = new Fourmis(nbrePheromoneADeposer,this);
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
			listeFourmis.get(j).ajouterNoeudVisite(chemin.getArrivee());
			listeFourmis.get(j).setEtat(Etat.ParcoursGraphe);
		}
	}
	
	/*
	 * Proc�dure permettant de cr�er l'ensemble des fourmis et d'ex�cuter l'algorithme des fourmis
	 */
	public void traiterProbleme(NoeudList noeudDepart, int nbrePheromoneADeposer)
	{
		this.creerFourmis(noeudDepart, nbrePheromoneADeposer);
		this.affecterPremierNoeud();
		
		for(int i=0; i < nbreIterations;i++)
		{
			for(int j=0; j < listeFourmis.size();j++)
			{
				if(listeFourmis.get(j).getEtat()== Etat.ParcoursGraphe)
					listeFourmis.get(j).trouverChemin();
				else if(listeFourmis.get(j).getEtat()== Etat.Rentre)
					listeFourmis.get(j).rentrer();
			}
			if((i % vitesseEvapPheromone) == 0 && i != 0)
				this.misAJourPheromone();
		}
	}
	
	/*
	 * Proc�dure permettant de d�poser du ph�romone sur une arr�te dont les noeuds sont pass�s en param�tre
	 */
	public void deposerPheromone(NoeudList noeudDepart, NoeudList noeudArrivee, int nbrePheromones)
	{
		resultant.setPoids(noeudDepart, noeudArrivee, nbrePheromones);
	}
	
	/*
	 * 
	 */
	public void misAJourPheromone()
	{
		System.out.println("Mis � jour des pheromones");
	}
	
	/* Getters et setters des attributs de la classe */
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

	public int getVitesseEvapPheromone() {
		return vitesseEvapPheromone;
	}

	public void setVitesseEvapPheromone(int vitesseEvapPheromone) {
		this.vitesseEvapPheromone = vitesseEvapPheromone;
	}

}
