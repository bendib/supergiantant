package InterfaceGraphique;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;

import Algorithme.AlgorithmeFourmis.AlgoFourmis;
import Algorithme.Graphe.GrapheList;
import Algorithme.Graphe.NoeudList;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Interface extends JFrame implements ActionListener
{
	private JTextField nbreIterations;
	private JTextField nbreFourmis;
	private JLabel lNomGraphe;
	private JTextField vitesseEvapPheromone;
	private JTextField nbrePheromoneAEvap;
	private GrapheList graphe;
	private NoeudList noeudDepart;
	
	public Interface()
	{
		this.setLocationRelativeTo(null);
		this.setTitle("Comparateur d'algorithmes");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		/************************ Cr�ation des diff�rents onglets *********************************************/
		JTabbedPane tab = new JTabbedPane();

		/************************ Cr�ation des diff�rents panneaux de l'interface *****************************/ 
		//JPanel pCreationGraphe = this.creerGraphe();

		/************************ Ajout des diff�rents �l�ments au container principal ************************/
		/*this.getContentPane().add(pCreationGraphe,BorderLayout.NORTH);
		pCreationGraphe.setPreferredSize(new java.awt.Dimension(638, 184));
		pCreationGraphe.setLayout(null);

			JPanel pAlgoFourmis = this.gererAlgoFourmis();
			getContentPane().add(pAlgoFourmis, BorderLayout.WEST);
			pAlgoFourmis.setPreferredSize(new java.awt.Dimension(301, 174));
			pAlgoFourmis.setLayout(null);
			
			pAlgoFourmis.setBackground(new java.awt.Color(218,218,218));
			{
				JLabel lVitesseEvapPheromone = new JLabel("Vitesse d'�vaporation\n des pheromones :");
				pAlgoFourmis.add(lVitesseEvapPheromone);
				lVitesseEvapPheromone.setBounds(17, 81, 240, 16);
			}
			{
				vitesseEvapPheromone = new JTextField(4);
				pAlgoFourmis.add(vitesseEvapPheromone);
				vitesseEvapPheromone.setBounds(247, 81, 37, 21);
			}
			{
				JLabel lNbrePheromoneAEvap = new JLabel("Nombre de pheromones\n � �vaporer :");
				pAlgoFourmis.add(lNbrePheromoneAEvap);
				lNbrePheromoneAEvap.setBounds(17, 107, 223, 16);
			}
			{
				nbrePheromoneAEvap = new JTextField(4);
				pAlgoFourmis.add(nbrePheromoneAEvap);
				nbrePheromoneAEvap.setBounds(247, 106, 37, 21);
			}
			{
				JLabel lNbreIterations = new JLabel("Nombre d'it�rations :");
				pAlgoFourmis.add(lNbreIterations);
				lNbreIterations.setBounds(18, 27, 194, 16);
			}
			{
				nbreFourmis = new JTextField(4);
				pAlgoFourmis.add(nbreFourmis);
				nbreFourmis.setBounds(247, 25, 37, 21);
			}
			{
				JLabel lNbreFourmis = new JLabel("Nombre de fourmis :");
				pAlgoFourmis.add(lNbreFourmis);
				lNbreFourmis.setBounds(18, 54, 194, 16);
			}
			{
				nbreIterations = new JTextField();
				pAlgoFourmis.add(nbreIterations);
				nbreIterations.setBounds(247, 55, 37, 21);
			}
			{
				JButton lancerAlgo = new JButton("Lancer l'algorithme des fourmis");
				pAlgoFourmis.add(lancerAlgo);
				lancerAlgo.addActionListener(this);
				lancerAlgo.setActionCommand("AlgoFourmis");
				lancerAlgo.setBounds(17, 139, 267, 23);
				}*/
		tab.addTab("Graphe", null, null, "Repr�sentation du graphe initiale");
		tab.addTab("Algorithme des fourmis", null, null, "Param�tres de l'algorithme des fourmis");
		tab.addTab("Algorithme G�n�tique", null, null, "Param�tres de l'algorithme g�n�tique");
		tab.addTab("R�sultats", null, null, "R�sultats des tests");
		add(tab);

		this.setLocationByPlatform(true);
		this.setVisible(true);
		pack();
	}

	/*
	 * Fonction cr�ant et retournant le pannel de cr�ation un graphe en fonction des donn�es que rentre l'utilisateur
	 */
	public JPanel creerGraphe()
	{
		JPanel pCreationGraphe = new JPanel();
		JLabel lcreationGraphe =  new JLabel("Cr�ation d'un graphe");
		
		
		pCreationGraphe.add(lcreationGraphe);
		lcreationGraphe.setBounds(229, 5, 200, 16);
		{
			lNomGraphe = new JLabel();
			pCreationGraphe.add(lNomGraphe);
			lNomGraphe.setText("Nom du graphe");
			lNomGraphe.setBounds(12, 21, 100, 16);
		}
		return pCreationGraphe;
	}
	
	public JPanel gererAlgoFourmis()
	{
		JPanel pAlgoFourmis = new JPanel();
		
		/******* Cr�ation des diff�rents textfield et labels pour entrer les param�tres ********/

		return pAlgoFourmis;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		if(e.getActionCommand().compareTo("AlgoFourmis")==0)
		{
			int nbreIterations = Integer.parseInt(this.nbreIterations.getText());
			int nbreFourmis = Integer.parseInt(this.nbreFourmis.getText());
			int vitesseEvapPheromone = Integer.parseInt(this.vitesseEvapPheromone.getText());
			double nbrePheromoneAEvap = Double.parseDouble(this.nbrePheromoneAEvap.getText());
			AlgoFourmis algoF = new AlgoFourmis(nbreFourmis, nbreIterations,vitesseEvapPheromone,nbrePheromoneAEvap,graphe);
			algoF.traiterProbleme(noeudDepart);
		}else if(e.getActionCommand().compareTo("AlgoGenetique")==0)
		{
			
		}
		
	}
}
