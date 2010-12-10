package banque;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;


import agence.Agence;
import agence.AgenceIntf;

//TODO: mettre a jour javadoc(return values, params, etc.)


/** Une banque regroupe une liste d'agences. L'entité Banque contien le nom de la banque et 
 * les opérations insereAgence(), retireAgence(), rechercheAgence(), listeAgences()
 * 
 * Cet objet est un objet distribuer
 * 
 * @author Bertrand BRUN et John CHARRON
 *
 */
public class Banque extends UnicastRemoteObject implements BanqueIntf {
	
	private static final long serialVersionUID = 1L;
	public String nomBanque;
	public List<AgenceIntf> agences;

	public Banque(String nomBanque) throws RemoteException{
		super();
		this.nomBanque = nomBanque;
		agences = new ArrayList<AgenceIntf>();
	}

	
	/** Permet d'insérer une nouvelle instance d'Agence dans la liste des agences de la banque. 
	 * Elle a deux paramètres : le nom de a ville et l'instance Agence.
	 * Nous supposerons qu'il existe une agence par ville au maximum.
	 * Les agences crééses sont gérés sur un autre type de serveur
	 * @param adrAgence
	 * @param agence
	 */
	public boolean insereAgence(String adrAgence, Agence agence){
		if(this.rechercheAgence(adrAgence) == null){
			this.agences.add(agence);
			return true;
		}
			return false;
	}
	
	/** Retire une agence à l'aide de sa ville passée en paramètre 
	 * @param adrAgence
	 * */
	public boolean retireAgence(String adrAgence) { // adresse et ville sont synonymes ici (selon le sujet)
		for(int i = 0; i < agences.size(); i++){
			if(((Agence) agences.get(i)).getAdrAgence() == adrAgence){
				agences.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/** Permet de retrouver une agence par le nom de sa ville 
	 * @param adrAgence
	 * @return
	 */
	public AgenceIntf rechercheAgence(String adrAgence){
		for(int i = 0; i < agences.size(); i++){
			if(((Agence) agences.get(i)).getAdrAgence() == adrAgence){
				return agences.get(i);
			}
		}
		return null;
	}
	
	/** Retourne la liste des agences de la banque par ville.
	 * La ville de chaque agence doit ëtre également retourée. 
	 * La taille de la liste n'étant pas connue à l'avance.
	 * @return
	 */
	/* ?? JE NE COMPRENDS PAS, LA VILLE DE L'AGENCE EST DANS L'OBJET AGENCE 
	 String adrAgence ?? */
	public List<AgenceIntf> listeAgencestoString(){
		return this.agences;
	}
	
	public String getNomBanque(){
		return this.nomBanque;
	}
	
	public String toStringListeAgences(){
		String s = "";
		for(int i = 0; i < agences.size(); i++){
				s += agences.get(i).toString();
		}		
		return s;
	}
	
	public String toString(){
		return "Nom de la banque : " + this.nomBanque
			+ this.toStringListeAgences();
	}
	
	public void displayToString() {
		System.out.println(this.toString());
	}


	public boolean insereAgence(String adrAgence, AgenceIntf agence)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}
}