package agence;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

import util.Adresse;
import util.Sexe;
import client.ClientIntf;

public interface AgenceIntf extends Serializable {
	public int creeClient(String nomClient, Sexe sexe, Adresse adrClient, Agence agence) throws RemoteException;
	public int creeClient(String nomClient, Sexe sexe, String rue, String ville, String numTel, Agence agence) throws RemoteException;
	public void detruitClient(int numClient) throws RemoteException;
	public ClientIntf rechercheClient(String nomClient) throws RemoteException;
	public List<ClientIntf> listeClients() throws RemoteException;
}
