package epf.domethic.ouroboros.activity;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.TabListener;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.ActionBar.Tab;

import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.activity.ListeGaucheHospiDMPFragment.OnRadioSelectedListener;
import epf.domethic.ouroboros.model.Patient;
import epf.domethic.ouroboros.model.Radio;

public class OngletsDMPFragment extends SherlockFragment implements TabListener {

	
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
		boite = new AlertDialog.Builder(getSherlockActivity());
		boite.setTitle("La fonction n'est pas encore impl�ment�e!");
		boite.setIcon(R.drawable.en_travaux);
		boite.setMessage("Cette fonction n'a pas �t� d�velopp�e dans cette version.");
		boite.setNegativeButton("Retour", null);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {

		int position = tab.getPosition();
		switch (position) {
		case 1:
			for(int i=0; i<listeOnglets.size(); i++)
				fragmentTransaction.remove(listeOnglets.get(i));
			listeOnglets.clear();
			fragmentTransaction.replace(R.id.tiers, menu_hospi);
			listeOnglets.add(menu_hospi);
			fragmentTransaction.replace(R.id.deuxTiers, infos);
			listeOnglets.add(infos);
			
			break;

		case 2:
			boite.show();
			break;
		case 3:
			boite.show();
			break;

		default:
			for(int i=0; i<listeOnglets.size(); i++)
				fragmentTransaction.remove(listeOnglets.get(i));
			listeOnglets.clear();
			fragmentTransaction.replace(R.id.deuxTiers, infos);
			infos.getPatient(this.patient);
			fragmentTransaction.replace(R.id.tiers, menu_infos);
			listeOnglets.add(menu_infos);
			listeOnglets.add(infos);
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
	
	public Patient getPatient(Patient patient){
		this.patient=patient;
		return patient;
	}
}
