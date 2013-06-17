package epf.domethic.ouroboros.model;

public class PersonnelConnexion {

	public int fonction;
	// m�decin: 1; secr�taire m�dicale:2; secr�taire administrative: 3; m�decin DIM:4; membre du personnel:5; administrateur:6;
	public String pseudo;
	public String password;
	
	//Constructor
	public PersonnelConnexion() {
		super();
	}
	
	public PersonnelConnexion(String pseudo, String password, int fonction) {
		super();
		this.fonction = fonction;
		this.pseudo = pseudo;
		this.password = password;
	}
	
	// Getters and Setters
	public int getFonction() {
		return fonction;
	}
	
	public void setFonction(int fonction) {
		this.fonction = fonction;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}	
}
