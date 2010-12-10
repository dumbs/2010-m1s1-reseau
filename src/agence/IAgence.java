package agence;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

import util.Adresse;
import util.Sexe;
import client.IClient;

public interface IAgence extends Serializable {
	public int creeClient(String nomClient, Sexe sexe, Adresse adrClient, IAgence agence) throws RemoteException;
	public int creeClient(String nomClient, Sexe sexe, String rue, String ville, String numTel, IAgence agence) throws RemoteException;
	public void detruitClient(int numClient) throws RemoteException;
	public IClient rechercheClient(String nomClient) throws RemoteException;
	public List<IClient> listeClients() throws RemoteException;
}
