package serveur;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import util.Clavier;

import banque.Banque;


/**
 * @author Bertrand BRUN
 *
 */
public class BanqueServeur {

	public static void main(String[] args) {
		// Create and install a security manager
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
            System.out.println("Security manager installed.");
        } else {
            System.out.println("Security manager already exists.");
        }
        
        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(1099); 
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }
        
        try {
        		System.out.println("Contructing Credit Agricole Bank...");
        		Banque ca = new Banque("Credit Agricole");
        		System.out.println("Binding server implementations to registry...");
        		Naming.rebind("//localhost/CA", ca);
        		System.out.println("BanqueServeur bound in registry (//localhost/CA)");
            	while (!(Clavier.lireString()).equals("quit"));
		} catch (Exception e) {
			System.out.println("RMI server exception:");
			e.printStackTrace();
		}
		System.exit(0);
	}
}