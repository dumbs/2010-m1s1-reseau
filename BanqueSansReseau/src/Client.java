import java.util.ArrayList;
import java.util.List;

/**
 * L’entité Client possède cinq attributs: le numéro de client, le nom du client, son sexe, son
adresse et l'agence gérant ses comptes. L'attribut sexe peut prendre deux valeurs:
masculin ou feminin. L'adresse du client est représentée par une structure composée des
trois champs: rue, ville et téléphone. L’entité Client comporte les opérations suivantes:
creeCompte(), creeLivret(), detruireCompte(), listeComptes(), virement()
 * @author user
 *
 */
public class Client {

	private static int nextNumClient = 1;
	private int numClient;
	private String nomClient;
	private Sexe sexe;
	private Adresse adrClient;
	private Agence agence;
	private List<Compte> comptesClient; // ?? attrbut supplémentaire... ici ??
	
	public Client(String nomClient, Sexe sexe, Adresse adrClient, Agence agence){
		this.numClient = nextNumClient;
		nextNumClient++;
		this.nomClient = nomClient;
		this.sexe = sexe;
		this.adrClient = adrClient;
		this.agence = agence;
		this.comptesClient = new ArrayList<Compte>();
	}
	
	public Client(String nomClient, Sexe sexe, String rue, String ville, 
			String numTel, Agence agence){
		this.numClient = nextNumClient;
		nextNumClient++;
		this.nomClient = nomClient;
		this.sexe = sexe;
		this.adrClient = new Adresse(rue, ville, numTel);
		this.agence = agence;
		this.comptesClient = new ArrayList<Compte>();
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
	
	/**
	 * Permet de créer une nouvelle instance de Compte, administré par un serveur de comptes. 
	 * Cette opération prend en paramètre le solde initial du compte.
	 */
	public int creeCompte(double solde){
		Compte compte = new Compte(this.numClient, this.agence.getNumAgence(), solde);
		this.comptesClient.add(compte);
		return compte.getNumCompte();
	}
	
	/**
	 * Crée une nouvelle instance de Livret géré par un serveur de livrets
	 * L'opération prend en paramètre le solde initial du compte ainsi que son taux.
	 * @param solde
	 * @param tauxInt
	 * @return
	 */
	public int creeLivret(double solde, double tauxInt){
		Livret livret = new Livret(this.numClient, this.agence.getNumAgence(), solde, tauxInt);
		this.comptesClient.add(livret);
		return livret.getNumCompte();
	}
	
	/**
	 * Sert à détruire un compte ou un livret précédemment créé.
	 * @param compte
	 * @return
	 */
	public double detruireCompte(Compte compte){
		double solde = compte.getSolde();
		compte = null;
		return solde;
	}
	
	/**
	 * Permet de retrouver un compte ou un livret à partir de son numéro.
	 * @param numCompte
	 * @return
	 */
	public Compte rechercheCompte(int numCompte){
		for(int i = 0; i < comptesClient.size(); i++){
			if(comptesClient.get(i).getNumCompte() == numCompte){
				return comptesClient.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Retourne la liste des comptes créés, la taille de la liste n'étant
	 * pas connue à l'avance.
	 * @return
	 */
	public List<Compte> listeComptes(){
		return this.comptesClient;
	}
	
	
	/**
	 * réalise une transaction bancaire entre deux comptes d'un même client.
	 * Elle débite le compte ou le livret de numéro c1 (premier paramètre) et crédite celui du
	 * compte c2 (deuxième paramètre).
	 * @param c1
	 * @param c2
	 * @param montant
	 * @return
	 */
	public boolean virement(int c1, int c2, double montant){
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

	public Agence getAgence() {
		return agence;
	}

	public List<Compte> getListeComptesClient() {
		return comptesClient;
	}
	
	public String toStringComptesClients(){
		String s = "";
		for(int i = 0; i < comptesClient.size(); i++){
				s += comptesClient.get(i).toString();
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
