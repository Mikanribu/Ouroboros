package epf.domethic.ouroboros.model;

public class MembrePersonnelHospitalier extends PersonnelMedical{

	public int niveauAcces;

	public MembrePersonnelHospitalier(String service, int niveauAcces) {
		super(service);
		this.niveauAcces = niveauAcces;
	}

	public int getNiveauAcces() {
		return niveauAcces;
	}

	public void setNiveauAcces(int niveauAcces) {
		this.niveauAcces = niveauAcces;
	}
	
	
}
