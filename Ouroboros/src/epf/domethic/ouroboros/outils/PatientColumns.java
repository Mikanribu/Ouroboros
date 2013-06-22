package epf.domethic.ouroboros.outils;

import android.provider.BaseColumns;

//Classe permettant de r�cup�rer le nom des colonnes de la table Patient
public class PatientColumns implements BaseColumns {

	public static final String KEY_ID ="id";
	public static final String KEY_NOM = "nom";
    public static final String KEY_PRENOM = "prenom";
    public static final String KEY_SEXE = "sexe";
    public static final String KEY_DATE_NAISSANCE ="date_naissance" ;
    public static final String KEY_LIEU_NAISSANCE = "lieu_naissance";
    public static final String KEY_ADRESSE = "adresse";
    public static final String KEY_VILLE = "ville";
    public static final String KEY_CODE_POSTAL = "code_postal";
    public static final String KEY_PAYS = "pays";
    public static final String KEY_NATIONALITE = "nationalite";
    public static final String KEY_TELEPHONE = "telephone";
    public static final String KEY_NUMSS = "num_ss";
    public static final String KEY_MEDECIN_TRAITANT = "medecin_traitant";
    public static final String KEY_HOSPITALISE = "hospitalise";
}
