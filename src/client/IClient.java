package client;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

import compte.Compte;
import compte.ICompte;

public interface IClient extends Serializable {
	
	public int creeCompte(double solde) throws RemoteException;
	public int creeLivret(double solde, double tauxInt) throws RemoteException;
	public double detruireCompte(Compte compte) throws RemoteException;
	public ICompte rechercheCompte(int numCompte) throws RemoteException;
	public boolean virement(int c1, int c2, double montant) throws RemoteException;
	public List<ICompte> getListeComptesClient() throws RemoteException;
}