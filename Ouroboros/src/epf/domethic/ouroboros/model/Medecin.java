package epf.domethic.ouroboros.model;

public class Medecin extends PersonnelMedical {
	//Classe d'un m�decin. On peut faire la diff�rence entre le chef et le medecin "normal"
	
	public enum Chef {
		OUI,
		NON;
	}
	
	private Chef chef;			//Permet de savoir si le m�decin est Chef de Servicce ou non

	//Constructeur de la classe Medecin
	public Medecin(String service, Chef chef) {
		super(service);
		this.chef = chef;
	}
	
	/* ----------	D�claration des Mutateurs & Accesseurs	----------*/
	
	public Chef getChef() {
		return chef;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}


	
	//On ajoute un commentaire
	
}
