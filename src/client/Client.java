package client;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import util.Adresse;
import util.Sexe;
import agence.Agence;
import agence.AgenceIntf;

import compte.Compte;
import compte.ICompte;
import compte.Livret;

/**
 * L’entité Client possède cinq attributs: le numéro de client, le nom du client, son sexe, son
adresse et l'agence gérant ses comptes. L'attribut sexe peut prendre deux valeurs:
masculin ou feminin. L'adresse du client est représentée par une structure composée des
trois champs: rue, ville et téléphone. L’entité Client comporte les opérations suivantes:
creeCompte(), creeLivret(), detruireCompte(), listeComptes(), virement()
 * @author user
 *
 */
public class Client implements ClientIntf {

	private static final long serialVersionUID = 1L;
	private static int nextNumClient = 1;
	private int numClient;
	private String nomClient;
	private Sexe sexe;
	private Adresse adrClient;
	private AgenceIntf agence;
	private List<ICompte> comptesClient; // ?? attrbut supplémentaire... ici ??
	
	public Client(String nomClient, Sexe sexe, Adresse adrClient, AgenceIntf agence) throws RemoteException {
		super();
		this.numClient = nextNumClient;
		nextNumClient++;
		this.nomClient = nomClient;
		this.sexe = sexe;
		this.adrClient = adrClient;
		this.agence = agence;
		this.comptesClient = new ArrayList<ICompte>();
	}
	
	public Client(String nomClient, Sexe sexe, String rue, String ville, 
			String numTel, AgenceIntf agence) throws RemoteException {
		super();
		this.numClient = nextNumClient;
		nextNumClient++;
		this.nomClient = nomClient;
		this.sexe = sexe;
		this.adrClient = new Adresse(rue, ville, numTel);
		this.agence = agence;
		this.comptesClient = new ArrayList<ICompte>();
	}	

/*	
	public Client(String nomClient, char sexe, String rue, String ville, 
			String numTel, String adrAgence){
		this.numClient = nextNumClient;
		nextNumClient++;
		this.nomClient = nomClient;
		this.sexe = sexe;
		this.adrClient = new Adresse(rue, ville, numTel);
		//this.agence = agence;
		// ?? Où mettre adrAgence ??
		// (pour creeClient() d'Agence selon prof ??
	}
*/
	
	/* (non-Javadoc)
	 * @see client.ClientIntf#creeCompte(double)
	 */
	public int creeCompte(double solde) throws RemoteException {
		Compte compte = new Compte(this.numClient, ((Agence) this.agence).getNumAgence(), solde);
		this.comptesClient.add(compte);
		return compte.getNumCompte();
	}
	
	/* (non-Javadoc)
	 * @see client.ClientIntf#creeLivret(double, double)
	 */
	public int creeLivret(double solde, double tauxInt) throws RemoteException{
		Livret livret = new Livret(this.numClient, ((Agence) this.agence).getNumAgence(), solde, tauxInt);
		this.comptesClient.add(livret);
		return livret.getNumCompte();
	}
	
	/* (non-Javadoc)
	 * @see client.ClientIntf#detruireCompte(compte.Compte)
	 */
	public double detruireCompte(Compte compte){
		double solde = compte.getSolde();
		compte = null;
		return solde;
	}
	
	/* (non-Javadoc)
	 * @see client.ClientIntf#rechercheCompte(int)
	 */
	public Compte rechercheCompte(int numCompte){
		for(int i = 0; i < comptesClient.size(); i++){
			if(((Compte) comptesClient.get(i)).getNumCompte() == numCompte){
				return (Compte) comptesClient.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Retourne la liste des comptes créés, la taille de la liste n'étant
	 * pas connue à l'avance.
	 * @return
	 */
	public List<ICompte> listeComptes(){
		return this.comptesClient;
	}
	
	
	/* (non-Javadoc)
	 * @see client.ClientIntf#virement(int, int, double)
	 */
	public boolean virement(int c1, int c2, double montant) throws RemoteException {
		if((this.rechercheCompte(c1).getNumClient() == this.numClient)
			&& (this.rechercheCompte(c1).getNumClient() == this.numClient)
			&& (c1 != c2)){
				if(this.rechercheCompte(c1).debiter(montant)){
					this.rechercheCompte(c2).crediter(montant);
					return true;
			}
		}
		return false;			
	}
	
	public static int getNextNumClient() {
		return nextNumClient;
	}

	public int getNumClient() {
		return numClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public Sexe getSexe() {
		return sexe;
	}

	public Adresse getAdrClient() {
		return adrClient;
	}

	public AgenceIntf getAgence() {
		return agence;
	}

	/* (non-Javadoc)
	 * @see client.ClientIntf#getListeComptesClient()
	 */
	public List<ICompte> getListeComptesClient() {
		return comptesClient;
	}
	
	public String toStringComptesClients(){
		String s = "";
		for(ICompte c : comptesClient){
				s += c.toString();
		}
		return s;
	}		
	
	public String toString(){
		return "\nNuméro de client : " + this.numClient
			+ "\nNom du client : " + this.nomClient
			+ "\nSexe : " + this.sexe
			+ "\nadrClient : " + this.adrClient
			+ toStringComptesClients();
	}
	
	public void displayToString(){
		System.out.println(this.toString());
	}
}
