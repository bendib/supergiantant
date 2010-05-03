package Algorithme.Graphe;

import java.util.ArrayList;

/**
 * Class GraphList
 */
public class GrapheList implements Graphe
{
	private String nom;
	private ArrayList<NoeudList> noeuds;
	
	/*
	 * Constructeur de la classe GrapheList
	 */
	public GrapheList(String nom, int nbreNoeuds)
	{
		this.nom = nom;
		noeuds = new ArrayList<NoeudList>(nbreNoeuds);
	}

	/*
	 * Fonction retournant la copie conforme du graphe qui appelle la m�thode
	 */
	public GrapheList copierGraphe()
	{
		GrapheList copie = new GrapheList(this.getNom(),this.getNbreNoeuds());
		ArrayList<NoeudList> listeNoeuds = new ArrayList<NoeudList>();
		//On cr�er la liste des noeuds (sans les arr�tes)
		for(int i = 0; i < this.getNbreNoeuds(); i++)
		{
			NoeudList copieNoeud = new NoeudList(this.getNoeuds().get(i).getId());
			listeNoeuds.add(copieNoeud);
		}
		
		for(int i = 0; i < this.getNbreNoeuds(); i++)
		{
			copie.getNoeuds().add(this.getNoeuds().get(i).copierNoeud(listeNoeuds));
		}
		return copie;
	}
	
	/*
	 * Fonction retournant la liste des noeuds suivants d'un noeud donn�
	 */
	public ArrayList<NoeudList> getSuivants(NoeudList noeud)
	{
		ArrayList<NoeudList> listeNoeuds = new ArrayList<NoeudList>();
		ArrayList<ArreteList> listeArretes = noeud.getDestinations();
		
		for(int i=0; i < listeArretes.size();i++)
		{
			listeNoeuds.add(listeArretes.get(i).getArrivee());
		}
		return listeNoeuds;
	}

	/*
	 * Fonction v�rifiant qu'il y a un chemin (arr�te) entre les deux noeuds pass�s en param�tre 
	 */
	public boolean checkTrajet(NoeudList noeudDepart, NoeudList noeudArrivee)
	{
		ArrayList<ArreteList> listeArretes = null;
		NoeudList nDepart = null;
		for(int j = 0; j < this.getNbreNoeuds(); j++)
		{
			if(this.getNoeuds().get(j).compareTo(noeudDepart))
			{
				nDepart = this.getNoeuds().get(j);
				listeArretes = nDepart.getDestinations();
			}
		}
		for(int i = 0; i< listeArretes.size(); i++)
		{
			if (listeArretes.get(i).checkTrajet(nDepart, noeudArrivee))
				return true;
		}
		return false;
	}

	/*
	 * Fonction retournant le poids de l'arr�te correspondant au deux noeuds pass�s en param�tre
	 * (la fonction retourne -1, s'il n'y a pas d'arr�te entre ces deux points)
	 */
	public double getPoids(NoeudList noeudDepart, NoeudList noeudArrivee)
	{
		ArrayList<ArreteList> listeArretes = null;
		NoeudList nDepart = null;
		for(int j = 0; j < this.getNbreNoeuds(); j++)
		{
			if(this.getNoeuds().get(j).compareTo(noeudDepart))
			{
				nDepart = this.getNoeuds().get(j);
				listeArretes = nDepart.getDestinations();
			}
		}
		for(int i = 0; i< listeArretes.size(); i++)
		{
			if (listeArretes.get(i).checkTrajet(noeudDepart, noeudArrivee))
				return listeArretes.get(i).getPoids();
		}
		return -1;
	}

	/*
	 * Proc�dure permettant de fixer le poids d'une arr�te dont les deux noeuds sont pass�s en param�tre
	 */
	public void setPoids(NoeudList noeudDepart, NoeudList noeudArrivee, double poids) 
	{
		ArrayList<ArreteList> listeArretes = noeudDepart.getDestinations();
		boolean estPresent = false;

		for(int i = 0; i < listeArretes.size() ;i++)
		{
			if (listeArretes.get(i).checkTrajet(noeudDepart, noeudArrivee)) {
				listeArretes.get(i).setPoids(poids);
				estPresent = true;
			}
		}
		if (!estPresent) 
			noeudDepart.addDestination(noeudArrivee, poids);
	}

	/*
	 * Proc�dure ajoutant le noeud pass� en param�tre � la liste des noeuds du graphe
	 */
	public void ajouterNoeud(NoeudList noeud)
	{
		this.noeuds.add(noeud);
	}

	/*
	 * Fonction renvoyant le nombre de noeuds du graphe
	 */
	public int getNbreNoeuds() 
	{
		return noeuds.size();
	}
	
	/*
	 * Fonction effa�ant le poids ou le nombre de pheromones des arr�tes du graphe
	 */
	public void viderInformations() 
	{
		for(int i=0; i < this.getNbreNoeuds() ;i++)
		{
			ArrayList<ArreteList> listeArretes = noeuds.get(i).getDestinations();
			for(int j=0; j < listeArretes.size() ;j++)
			{
				listeArretes.get(j).setPoids(0);
			}
		}
	}

	/*
	 * Proc�dure affichant le graphe 
	 */
	public void afficherGraphe()
	{
		System.out.println("Noeud de d�part \t Noeud d'arriv�e \t Poids");
		for(int i = 0; i < this.getNbreNoeuds();i++)
		{
			ArrayList<ArreteList> listeArretes = noeuds.get(i).getDestinations();
			for(int j = 0; j < listeArretes.size() ;j++)
			{
				System.out.println("\t"+listeArretes.get(j).getDepart().getId()+"\t\t\t" + listeArretes.get(j).getArrivee().getId()+"\t\t  "+listeArretes.get(j).getPoids());
			}
		}		
	}
	
	/*Getters et Setters des attributs de la classe */
	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public String getNom() 
	{
		return nom;
	}

	public ArrayList<NoeudList> getNoeuds() 
	{
		return noeuds;
	}

	public void setNoeuds(ArrayList<NoeudList> noeuds) 
	{
		this.noeuds = noeuds;
	}
}
