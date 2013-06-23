package epf.domethic.ouroboros.data;

import epf.domethic.ouroboros.outils.DocumentColumns;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//Classe permettant la cr�ation de la Base de Donn�es Radio
public class RadioDBOpenHelper extends SQLiteOpenHelper {
	
	/* ----------	D�claration des variables	----------*/
	
	private final static String TAG = PersDBOpenHelper.class.getSimpleName(); 
	
	private static final String DATABASE_NAME = "radios.db";			// Nom de la Base de Donn�es
	private static final int DATABASE_VERSION = 1;						// Version de la Base de Donn�es
	public static final String TABLE_RADIO = "Radio";					// Nom de la table Radio
	
	/* ----------	D�claration des focntions	----------*/
    
	//String de cr�ation de la BDD Radio avec le nom de la table et des colonnes
	private static final String DATABASE_CREATE = 
			"CREATE TABLE " + TABLE_RADIO + "( " + DocumentColumns._ID + " integer primary key autoincrement," +
					DocumentColumns.KEY_ID_PATIENT + " text not null, " + DocumentColumns.KEY_NOM + " text not null," +
					DocumentColumns.KEY_RADIO + " text not null, " + DocumentColumns.KEY_CAUSE + " text not null,"+
					DocumentColumns.KEY_DATE + " text not null," + DocumentColumns.KEY_MEDECIN + " text not null," +
					DocumentColumns.KEY_DESCRIPTION + " text not null," + DocumentColumns.KEY_INTERPRETATION + " text not null)";

	public RadioDBOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(TAG, "Cr�ation de la base : [version " + db.getVersion() + "]");
		db.execSQL(DATABASE_CREATE);			//Cr�ation de la Base de Donn�es
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//Message lors de la mise � jour de la Base de Donn�es
		Log.w(TAG, "Mise � jour de la base [" + DATABASE_NAME + "] : [" + oldVersion + " --> " + newVersion + "]");
	}

}
