package epf.domethic.ouroboros.model;

public class PersonnelAdministratif extends Personnel {
	//Classe permettant d'avoir les m�tiers administrateurs et secr�taires m�dicales
	
	public String metier;

	//Constructeur de la classe PersonnelAdministratif
	public PersonnelAdministratif(String metier) {
		super();
		this.metier = metier;
	}

	/* ----------	D�claration des Mutateurs & Accesseurs	----------*/
	
	public String getMetier() {
		return metier;
	}

	public void setMetier(String metier) {
		this.metier = metier;
	}
	
}
