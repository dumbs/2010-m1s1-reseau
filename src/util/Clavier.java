package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Bertrand BRUN
 * 
 * Cette classe fournit des methodes permettant de lire sur une ligne
 * une information de l'un des types int, float, double ou String.
 * La methode de lecture d'une chaine est utiliee par les autres pour 
 * lire la ligne.
 *
 */
public class Clavier {
	public static String lireString() {	// lecture d'une chaine
		String readLine = null;
		try {
			InputStreamReader reader = new InputStreamReader(System.in);
			BufferedReader entry = new BufferedReader(reader);
			readLine = entry.readLine();
		} catch (IOException e) {
			System.exit(0);
		}
		return (readLine);
	}
	
	public static float lireFloat() { // lecture d'un float
		float x = 0;
		try {
			String readLine = lireString();
			x = Float.parseFloat(readLine);
		} catch (NumberFormatException e) {
			System.out.println("Error: It isn't a float number!");
			System.exit(0);
		}
		return (x);
	}
	
	public static double lireDouble() { // lecture d'un double
		double x = 0;
		try {
			String readLine = lireString();
			x = Double.parseDouble(readLine);
		} catch (NumberFormatException e) {
			System.out.println("Error: It isn't a double number!");
			System.exit(0);
		}
		return (x);
	}
	
	public static int lireInt() { // lecture d'un entier
		int x = 0;
		try {
			String readLine = lireString();
			x = Integer.parseInt(readLine);
		} catch (NumberFormatException e) {
			System.out.println("Error: It isn't an int number!");
			System.exit(0);
		}
		return (x);
	}
}
