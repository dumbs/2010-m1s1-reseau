
public class Adresse {
	
	private String rue;
	private String ville;
	private String numTel;
	
	public Adresse(){
		
	}
	
	public Adresse(String rue, String ville, String numTel){
		this.rue = rue;
		this.ville = ville;
		this.numTel = numTel;
	}
	
	public void setRue(String rue){
		this.rue = rue;
	}
	
	public String getRue(){
		return this.rue;
	}
	
	public void setVille(String ville){
		this.ville = ville;
	}
	
	public String getVille(){
		return this.ville;
	}
	
	public void setNumTel(String numTel){
		this.numTel = numTel;
	}
	
	public String getNumTel(){
		return this.numTel;
	}
	
	public String toString(){
		return "\nNuméro et rue : " + this.rue
			+ "\nVille : " + this.ville
			+ "\nNuméro de téléphone : " + this.numTel
			+ "\n";
	}
}
