package epf.domethic.ouroboros.model;

public class PersonnelMedical extends Personnel {
	//Classe personnel uniquement m�dical. Classe m�re de Medecin, MermbrePersonnelHospitalier
	
	public String service;
	
	//Constructeur
	public PersonnelMedical(String service) {
		super();
		this.service = service;
	}
	
	/* ----------	D�claration des Mutateurs & Accesseurs	----------*/
	
	public PersonnelMedical() {
		super();
	}
	
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}


}
