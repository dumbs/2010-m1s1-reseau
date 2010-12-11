import java.rmi.Naming;

import util.Clavier;
import util.Sexe;
import agence.IAgence;
import banque.IBanque;
import client.IClient;


public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			IBanque ca = (IBanque) Naming.lookup("//localhost/CA");

			System.out.println("Enter an agence location (URL):");
			IAgence montpellierCA = (IAgence)Naming.lookup(Clavier.lireString());
			
			// 3. On insère les agences dans l'objet Banque
			ca.insereAgence("Montpellier", montpellierCA);
			//ca.insereAgence("Paris", new Agence("Paris", ca));
			//ca.insereAgence("Paris", new Agence("Paris", ca)); // ne sera pas inséré, car agence "Paris" existe déjà

			IAgence agMtp = ca.rechercheAgence("Montpellier");
			//Agence agParis = (Agence) ca.rechercheAgence("Montpellier");

			// 4. On crée des clients à partir des objets Absence
			agMtp.creeClient("John Charron", Sexe.MASCULIN, "58, rue du faubourg Boutonnet", "Montpellier", 
					"06.71.78.84.50", agMtp);
			agMtp.creeClient("Bertrand Brun", Sexe.MASCULIN, "16, rue Vivaldi", "Montpellier",
					"06.74.56.71.61",  agMtp);
			//agParis.creeClient("Abel Triunfante", Sexe.MASCULIN, "13, rue Victor Hugo",
			//		"Paris", "01.43.44.28.43", agParis);

			
			// 5. On crée des comptes et on fait quelques transactions
			IClient charron = agMtp.rechercheClient("John Charron");
			int numCompteCharron = charron.creeCompte(500000.00);
			int numLivretCharron = charron.creeLivret(100000, 2.5);
			charron.virement(numCompteCharron, numLivretCharron, 50000);

			IClient brun = agMtp.rechercheClient("Bertrand Brun");
			brun.creeCompte(483.06);

			// 6. On imprime tout à partir de l'objet Banque 
			// FIXME : Je ne comprend pas pourquoi c'a n'affiche pas toutes les informations.
			System.out.println(ca.infos());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
