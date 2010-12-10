package compte;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface ICompte extends Serializable {

	public double crediter(double montant) throws RemoteException;
	public boolean debiter(double montant) throws RemoteException;

}