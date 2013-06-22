package epf.domethic.ouroboros.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import epf.domethic.ouroboros.data.RadioDBOpenHelper;
import epf.domethic.ouroboros.model.Radio;
import epf.domethic.ouroboros.outils.DocumentColumns;
import epf.domethic.ouroboros.outils.PatientColumns;

public class RadioDAO {

	/* ----------	D�claration des variables	---------- */
	private Context context;
	
	private SQLiteDatabase database;	//Base SQLite
	
	private RadioDBOpenHelper helper;
	
	
	/* ----------	D�claration des fonctions	---------- */
	
	public RadioDAO(Context context) {
		this.context = context;
		this.helper = new RadioDBOpenHelper(context);   
		database = helper.getWritableDatabase();
	}
	//Fonction permettant de fermer la BDD Radio
	public void close() {
		helper.close();
	}
	//Fonction permettant l'ouverture de la BDD Radio
	public RadioDAO open() throws SQLException {
		helper = new RadioDBOpenHelper(context);
		database = helper.getWritableDatabase();
        return this;
    }
	
	/* Focntion permettant de r�cup�rer toutes les tables de la BDD Radio	  
	 */
	public Cursor getRadiosCursor(){
		String[] columns = 			//Colonnes � r�cup�rer
				new String[]{DocumentColumns._ID, DocumentColumns.KEY_ID_PATIENT, DocumentColumns.KEY_NOM,
				DocumentColumns.KEY_RADIO, DocumentColumns.KEY_DATE, DocumentColumns.KEY_MEDECIN,
				DocumentColumns.KEY_DESCRIPTION, DocumentColumns.KEY_INTERPRETATION};
		//Retourne toutes les tables de la BDD Radio tri� dans l'ordre alphab�tique
		return	database.query(RadioDBOpenHelper.TABLE_RADIO,columns, null, null, null, null, DocumentColumns.KEY_DATE, null);
	}
	
	/*Fonction qui permet de r�cup�rer les tables de la BDD selon un crit�re
	 like repr�sente un nom qui devra �tre similaire au nom de la radio	 */
	public Cursor getRadiosCursor(String like){
		String[] columns =						//Colonnes � r�cup�rer 
				new String[]{DocumentColumns._ID, DocumentColumns.KEY_ID_PATIENT, DocumentColumns.KEY_NOM, 
				DocumentColumns.KEY_RADIO, DocumentColumns.KEY_DATE, DocumentColumns.KEY_MEDECIN, 
				DocumentColumns.KEY_DESCRIPTION, DocumentColumns.KEY_INTERPRETATION};


		String where = PatientColumns.KEY_NOM + " like '%" + like + "%'";			//Clause o� le nom de la radio correspond � la chaine de caract�re like	
		String[] whereArgs = new String[]{like};
		//Retourne un curseur sur les tables qui correspondent au crit�re de s�lection
		return	database.query(RadioDBOpenHelper.TABLE_RADIO,columns, where, null, null, null, null);

	}
	
	/*Fonction qui permet de r�cup�rer les tables de la BDD selon un crit�re
	 like repr�sente un entier qui devra �tre similaire � l'id de la radio	 */
	public Cursor getRadiosCursor(int like){
		String[] columns = 					//Colonnes � r�cup�rer
				new String[]{DocumentColumns._ID, DocumentColumns.KEY_ID_PATIENT, DocumentColumns.KEY_NOM, 
				DocumentColumns.KEY_RADIO, DocumentColumns.KEY_DATE, DocumentColumns.KEY_MEDECIN, 
				DocumentColumns.KEY_DESCRIPTION, DocumentColumns.KEY_INTERPRETATION};
		String where = PatientColumns._ID + " like '%" + like + "%'";		 		//Si l'id de la radio est comme l'entier 'like'
		int whereArgs = like;
		//Retourne un curseur sur les tables qui correspondent au crit�re de s�lection
		return	database.query(RadioDBOpenHelper.TABLE_RADIO,columns, where, null, null, null, null); 
	}
	
	/*Fonction qui permet d'ajouter une radio dans la base de donn�es
	arrayRadios correspond � la liste des attributs d'une radio*/
	public void ajouterRadio(ArrayList<String> arrayRadios){
		ContentValues values = new ContentValues();
		// ajout de toutes les valeurs dans le champ de la BDD correspondant
		values.put(DocumentColumns.KEY_ID_PATIENT, arrayRadios.get(0));
		values.put(DocumentColumns.KEY_NOM, arrayRadios.get(1));
		values.put(DocumentColumns.KEY_RADIO, arrayRadios.get(2));
		values.put(DocumentColumns.KEY_CAUSE, arrayRadios.get(3));	
		values.put(DocumentColumns.KEY_DATE, arrayRadios.get(4));
		values.put(DocumentColumns.KEY_MEDECIN, arrayRadios.get(5));
		values.put(DocumentColumns.KEY_DESCRIPTION, arrayRadios.get(6));
		values.put(DocumentColumns.KEY_INTERPRETATION, arrayRadios.get(7));

		database.insert(RadioDBOpenHelper.TABLE_RADIO, null, values); //Insert les donn�es dans la BDD
	}
	
	//Fonction permettant de savoir si la Base de Donn�es est vide
	public boolean dbIsEmpty () {
		Cursor cur = database.rawQuery("SELECT COUNT(*) FROM "+ RadioDBOpenHelper.TABLE_RADIO, null);
		
		if (cur != null) {							 //Si le curseur n'est pas null
		    cur.moveToFirst();                       // On pointe le curseur sur le premier �l�ment
		    if (cur.getInt (0) == 0) {               // Zero signifie que la table est vide
		    	return true;
		    }
		}
		return false;					//On retourne false si la table n'est pas vide
	}
	
	public Radio getRadio(Cursor cursor){
		Radio radio = new Radio();
		radio.setTitre(cursor.getString(0));
		radio.setNomRadio(cursor.getString(1));
		radio.setCause(cursor.getString(2));
		radio.setDate(cursor.getString(3));
		radio.setMedecin(cursor.getString(4));
		radio.setDescription(cursor.getString(5));
		radio.setInterpretation(cursor.getString(6));
		
		return radio;
	}
	
}
