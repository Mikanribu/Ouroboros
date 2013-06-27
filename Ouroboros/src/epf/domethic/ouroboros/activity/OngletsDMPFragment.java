package epf.domethic.ouroboros.activity;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextThemeWrapper;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.ActionBar.Tab;

import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.model.Patient;

public class OngletsDMPFragment extends SherlockFragment implements
		TabListener {

	//d�claration des variables
	private ArrayList<SherlockFragment> listeOnglets = new ArrayList<SherlockFragment>();
	private int position;
	private Patient patient;
	
	// Bo�te de dialogue pour les fonctions non encore impl�ment�es
	AlertDialog.Builder boite;

	// Fragments
	InformationsGeneralesFragment infos = new InformationsGeneralesFragment();
	ListeGaucheHospiDMPFragment menu_hospi = new ListeGaucheHospiDMPFragment();
	ListeGaucheInfosDMPFragment menu_infos = new ListeGaucheInfosDMPFragment();
	AfficherRadioFragment radio_fragment = new AfficherRadioFragment();
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		//on ajoute les onglets � la barre d'action
		ActionBar.Tab tab_infos = getSherlockActivity().getSupportActionBar().newTab();
		tab_infos.setText("Informations");
		tab_infos.setTabListener(this);
		getSherlockActivity().getSupportActionBar().addTab(tab_infos);

		ActionBar.Tab tab_hospi = getSherlockActivity().getSupportActionBar().newTab();
		tab_hospi.setText("Hospitalisation");
		tab_hospi.setTabListener(this);
		getSherlockActivity().getSupportActionBar().addTab(tab_hospi);

		ActionBar.Tab tab_histo = getSherlockActivity().getSupportActionBar().newTab();
		tab_histo.setText("Historique");
		tab_histo.setTabListener(this);
		getSherlockActivity().getSupportActionBar().addTab(tab_histo);

		ActionBar.Tab tab_code = getSherlockActivity().getSupportActionBar().newTab();
		tab_code.setText("Codification");
		tab_code.setTabListener(this);
		getSherlockActivity().getSupportActionBar().addTab(tab_code);

		// Cr�ation de la bo�te de dialogue qui sera affich�e lorsque
		// l'utilisateur cliquera sur des boutons pas d�velopp�
		ContextThemeWrapper ctw = new ContextThemeWrapper(getSherlockActivity(), R.style.ThemeHoloDialog );
		boite = new AlertDialog.Builder(ctw);
		boite.setTitle("La fonction n'est pas encore impl�ment�e!");
		boite.setIcon(R.drawable.travaux);
		boite.setMessage("Cette fonction n'a pas �t� d�velopp�e dans cette version.");
		boite.setNegativeButton("Retour", null);
		
	}

	@Override
	//Si un onglet est s�lectionn�
	public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {

		//index de l'onglet s�lectionn�
		int position = tab.getPosition();
		switch (position) {
		case 1: //Si hospitalisation
			
			infos.getPatient(this.patient); //instanciation du patient dans le fragment infos
			//S'ils ne sont pas pr�sents � l'�cran,  affichage des onglets infos et menu h�spitalisation
			if(!menu_hospi.isResumed()){
				fragmentTransaction.replace(R.id.tiers, menu_hospi);
				menu_hospi.getPatient(patient);
			}
			if(!infos.isResumed())
				fragmentTransaction.replace(R.id.deuxTiers, infos);
			
			break;

		case 2:
			boite.show(); //Pas encore impl�ment�: bo�te de dialogue
			break;
		case 3:
			boite.show(); //Pas encore impl�ment�: bo�te de dialogue
			break;

		default:
			
			infos.getPatient(this.patient); //instanciation du patient dans le fragment infos
			//S'ils ne sont pas pr�sents � l'�cran,  affichage des onglets infos et menu informations
			if(!menu_infos.isResumed())
				fragmentTransaction.replace(R.id.tiers, menu_infos);
			if(!infos.isResumed())
				fragmentTransaction.replace(R.id.deuxTiers, infos);
			
			break;
		}

	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {

	}
	
	public void onDetach() {
		super.onDetach();
		
	}
	
	//instanciation du patient dans le fragment
	public Patient getPatient(Patient patient){
		this.patient=patient;
		return patient;
	}
}
