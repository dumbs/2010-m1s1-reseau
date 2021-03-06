package agence;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import util.Adresse;
import util.Sexe;
import banque.Banque;
import banque.IBanque;
import client.Client;
import client.IClient;

/**
 * L'entité Agence contient trois attributs : le numéro d'agence, l'adresse de l'agence
 * et la banque à laquelle elle appartient. Elle définit les opérations CreeClient(),
 * detruireClient(), rechercheClient(), listeClients()
 * @author John CHARRON & Bertrand BRUN
 *
 */
public class Agence extends UnicastRemoteObject implements IAgence {
	
	private static final long serialVersionUID = 1L;
	private static int nextNumAgence = 1;
	private int numAgence;
	private String adrAgence; // qui consiste exclusivement du nom de la ville = 1 agence par ville
	private IBanque banque;
	private ArrayList<IClient> clients; // attribut pas demandé
	
	/** Constructeur par defaut. 
	 * @throws RemoteException */
	public Agence() throws RemoteException {
		super();
		this.numAgence = nextNumAgence;
		nextNumAgence++;
		this.adrAgence = "";
		this.banque = null;
		this.clients = new ArrayList<IClient>();
	}
	
	/** Constructeur plein avec, en paramètre, un objet banque 
	 * @throws RemoteException */
	public Agence(String adrAgence, IBanque banque) throws RemoteException {
		super();
		this.numAgence = nextNumAgence;
		nextNumAgence++;
		this.adrAgence = adrAgence;
		this.banque = banque;
		this.clients = new ArrayList<IClient>();
	}
	
	/**
	 * Permet de créer une nouvelle instance de Client.
	 * Cette opération prend en paramètre le nom du client, son sexe et
	 * l'adresse de sa banque (ville) et retourne son numéro de client.
	 * Le client est géré par un autre type de serveur.
	 * @throws RemoteException 
	 */
	// ?? Et l'adresse du client ? Pourquoi l'adresse de la banque ??
	public int creeClient(String nomClient, Sexe sexe, Adresse adrClient, IAgence agence) throws RemoteException{
		System.out.println("Agence.creeClient()");
		Client client = new Client(nomClient, sexe, adrClient, this);
		this.clients.add(client);
		return client.getNumClient();
	}
	
	public int creeClient(String nomClient, Sexe sexe, String rue, String ville,
			String numTel, IAgence agence) throws RemoteException{
		Client client = new Client(nomClient, sexe, rue, ville, numTel, this);
		this.clients.add(client);
		return client.getNumClient();
	}
	
	/** 
	 * Détruit un client à l'aide de son numéro de client passé en paramètre
	 */
	
	public void detruitClient(int numClient){
		for(int i = 0; i < clients.size(); i++){
			if(((Client) clients.get(i)).getNumClient() == numClient){
				clients.remove(i);
			}
		}
	}
	
	/**
	 * Permet de retrouver un client par son nom
	 * @param nomClient
	 */
	public IClient rechercheClient(String nomClient){
		for(int i = 0; i < clients.size(); i++){
			if (((Client) clients.get(i)).getNomClient().equals(nomClient)) {
				return clients.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Retourne la liste des clients de l'agence, la taille de la liste n'était pas
	 * connue à l'avance.
	 */
	public List<IClient> listeClients(){
		return this.clients;
	}
	
	public int getNumAgence(){
		return this.numAgence;
	}
	
	public String getAdrAgence(){
		return this.adrAgence;
	}
	
	public IBanque getBanque(){
		return this.banque;
	}
	
	public String getNomBanque(){
		return ((Banque) this.banque).getNomBanque();
	}
	
	public String toStringClients() {
		String s = "";
		for(IClient c : clients){
			s += c.toString();
		}
		return s;
	}
	
	@Override
	public String toString() {
		return "\n\nNuméro agence : " + this.numAgence
			+ " -> adresse : " + this.getAdrAgence()
			+ "\n" + toStringClients();
	}
	
	public void displayToString() throws RemoteException {
		System.out.println(this.toString());
	}
	
	public String infos() {
		return (this.toString());
	}
}
