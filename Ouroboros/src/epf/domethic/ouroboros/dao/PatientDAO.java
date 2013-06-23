package epf.domethic.ouroboros.dao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import epf.domethic.ouroboros.activity.Utils;
import epf.domethic.ouroboros.data.PersDBOpenHelper;
import epf.domethic.ouroboros.model.Patient;
import epf.domethic.ouroboros.model.Patient.Sexe;
import epf.domethic.ouroboros.outils.PersColumns;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PatientDAO {

	/* ----------	D�claration des variables	---------- */
	private Context context;	
	private SQLiteDatabase database;	
	private PersDBOpenHelper helper;
	
	/* ----------	D�claration des fonctions	---------- */
	//Constructeur
	public PatientDAO(Context context) {
		this.context = context;
		this.helper = new PersDBOpenHelper(context);
		database = helper.getWritableDatabase(); 	//Autorise l'�criture dans la BDD
	}
	
	//Fonction permettant de fermer la BDD Patient
	public void close() {
		helper.close();
	}
	
	//Fonction permettant l'ouverture de la BDD Patient
	public PatientDAO open() throws SQLException {
		helper = new PersDBOpenHelper(context);
		database = helper.getWritableDatabase();
        return this;
    }
	
	/* Focntion permettant de r�cup�rer toutes les tables de la BDD Patient.	 */
	public Cursor getPatientsCursor(){
		String[] columns = 					//Colonnes � r�cup�rer
				new String[]{PersColumns._ID, PersColumns.KEY_NOM, PersColumns.KEY_PRENOM,
				PersColumns.KEY_SEXE, PersColumns.KEY_DATE_NAISSANCE,PersColumns.KEY_LIEU_NAISSANCE, 
				PersColumns.KEY_ADRESSE, PersColumns.KEY_VILLE, PersColumns.KEY_CODE_POSTAL, PersColumns.KEY_PAYS, 
				PersColumns.KEY_NATIONALITE, PersColumns.KEY_TELEPHONE, PersColumns.KEY_NUMSS, 
				PersColumns.KEY_MEDECIN_TRAITANT, PersColumns.KEY_HOSPITALISE};
		//Retourne toutes les tables de la BDD Radio tri� dans l'ordre alphab�tique
		return	database.query(PersDBOpenHelper.TABLE_PATIENT,columns, null, null, null, null, PersColumns.KEY_NOM, null);
	}
	
	/*Fonction qui permet de r�cup�rer les tables de la BDD selon un crit�re.
	'like' repr�sente un nom qui devra �tre similaire au nom du patient	 */
	public Cursor getPatientsCursor(String like){
		String[] columns = 					//Colonnes � r�cup�rer 
				new String[]{PersColumns._ID, PersColumns.KEY_NOM,	PersColumns.KEY_PRENOM,
				PersColumns.KEY_SEXE, PersColumns.KEY_DATE_NAISSANCE,PersColumns.KEY_LIEU_NAISSANCE, 
				PersColumns.KEY_ADRESSE, PersColumns.KEY_VILLE, PersColumns.KEY_CODE_POSTAL, PersColumns.KEY_PAYS, 
				PersColumns.KEY_NATIONALITE, PersColumns.KEY_TELEPHONE, PersColumns.KEY_NUMSS, 
				PersColumns.KEY_MEDECIN_TRAITANT, PersColumns.KEY_HOSPITALISE};
		//Clause o� le nom du patient correspond � la chaine de caract�re 'like'	
		String where = PersColumns.KEY_NOM + " like '%" + like + "%'";
		//Retourne un curseur sur les tables qui correspondent au crit�re de s�lection
		return	database.query(PersDBOpenHelper.TABLE_PATIENT,columns, where, null, null, null, null);
	}
	
	/*Fonction qui permet de r�cup�rer les patient
	L'argument repr�sente le curseur de la table
	Cette fonction retourne un patient de la classe Patient*/
	public Patient getPatient(Cursor cursor){
		Patient patient = new Patient();	//Cr�ation du patient
		//R�cup�ration des donn�es via le cursor pass� en argument et ajout dans le patient
		patient.setId(cursor.getInt(0));
		patient.setNom(cursor.getString(1));
		patient.setPrenom(cursor.getString(2));
		Sexe sexe = Sexe.valueOf(cursor.getString(3));
		patient.setSexe(sexe);
		try {	//Mise en forme de la date
			Date dateNaissance = Utils.parserDate(cursor.getString(4));
			patient.setDateNaissance(dateNaissance);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		patient.setLieuNaissance(cursor.getString(5));
		patient.setAdresse(cursor.getString(6));
		patient.setVille(cursor.getString(7));
		patient.setCodePostal(cursor.getString(8));
		patient.setPays(cursor.getString(9));
		patient.setNationalite(cursor.getString(10));
		patient.setTelephone(cursor.getString(11));
		patient.setNumSS(cursor.getString(12));
		patient.setMedecinTraitant(cursor.getString(13));
		patient.setHospitalise(cursor.getInt(14) == 1);  // R�cup�rer la valeur en tant que boolean
		//Retourne le patient
		return patient;
	}
	
	/*
	 * Fonction qui permet de r�cup�rer une liste de tous les patients de la BDD Patient
	 */
	public List<Patient> getPatients(){	
		List<Patient> patients = new ArrayList<Patient>();	//Cr�ation de la liste des patients
		String[] columns = 					//Colonnes � r�cup�rer 
				new String[]{PersColumns._ID, PersColumns.KEY_NOM,	PersColumns.KEY_PRENOM,
				PersColumns.KEY_SEXE, PersColumns.KEY_DATE_NAISSANCE,PersColumns.KEY_LIEU_NAISSANCE, 
				PersColumns.KEY_ADRESSE, PersColumns.KEY_VILLE, PersColumns.KEY_CODE_POSTAL, PersColumns.KEY_PAYS, 
				PersColumns.KEY_NATIONALITE, PersColumns.KEY_TELEPHONE, PersColumns.KEY_NUMSS, 
				PersColumns.KEY_MEDECIN_TRAITANT, PersColumns.KEY_HOSPITALISE};
		Cursor cursor = database.query(PersDBOpenHelper.TABLE_PATIENT,columns, null, null, null, null, null);
		
		cursor.moveToFirst();						//On place le cursor au premier �l�ment de la table Patient
		while (!cursor.isAfterLast()) {				//Tant que le cursor pointe sur un patient
			Patient patient = new Patient();		//Cr�ation du patient
			//R�cup�ration des donn�es via le cursor et ajout dans le patient
			patient.setId(cursor.getInt(0));
			patient.setNom(cursor.getString(1));
			patient.setPrenom(cursor.getString(2));
			Sexe sexe = Sexe.valueOf(cursor.getString(3));
			patient.setSexe(sexe);
			try {									//Mise en forme de la date
				Date dateNaissance = Utils.parserDate(cursor.getString(4));
				patient.setDateNaissance(dateNaissance);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			patient.setLieuNaissance(cursor.getString(5));
			patient.setAdresse(cursor.getString(6));
			patient.setVille(cursor.getString(7));
			patient.setCodePostal(cursor.getString(8));
			patient.setPays(cursor.getString(9));
			patient.setNationalite(cursor.getString(10));
			patient.setTelephone(cursor.getString(11));
			patient.setNumSS(cursor.getString(12));
			patient.setMedecinTraitant(cursor.getString(13));
			patient.setHospitalise(cursor.getInt(14) == 1);  // R�cup�rer la valeur en tant que boolean
			
			patients.add(patient);			//Ajoute le patient nouvellement cr�� � la liste de patients
			cursor.moveToNext();			//On passe � la ligne suivante dans la table Patient
		}
		cursor.close();						//Fermeture du cursor
		return patients;					//On retourne la liste de tous les patients de la BDD
	}
	
	/*Fonction qui permet d'ajouter un patient dans la base de donn�es */
	public void ajouterPatient(Patient patient){
		ContentValues values = new ContentValues();
		// Ajout de toutes les valeurs dans le champ de la BDD correspondant
		values.put(PersColumns.KEY_NOM, patient.getNom());
		values.put(PersColumns.KEY_PRENOM,patient.getPrenom());
		values.put(PersColumns.KEY_SEXE, patient.getSexe().name());	
		values.put(PersColumns.KEY_DATE_NAISSANCE, Utils.formaterDate(patient.getDateNaissance()));
		values.put(PersColumns.KEY_NOM, patient.getNom());
		values.put(PersColumns.KEY_LIEU_NAISSANCE, patient.getLieuNaissance());
		values.put(PersColumns.KEY_ADRESSE, patient.getAdresse());
		values.put(PersColumns.KEY_VILLE, patient.getVille());
		values.put(PersColumns.KEY_CODE_POSTAL, patient.getCodePostal());
		values.put(PersColumns.KEY_PAYS, patient.getPays());
		values.put(PersColumns.KEY_NATIONALITE, patient.getNationalite());
		values.put(PersColumns.KEY_TELEPHONE, patient.getTelephone());
		values.put(PersColumns.KEY_NUMSS, patient.getNumSS());
		values.put(PersColumns.KEY_MEDECIN_TRAITANT, patient.getMedecinTraitant());
		values.put(PersColumns.KEY_HOSPITALISE, patient.isHospitalise());
		database.insert(PersDBOpenHelper.TABLE_PATIENT, null, values);		//Insertion des valeurs r�cup�r�es dans la BDD
	}
	
	//Fonction permettant de savoir si la Base de Donn�es est vide
	public boolean dbIsEmpty () {
		Cursor cur = database.rawQuery("SELECT COUNT(*) FROM "+ PersDBOpenHelper.TABLE_PATIENT, null);
		
		if (cur != null) {							 //Si le curseur n'est pas null
		    cur.moveToFirst();                       // On pointe le curseur sur le premier �l�ment
		    if (cur.getInt (0) == 0) {               // Zero signifie que la table est vide
		    	return true;
		    }
		}
		return false;								 //On retourne false si la table n'est pas vide
	}
	
}
