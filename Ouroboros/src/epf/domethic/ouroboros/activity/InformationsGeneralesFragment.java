package epf.domethic.ouroboros.activity;

import java.text.SimpleDateFormat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.model.Patient;

public class InformationsGeneralesFragment extends SherlockFragment{
	
	/*----------	D�claration des variables	----------*/
	private TextView nNomPatient;
	private TextView nSexePatient;
	private TextView nLieuNaissance;
	private TextView nDateNaissance;
	private TextView nVille;
	private TextView nAdresse;
	private TextView nCodePostal;
	private TextView nPays;
	private TextView nNationalite;
	private TextView nNumeroSS;
	private TextView nTelephone;
	private TextView nMedecinTraitant;
	
	private Patient patient;
	private int id;
	
	/*----------	D�claration des fonctions	----------*/
	
	//Cr�ation de la vue
    @Override
   	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
   		View view = inflater.inflate(R.layout.fragment_infos_g,container, false);
   		
   		Bundle bundle = this.getArguments();
   		//int identifiant_patient = bundle.getInt("identifiant", id);
   		if(getArguments()!=null)
   		{
   		int myInt = getArguments().getInt("identifiant", id);
   		}
   		
   	//R�cup�ration des variables du layout pour leur affilier une valeur
		nNomPatient = (TextView)view.findViewById(R.id.tvNomPatient);
		nSexePatient = (TextView)view.findViewById(R.id.tvSexeValuePatient);
		nLieuNaissance = (TextView)view.findViewById(R.id.tvLieuNaissanceValuePatient);
		nDateNaissance = (TextView)view.findViewById(R.id.textview_date_naissance_value);
		nVille = (TextView)view.findViewById(R.id.tvVilleValuePatient);
		nAdresse =(TextView)view.findViewById(R.id.tvAdresseValuePatient);
		nCodePostal = (TextView)view.findViewById(R.id.tvCodePostalValuePatient);
		nPays = (TextView)view.findViewById(R.id.tvPaysValuePatient);
		nNationalite = (TextView)view.findViewById(R.id.tvNationaliteValuePatient);
		nNumeroSS = (TextView)view.findViewById(R.id.tvSSValuePatient);
		nTelephone = (TextView)view.findViewById(R.id.tvTelephoneValuePatient);
		nMedecinTraitant = (TextView)view.findViewById(R.id.tvMedecinTraitantValuePatient);
				
   		return view;
       }
    
    //M�thode d'affichage du patient
    public void afficherPatient (Patient patient) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//On affilie chaque TextView � une valeur de la classe Patient
		this.patient = patient;
		nNomPatient.setText(patient.getPrenom() + " " + patient.getNom());
		nSexePatient.setText(patient.getSexe().toString());
		nLieuNaissance.setText(patient.getLieuNaissance());
		nDateNaissance.setText(sdf.format(patient.getDateNaissance()));
		nVille.setText(patient.getVille());
		nAdresse.setText(patient.getAdresse());;
		nCodePostal.setText(patient.getCodePostal());;
		nPays.setText(patient.getPays());;
		nNationalite.setText(patient.getNationalite());;
		nNumeroSS.setText(patient.getNumSS());;
		nTelephone.setText(patient.getTelephone());;
		nMedecinTraitant.setText(patient.getMedecinTraitant());;
	}
}
