package Algorithme.AlgorithmeFourmis;

import java.util.ArrayList;

import Algorithme.Graphe.Noeud;

/**
 *  Class Fourmis : Représente une entitée fourmis dans l'algorithme des fourmis
 */
public class Fourmis
{
	enum Etat{ 
		CherchePremierNoeud,
		ParcoursGraphe, 
		Rentre
	};
	private int vitesseEvapPheromone;
	private int nbrePheromonesADeposer;
	private ArrayList<Noeud> listeNoeudsVisites;
	private Etat etat;
	private AlgoFourmis algo;
	
	public Fourmis(int vitesseEvapPheromone,int nbrePheromoneDeposes, AlgoFourmis algo)
	{
		this.vitesseEvapPheromone = vitesseEvapPheromone;
		this.nbrePheromonesADeposer = nbrePheromoneDeposes;
		//this.setEtat(Etat.CherchePremierNoeud);
		this.setEtat(Etat.ParcoursGraphe);
		this.listeNoeudsVisites = new ArrayList<Noeud>();
		this.setAlgo(algo);
	}
	
	// Non fonctionnel
	public void trouverChemin()
	{
		double poidsMinimum = 9999999;
		Noeud noeudSuivant = null;
		/* Le noeud o� se situe la fourmis est le dernier noeud qui a �t� visit� */
		Noeud noeudCourant = listeNoeudsVisites.get(listeNoeudsVisites.size()-1);
		if(etat == Etat.ParcoursGraphe)
		{
			for(int i = 0; i < algo.getNbreNoeuds();i++)
			{//On regarde s'il y a un chemin entre la ville o� est la fourmis et une autre ville
				double poids = algo.getProbleme().getPoids(noeudCourant, new Noeud(i));
				if(poids != 0)
				{
					/*if(!aDejaEteVisite(i) && poidsMinimum > poids)
					{
						poidsMinimum = poids;
						noeudSuivant = i;
					}*/
				}
			}
			if(noeudSuivant != null)
			{
				listeNoeudsVisites.add(noeudSuivant);
			}
			else//on n'a pas trouv� d'autre noeud donc la fourmis doit rentrer
				setEtat(Etat.Rentre);
		}
	}
	
	public void rentrer()
	{
		if(etat == Etat.Rentre)
		{
			for(int j = listeNoeudsVisites.size()-1;j > 0;j--)
			{
				algo.deposerPheromone(listeNoeudsVisites.get(j),listeNoeudsVisites.get(j-1), this.nbrePheromonesADeposer);
			}
		}
	}
	
	public boolean aDejaEteVisite(Noeud noeud)
	{
		for(int i = 0;i < listeNoeudsVisites.size();i++)
		{
			if(listeNoeudsVisites.get(i).compareTo(noeud))
				return true;			
		}
		return false;
	}
	
	public int getVitesseEvapPheromone() {
		return vitesseEvapPheromone;
	}
	public void setVitesseEvapPheromone(int vitesseEvapPheromone) {
		this.vitesseEvapPheromone = vitesseEvapPheromone;
	}
	public int getNbrePheromonesADeposer() {
		return nbrePheromonesADeposer;
	}
	public void setNbrePheromonesADeposer(int nbrePheromonesADeposer) {
		this.nbrePheromonesADeposer = nbrePheromonesADeposer;
	}
	public void setListeNoeudsVisites(ArrayList<Noeud> listeNoeudsVisites) {
		this.listeNoeudsVisites = listeNoeudsVisites;
	}
	public ArrayList<Noeud> getListeNoeudsVisites() {
		return listeNoeudsVisites;
	}
	public void setAlgo(AlgoFourmis algo) {
		this.algo = algo;
	}
	public AlgoFourmis getAlgo() {
		return algo;
	}
	public void setEtat(Etat etat) {
		this.etat = etat;
	}
	public Etat getEtat() {
		return etat;
	}

}
