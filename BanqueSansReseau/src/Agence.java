import java.util.ArrayList;
import java.util.List;

/**
 * L'entité Agence contient trois attributs : le numéro d'agence, l'adresse de l'agence
 * et la banque à laquelle elle appartient. Elle définit les opérations CreeClient(),
 * detruireClient(), rechercheClient(), listeClients()
 * @author John CHARRON & Bertrand BRUN
 *
 */
public class Agence {
	
	private static int nextNumAgence = 1;
	private int numAgence;
	private String adrAgence; // qui consiste exclusivement du nom de la ville = 1 agence par ville
	private Banque banque;
	private List<Client> clients; // attribut pas demandé
	
	/** Constructeur vide. */
	public Agence(){
		this.numAgence = nextNumAgence;
		nextNumAgence++;
		this.adrAgence = "";
		this.banque = null;
		this.clients = new ArrayList<Client>();
	}
	
	/** Constructeur plein avec, en paramètre, un objet banque */
	public Agence(String adrAgence, Banque banque){
		this.numAgence = nextNumAgence;
		nextNumAgence++;
		this.adrAgence = adrAgence;
		this.banque = banque;
		this.clients = new ArrayList<Client>();
	}
	
	/**
	 * Permet de créer une nouvelle instance de Client.
	 * Cette opération prend en paramètre le nom du client, son sexe et
	 * l'adresse de sa banque (ville) et retourne son numéro de client.
	 * Le client est géré par un autre type de serveur.
	 */
	// ?? Et l'adresse du client ? Pourquoi l'adresse de la banque ??
	public int creeClient(String nomClient, Sexe sexe, Adresse adrClient, Agence agence){
		Client client = new Client(nomClient, sexe, adrClient, this);
		this.clients.add(client);
		return client.getNumClient();
	}
	
	public int creeClient(String nomClient, Sexe sexe, String rue, String ville,
			String numTel, Agence agence){
		Client client = new Client(nomClient, sexe, rue, ville, numTel, this);
		this.clients.add(client);
		return client.getNumClient();
	}
	
/*
		public int creeClient(String nomClient, char sexe, String rue, String ville,
			String numTel, String adrAgence){
		Client client = new Client(nomClient, sexe, rue, ville, numTel, adrAgence);
		return client.getNumClient();
	} // ?? adrAgence ???
 */
	
	/** 
	 * Détruit un client à l'aide de son numéro de client passé en paramètre
	 */
	
	public void detruitClient(int numClient){
		for(int i = 0; i < clients.size(); i++){
			if(clients.get(i).getNumClient() == numClient){
				clients.remove(i);
			}
		}
	}
	
	/**
	 * Permet de retrouver un client par son nom
	 * @param nomClient
	 */
	public Client rechercheClient(String nomClient){
		for(int i = 0; i < clients.size(); i++){
			if(clients.get(i).getNomClient() == nomClient){
				return clients.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Retourne la liste des clients de l'agence, la taille de la liste n'était pas
	 * connue à l'avance.
	 */
	public List<Client> getClients(){
		return this.clients;
	}
	
	public int getNumAgence(){
		return this.numAgence;
	}
	
	public String getAdrAgence(){
		return this.adrAgence;
	}
	
	public Banque getBanque(){
		return this.banque;
	}
	
	public String getNomBanque(){
		return this.banque.getNomBanque();
	}
	
	public String toStringClients(){
		String s = "";
		for(int i = 0; i < clients.size(); i++){
				s += clients.get(i).toString();
		}		
		return s;
	}
	
	public String toString(){
		return "\n\nNuméro agence : " + this.numAgence
			+ " -> adresse : " + this.getAdrAgence()
			+ "\n" + toStringClients();
	}
	
	public void displayToString(){
		System.out.println(this.toString());
	}
}
