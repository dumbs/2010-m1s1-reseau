/**
 * Un compte est défini par un numéro (identifiant unique), son client propriétaire, l'agence
 * où il est créé et le solde du compte. L’entité Compte fournit deux opérations pour
 * modifier le solde courant: crediter(), debiter()
 * @author John CHARRON & Bertrand BRUN 
 *
 */
public class Compte {

	private static int nextNumCompte = 1;
	public static int getNextNumCompte() {
		return nextNumCompte;
	}

	public static void setNextNumCompte(int nextNumCompte) {
		Compte.nextNumCompte = nextNumCompte;
	}

	private int numCompte;
	private int numClient;
	private int numAgence;
	private double solde;
	
	public Compte(int numClient, int numAgence, double solde){
		numCompte = nextNumCompte;
		nextNumCompte++;
		this.numClient = numClient;
		this.numAgence = numAgence;
		this.solde = solde;
	}
	
	/**
	 * L'opération credit pour créditer le solde d'un certain montant passé en paramètre.
	 * @param montant
	 * @return
	 */
	public double crediter(double montant){
		this.solde = this.solde + montant;
		return this.solde;
	}

	/**
	 * L'opération debit pour débiter le solde d'un montant donné passé également en paramètre.
	 * @param montant
	 * @return
	 */
	public boolean debiter(double montant){
		if(montant < this.solde){
			this.solde = this.solde - montant;
			return true;
		}
		else{
			System.out.println("\nOPERATION NON EFFECTUEE : Montant plus élevé que solde actuel");
			return false;
		}
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
}
