

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

import banque.Banque;


/**
 * @author Bertrand BRUN
 *
 */
public class BanqueServeur {

	public static void main(String[] args) {
		if(System.getSecurityManager() == null)
			System.setSecurityManager(new RMISecurityManager());
		try {
			System.out.println("Contructing Credit Agricole Bank...");
			Banque ca = new Banque("Credit Agricole");
			System.out.println("Binding server implementations to registry...");
			Naming.rebind("rmi://127.0.0.1/CA", ca);
			System.out.println("Waiting for invocations from client...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}