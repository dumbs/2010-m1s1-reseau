/**
 * 
 */
package serveur;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import util.Clavier;
import agence.Agence;
import banque.IBanque;

/**
 * @author Bertrand BRUN
 *
 */
public class AgenceServeur {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Usage: AgenceServeur <banqueIP>");
			System.exit(-1);
		}
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
        	IBanque banque = (IBanque) Naming.lookup("//localhost/CA");
        	System.out.println("Write the location of the new agence:");
        	String agenceName;
        	while (!(agenceName = Clavier.lireString()).equals("quit")) {
        		System.out.println("Contructing "+agenceName+"...");
        		Agence montpCA = new Agence(agenceName, banque);
        		System.out.println("Binding server implementations to registry...");
        		
        		String badURL = "//localhost/"+banque.getNomBanque()+"/"+agenceName;
        		String url = badURL.replace(" ", "");
        		
        		Naming.rebind(url, montpCA);
        		System.out.println(""+agenceName+" bound in registry ("+url+")");

            	System.out.println("Write the location of the new agence:");
        	}
        } catch (Exception e) {
        		System.out.println("RMI server exception:");
        		e.printStackTrace();
        }
        System.exit(0);
	}

}
