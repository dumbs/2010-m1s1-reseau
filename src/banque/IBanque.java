package banque;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import agence.IAgence;

public interface IBanque extends Remote {
	public boolean insereAgence(String adrAgence, IAgence agence) throws RemoteException;
	public boolean retireAgence(String adrAgence) throws RemoteException;
	public IAgence rechercheAgence(String adrAgence) throws RemoteException;
	public List<IAgence> listeAgencestoString() throws RemoteException;
	public void displayToString() throws RemoteException;
	public String infos() throws RemoteException;
}
