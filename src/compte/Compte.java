package compte;

import java.rmi.RemoteException;

/**
 * Un compte est défini par un numéro (identifiant unique), son client propriétaire, l'agence
 * où il est créé et le solde du compte. L’entité Compte fournit deux opérations pour
 * modifier le solde courant: crediter(), debiter()
 * @author John CHARRON & Bertrand BRUN 
 *
 */
public class Compte implements ICompte {

	private static final long serialVersionUID = 1L;
	private static int nextNumCompte = 1;
	private int numCompte;
	private int numClient;
	private int numAgence;
	private double solde;
	
	public Compte(int numClient, int numAgence, double solde) throws RemoteException {
		super();
		numCompte = nextNumCompte;
		nextNumCompte++;
		this.numClient = numClient;
		this.numAgence = numAgence;
		this.solde = solde;
	}

	public int getNumCompte() {
		return numCompte;
	}

	public int getNumClient() {
		return numClient;
	}

	public int getNumAgence() {
		return numAgence;
	}

	public double getSolde() {
		return solde;
	}
	
	public String toString(){
		return "Numéro de client : " + this.numClient
			+ ", Numéro d'agence : " + this.numAgence
			+ ", Solde : " + this.solde
			+ "\n";
	}
	
	
	/* (non-Javadoc)
	 * @see compte.CompteIntf#crediter(double)
	 */
	public double crediter(double montant) throws RemoteException {
		this.solde = this.solde + montant;
		return this.solde;
	}

	/* (non-Javadoc)
	 * @see compte.CompteIntf#debiter(double)
	 */
	public boolean debiter(double montant) throws RemoteException{
		if(montant < this.solde){
			this.solde = this.solde - montant;
			return true;
		}
		else{
			System.out.println("\nOPERATION NON EFFECTUEE : Montant plus élevé que solde actuel");
			return false;
		}
	}	
}
