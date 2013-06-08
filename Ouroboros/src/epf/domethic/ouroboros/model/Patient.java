package epf.domethic.ouroboros.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient {
	
	public enum Sexe {
		Masculin,
		Feminin;
	}
	
	private int id;
	
	private String nom;
	private String prenom;
	private Sexe sexe;
	private Date dateNaissance;
	private String lieuNaissance;
	private String adresse;
	private String ville;
	private String code_postal;
	private String pays;
	private String nationalite;
	private String telephone;
	private String numSS;
	private String medecinTraitant;
	private boolean hospitalise;

	
	

	public static List<Patient> ALL;
	
	static{
		ALL = new ArrayList<Patient>();
		Patient p1 = new Patient("Am�lie", "Aaaaa", new Date(), Sexe.Feminin);
		ALL.add(p1);
		Patient p2 = new Patient("Arielle", "Aabaa", new Date(), Sexe.Feminin);
		ALL.add(p2);
		Patient p3 = new Patient("B�a", "Muadaa", new Date(), Sexe.Feminin);
		ALL.add(p3);
		Patient p4 = new Patient("Zoya", "Rabaa", new Date(), Sexe.Feminin);
		ALL.add(p4);
		Patient p5 = new Patient("Yann", "Fkfda", new Date(), Sexe.Masculin);
		ALL.add(p5);
		Patient p6 = new Patient("V�ro", "Lbdh", new Date(), Sexe.Feminin);
		ALL.add(p6);
		Patient p7 = new Patient("Jean", "Jfd", new Date(), Sexe.Masculin);
		ALL.add(p7);
		Patient p8 = new Patient("Henry", "Nui", new Date(), Sexe.Masculin);
		ALL.add(p8);
		Patient p9 = new Patient("Bastien", "Abbaa", new Date(), Sexe.Masculin);
		ALL.add(p9);
		Patient p10 = new Patient("Thomas", "Podjs", new Date(), Sexe.Masculin);
		ALL.add(p10);
		Patient p11 = new Patient("G�raldine", "Dertu", new Date(), Sexe.Feminin);
		ALL.add(p11);
		Patient p12 = new Patient("Chris", "Utyr", new Date(), Sexe.Masculin);
		ALL.add(p12);
		Patient p13 = new Patient("Aline", "Acbaa", new Date(), Sexe.Feminin);
		ALL.add(p13);
		
//		for(int i14 = 0; i < 20; i++){
//			Patient p = new Patient("Nom" + i, "Pr�nom" + i, new Date(), Sexe.Feminin);
//			ALL.add(p);		
//		}
	}

	public Patient (){
		
	}
	 
	public Patient(int id, String nom, String prenom, Sexe sexe,
			Date dateNaissance, String lieuNaissance, String adresse,
			String ville, String code_postal, String pays, String nationalite,
			String telephone, String numSS, String medecinTraitant,
			boolean hospitalise) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.dateNaissance = dateNaissance;
		this.lieuNaissance = lieuNaissance;
		this.adresse = adresse;
		this.ville = ville;
		this.code_postal = code_postal;
		this.pays = pays;
		this.nationalite = nationalite;
		this.telephone = telephone;
		this.numSS = numSS;
		this.medecinTraitant = medecinTraitant;
		this.hospitalise = hospitalise;
	}

	public Patient(String valNom, String valPrenom, Date valDate, Sexe valSexe){
		nom = valNom;
		prenom = valPrenom;
		dateNaissance = valDate;
		sexe = valSexe;
	}
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public Sexe getSexe() {
		return sexe;
	}
	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}
	public String getLieuNaissance() {
		return lieuNaissance;
	}

	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getNumSS() {
		return numSS;
	}

	public void setNumSS(String numSS) {
		this.numSS = numSS;
	}

	public String getMedecinTraitant() {
		return medecinTraitant;
	}

	public void setMedecinTraitant(String medecinTraitant) {
		this.medecinTraitant = medecinTraitant;
	}

	public boolean isHospitalise() {
		return hospitalise;
	}

	public void setHospitalise(boolean hospitalise) {
		this.hospitalise = hospitalise;
	}


}
