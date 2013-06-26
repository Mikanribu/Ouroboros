package epf.domethic.ouroboros.activity;

import java.text.SimpleDateFormat;

import com.actionbarsherlock.app.SherlockFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.model.Patient;

public class AfficherPatientFragment extends SherlockFragment {

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
	
	private Button bVisualiserDMP;
	OngletsDMPFragment onglets = new OngletsDMPFragment();

	private Patient patient;

	
	/*----------	D�claration des fonctions	----------*/
	
	//Cr�ation de la vue 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		View view = inflater.inflate(R.layout.fragment_visualiser_patients,container, false);

		
		/*R�cup�ration des variables de type du layout "fragment_visualiser_patients"
		 * Permet de r�cup�rer les TextView afin de leur affilier une valeur		 */		
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
		
		//Le bouton permettant d'aller voir le DMP du patient
		bVisualiserDMP =(Button)view.findViewById(R.id.bVueDMP);
		bVisualiserDMP.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
				FragmentManager fragmentManager = getFragmentManager();
				FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
				fragmentManager.popBackStack();
				fragmentTransaction.replace(R.id.deuxTiers, onglets);
				onglets.getPatient(patient);
				fragmentTransaction.commit();
		    }
		});		
			
		return view;
	}
	
	// Fonction permettant d'affilier une valeur aux TextView
	// Permet d'afficher les donn�es du patient dans le fragment 
	public void afficherPatient (Patient patient) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//On affilie chaque TextView � une valeur de la classe Patient
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
	
	public Patient getDetailPatient(Patient patient){
		this.patient=patient;
		afficherPatient(patient);
		return patient;
	}
	
	public void onDetach(){
		super.onDetach();
	}
}
