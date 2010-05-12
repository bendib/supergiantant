package Algorithme.AlgorithmeFourmis;

import java.util.ArrayList;

import Algorithme.Algorithme;
import Algorithme.AlgorithmeFourmis.Fourmis.Etat;
import Algorithme.Graphe.ArreteList;
import Algorithme.Graphe.Graphe;
import Algorithme.Graphe.GrapheList;
import Algorithme.Graphe.NoeudList;

/**
 *  Class AlgoFourmis : 
 */
public class AlgoFourmis extends Algorithme{

	private int nbreFourmis;
	private int nbreIterations;
	private int vitesseEvapPheromone;
	private double nbrePheromoneAEvap;
	private ArrayList<Fourmis> listeFourmis;
	private NoeudList noeudDeDepart;
	private Graphe probleme;
	private Graphe solution;//c'est dans ce graphe que l'on d�pose le ph�romone
	
	/*
	 * Constructeur de la classe fourmis
	 */
	public AlgoFourmis(int nbreFourmis, int nbreIterations, int vitesseEvaporationPheromone, double nbrePheromoneAEvap,Graphe probleme)
	{
		listeFourmis = new ArrayList<Fourmis>();

		this.nbreFourmis = nbreFourmis;
		this.nbreIterations = nbreIterations;
		this.modifierProbleme(probleme);
		this.vitesseEvapPheromone = vitesseEvaporationPheromone;
		this.nbrePheromoneAEvap = nbrePheromoneAEvap;
		
		solution = probleme.copierGraphe() ;
		solution.viderInformations();//On efface tous les poids des arr�tes afin de d�poser le ph�romone
	}
	
	/*
	 * Fonction permettant de cr�er l'ensemble des fourmis qui seront utilis�es pour l'algorithme
	 */
	public void creerFourmis()
	{
		for(int j=0; j < nbreFourmis;j++)
		{
			Fourmis f = new Fourmis(this);
			f.ajouterNoeudVisite(noeudDeDepart);
			listeFourmis.add(f);
		}
	}
	
	/*
	 * Fonction affectant les fourmis al�atoirement sur les diff�rents noeuds suivants le d�part
	 */
	public void affecterPremierNoeud()
	{
		
		ArrayList<ArreteList> listeDestinations = noeudDeDepart.obtenirDestinations();
		int tailleListeDest = listeDestinations.size();
		int random = -1;//permet de d�finir al�atoirement le chemin qui doit �tre pris
		ArrayList<Integer> cheminDejaPris = new ArrayList<Integer>();
		boolean cheminTrouve = false;
		
		for(int j=0; j < listeFourmis.size();j++)
		{
			while(cheminTrouve == false && cheminDejaPris.size() < tailleListeDest)//boucle permettant d'affecter au moins une fourmis sur chaque destination possible
			{
				random = (int)(Math.random() * tailleListeDest);
			
				if(!cheminDejaPris.contains(random))
				{
					ArreteList chemin = listeDestinations.get(random);
					listeFourmis.get(j).ajouterNoeudVisite(chemin.obtenirArrivee());
					listeFourmis.get(j).modifierEtat(Etat.ParcoursGraphe);
					cheminTrouve = true;
					cheminDejaPris.add(random);
				}else if(cheminDejaPris.size() >= tailleListeDest)
				{
					ArreteList chemin = listeDestinations.get(random);
					listeFourmis.get(j).ajouterNoeudVisite(chemin.obtenirArrivee());
					listeFourmis.get(j).modifierEtat(Etat.ParcoursGraphe);
					cheminTrouve = true;
				}			
			}
			ArreteList chemin = listeDestinations.get(random);
			listeFourmis.get(j).ajouterNoeudVisite(chemin.getArrivee());
			listeFourmis.get(j).setEtat(Etat.ParcoursGraphe);
			cheminTrouve = false;
		}
	}
	
	/*
	 * Proc�dure permettant de cr�er l'ensemble des fourmis et d'ex�cuter l'algorithme des fourmis
	 */
	public void traiterProbleme()
	{
		this.debuter();
		this.creerFourmis();
		this.affecterPremierNoeud();
		
		for(int i=0; i < nbreIterations;i++)
		{
			for(int j=0; j < listeFourmis.size();j++)
			{
				if(listeFourmis.get(j).obtenirEtat()== Etat.ParcoursGraphe)
					listeFourmis.get(j).trouverChemin();
				else if(listeFourmis.get(j).obtenirEtat()== Etat.Rentre)
				{
					//System.out.println("La fourmis rentre !");
					listeFourmis.get(j).rentrer();
				}
			}
			if((i % vitesseEvapPheromone) == 0 && i != 0)
				this.misAJourPheromone();
			//System.out.println("Affichage des pheromones :"+i);
			//resultant.afficherGraphe();
		}
		this.arreter();
		
	}
	
	/*
	 * Proc�dure permettant de d�poser du ph�romone sur une arr�te dont les noeuds sont pass�s en param�tre
	 */
	public void deposerPheromone(NoeudList noeudDepart, NoeudList noeudArrivee, double nbrePheromones)
	{
		solution.modifierPoids(noeudDepart, noeudArrivee, solution.obtenirPoids(noeudDepart, noeudArrivee)+ nbrePheromones);
		solution.modifierPoids(noeudArrivee, noeudDepart, solution.obtenirPoids(noeudDepart, noeudArrivee));//mis � jour de l'arr�te dont les noeuds sont invers�s par rapport � la premi�re arr�te
	}
	
	/*
	 * Proc�dure mettant � jour les pheromones d�pos�s sur les chemins
	 */
	public void misAJourPheromone()
	{
		double nouveauPheromone = 0.0;
		double pheromoneEnCours =0.0;
		for(int i =0; i < solution.obtenirNoeuds().size();i++)
		{
			ArrayList<ArreteList> listeArretes = solution.obtenirNoeuds().get(i).obtenirDestinations();
			for(int j =0; j < listeArretes.size() ;j++)
			{
				pheromoneEnCours = listeArretes.get(j).obtenirPoids();
				nouveauPheromone = pheromoneEnCours - pheromoneEnCours*this.nbrePheromoneAEvap; 
				if(nouveauPheromone < 0)
					listeArretes.get(j).modifierPoids(0);
				else
					listeArretes.get(j).modifierPoids(nouveauPheromone);
			}
		}
		//System.out.println("Mis � jour des pheromones");
	}
	
	/*
	 * Proc�dure affiche r�sultat 
	 */
	public String afficherSolution(NoeudList noeud,int nbreNoeud, ArrayList<NoeudList> noeudsVisites)
	{
		String chemin = "";
		double precPheromone = 0.0;
		NoeudList noeudDepS = null;
		NoeudList noeudSuivant = null;
				
		if(nbreNoeud > 0)
		{	
			if(noeud!=null)
			{	
				noeudsVisites.add(noeud);
				chemin = ""+noeud.obtenirId();
				
				noeudDepS = NoeudList.trouverNoeud(this.solution.obtenirNoeuds(), noeud.obtenirId());
									
				if(noeudDepS != null)
				{
					ArrayList<ArreteList> listeArretes = noeudDepS.obtenirDestinations();
					for(int i=0; i < listeArretes.size();i++)
					{
						if(NoeudList.trouverNoeud(noeudsVisites, listeArretes.get(i).obtenirArrivee().obtenirId()) == null)//le noeud n'a pas encore �t� visit�
						{
							if(precPheromone < listeArretes.get(i).obtenirPoids())
							{
								noeudSuivant = listeArretes.get(i).obtenirArrivee();
								precPheromone = listeArretes.get(i).obtenirPoids();
							}
						}
					}
					if(noeudSuivant != null)
					{
						chemin += " , " + afficherSolution(noeudSuivant,nbreNoeud-1,noeudsVisites);
					}
				}				
			}
		}else
			return "";
		return chemin;
	}
	
	/*
	 * Fonction permettant de savoir si une arrete(dont les noeuds(d�part et arriv�e) sont invers�s) est pr�sente dans une liste
	 */
	public boolean arreteInverseePresente(ArrayList<ArreteList> listeArretes, ArreteList a)
	{
		for(int i=0; i<listeArretes.size();i++)
		{
			if(listeArretes.get(i).obtenirArrivee().compareTo(a.obtenirDepart()))
			{
				if(listeArretes.get(i).obtenirDepart().compareTo(a.obtenirArrivee()))
					return true;
				else
					return false;
			}
		}
		return false;
	}
	/*
	 * Fonction permettant d'obtenir la distance du plus court chemin trouv�
	 */
	public double obtenirDistance() 
	{
		ArrayList<NoeudList> listeNoeuds = new ArrayList<NoeudList>();
		double distance = calculDistance(obtenirNoeudDeDepart(), obtenirNbreNoeuds(),listeNoeuds);
		
		return distance;
	}
	
	private double calculDistance(NoeudList noeud,int nbreNoeud, ArrayList<NoeudList> noeudsVisites)
	{
		double distanceTotal = 0.0;
		double precPheromone = 0.0;
		double poids = 0.0;
	
		NoeudList noeudDepS = null;
		NoeudList noeudDepP = null;
		NoeudList noeudSuivant = null;
		
		if(nbreNoeud > 0)
		{	
			if(noeud!=null)
			{	
				noeudsVisites.add(noeud);
				noeudDepS = NoeudList.trouverNoeud(this.solution.obtenirNoeuds(), noeud.obtenirId());
				noeudDepP = NoeudList.trouverNoeud(this.probleme.obtenirNoeuds(), noeud.obtenirId());
					
				if(noeudDepS != null && noeudDepP != null)
				{
					ArrayList<ArreteList> listeArretes = noeudDepS.obtenirDestinations();
					for(int i=0; i < listeArretes.size();i++)
					{
						if(NoeudList.trouverNoeud(noeudsVisites, listeArretes.get(i).obtenirArrivee().obtenirId()) == null)//le noeud n'a pas encore �t� visit�
						{
							if(precPheromone < listeArretes.get(i).obtenirPoids())
							{
								poids = noeudDepP.obtenirDestinations().get(i).obtenirPoids();
								noeudSuivant = listeArretes.get(i).obtenirArrivee();
								precPheromone = listeArretes.get(i).obtenirPoids();
							}
						}
					}
					if(noeudSuivant != null)
					{
						distanceTotal += poids + calculDistance(noeudSuivant,nbreNoeud-1,noeudsVisites);
					}
				}				
			}
		}else
			return 0;
		return distanceTotal;
	}
	
	/* Getters et setters des attributs de la classe */
	public int obtenirNbreFourmis() {
		return nbreFourmis;
	}
	public void modifierNbreFourmis(int nbreFourmis) {
		this.nbreFourmis = nbreFourmis;
	}
	public void modifierNbreIterations(int nbreIterations) {
		this.nbreIterations = nbreIterations;
	}
	public int obtenirNbreIterations() {
		return nbreIterations;
	}
	public void modifierProbleme(Graphe probleme) {
		this.probleme = probleme;
	}
	public Graphe obtenirProbleme() {
		return probleme;
	}
	public int obtenirNbreNoeuds()
	{
		return probleme.obtenirNbreNoeuds();
	}
	public void modifierSolution(Graphe solution) {
		this.solution = solution;
	}
	public GrapheList obtenirSolution() {
		return (GrapheList)solution;
	}

	public void modifierListeFourmis(ArrayList<Fourmis> listeFourmis) {
		this.listeFourmis = listeFourmis;
	}

	public ArrayList<Fourmis> obtenirListeFourmis() {
		return listeFourmis;
	}

	public int obtenirVitesseEvapPheromone() {
		return vitesseEvapPheromone;
	}

	public void modifierVitesseEvapPheromone(int vitesseEvapPheromone) {
		this.vitesseEvapPheromone = vitesseEvapPheromone;
	}

	public void modifierNbrePheromoneAEvap(double nbrePheromoneAEvap) {
		this.nbrePheromoneAEvap = nbrePheromoneAEvap;
	}

	public double obtenirNbrePheromoneAEvap() {
		return nbrePheromoneAEvap;
	}

	public NoeudList obtenirNoeudDeDepart() {
		return noeudDeDepart;
	}

	public void modifierNoeudDeDepart(NoeudList noeudDeDepart) {
		this.noeudDeDepart = noeudDeDepart;
	}
	
}
