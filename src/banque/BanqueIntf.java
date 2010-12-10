package banque;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import agence.AgenceIntf;

public interface BanqueIntf extends Remote {
	public boolean insereAgence(String adrAgence, AgenceIntf agence) throws RemoteException;
	public boolean retireAgence(String adrAgence) throws RemoteException;
	public AgenceIntf rechercheAgence(String adrAgence) throws RemoteException;
	public List<AgenceIntf> listeAgencestoString() throws RemoteException;
	public void displayToString() throws RemoteException;
	public String infos() throws RemoteException;
}
