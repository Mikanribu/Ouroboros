package epf.domethic.ouroboros.model;

public class PersonnelAdministratif extends Personnel {
	//Classe permettant d'avoir les m�tiers administrateurs et secr�taires m�dicales
	
	public String metier;

	public PersonnelAdministratif(String metier) {
		super();
		this.metier = metier;
	}

	public String getMetier() {
		return metier;
	}

	public void setMetier(String metier) {
		this.metier = metier;
	}
	
}
