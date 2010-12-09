/**
 * L’entité Livret est une spécialisation du type Compte. Elle offre une propriété
 * supplémentaire: un taux d'intérêt, un attribut accessible en lecture seulement. Le taux
 * est fixé à la création du compte. Attention : les comptes et les livrets sont gérés sur deux
 * types de serveurs différents !
 * @author John CHARRON & Bertrand BRUN 
 */
public class Livret extends Compte{
	
	private double tauxInt;
	
	public Livret(int numClient, int numAgence, double solde, double tauxInt){
		super(numClient, numAgence, solde);
	}
	
	/**
	 * un taux d'intérêt, un attribut accessible en lecture seulement. Le taux
	 * est fixé à la création du compte. Attention : les comptes et les livrets sont gérés sur deux
	 * types de serveurs différents !
	 * @return
	 */
	public double getTauxInt(){
		return this.tauxInt;
	}
}
