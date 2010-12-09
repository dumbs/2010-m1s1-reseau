public class Main {
	
	public static void main(String args[]){
		//1. On crée un objet Banque
		Banque bnp = new Banque("Banque Nationale de Paris");

		// 2. On crée des objets Agences
		Agence agMtp = new Agence("Montpellier", bnp);
		Agence agParis = new Agence("Paris", bnp);
		Agence agParis2 = new Agence("Paris", bnp);	
			// Ne sera pas ajouté car déjà une agence à Paris
		
		// 3. On insère les agences dans l'objet Banque
		bnp.insereAgence("Montpellier", agMtp);
		bnp.insereAgence("Paris", agParis);
		bnp.insereAgence("Paris", agParis2); // ne sera pas inséré, car agence "Paris" existe déjà
		
		// 4. On crée des clients à partir des objets Absence
		int numClientCharron = agMtp.creeClient("John Charron", Sexe.MASCULIN, "58, rue du faubourg Boutonnet", "Montpellier", 
				"06.71.78.84.50", agMtp);
		int numClientBrun = agMtp.creeClient("Bertrand Brun", Sexe.MASCULIN, "16, rue Vivaldi", "Montpellier",
				"06.74.56.71.61",  agMtp);
		int numClientTriunfante = agParis.creeClient("Abel Triunfante", Sexe.MASCULIN, "13, rue Victor Hugo",
				"Paris", "01.43.44.28.43", agParis);
		
		// 5. On crée des comptes et on fait quelques transactions
		Client charron = agMtp.rechercheClient("John Charron");
		int numCompteCharron = charron.creeCompte(500000.00);
		int numLivretCharron = charron.creeLivret(100000, 2.5);
		charron.virement(numCompteCharron, numLivretCharron, 50000);
		
		Client brun = agMtp.rechercheClient("Bertrand Brun");
		brun.creeCompte(483.06);
		
		// 6. On imprime tout à partir de l'objet Banque 
		bnp.displayToString();	
	}
}
