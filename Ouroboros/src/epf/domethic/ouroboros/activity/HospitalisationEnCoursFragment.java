package epf.domethic.ouroboros.activity;

import epf.domethic.ouroboros.listener.MyTabsListener;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class HospitalisationEnCoursFragment extends Fragment{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActivity().getActionBar();

		// --------------------Initialisation des onglets----------------------------

		// Cr�ation des fragments associ�s aux onglets
		// Cr�ation des onglets et des �couteurs associ�s

		// Informations
		InformationsGeneralesFragment infosGFragment = new InformationsGeneralesFragment();
		ActionBar.Tab infosGTab = actionBar.newTab().setText("Informations");
		infosGTab.setTabListener(new MyTabsListener(infosGFragment));

		// Hospitalisation
		HospitalisationEnCoursFragment hospiFragment = new HospitalisationEnCoursFragment();
		ActionBar.Tab hospiTab = actionBar.newTab().setText("Hospitalisation");
		hospiTab.setTabListener(new MyTabsListener(hospiFragment));

		// Historique
		HistoriqueFragment historiqueFragment = new HistoriqueFragment();
		ActionBar.Tab historiqueTab = actionBar.newTab().setText("Historique");
		historiqueTab.setTabListener(new MyTabsListener(historiqueFragment));

		// Codification
		CodificationFragment codiFragment = new CodificationFragment();
		ActionBar.Tab codiTab = actionBar.newTab().setText("Codification");
		codiTab.setTabListener(new MyTabsListener(codiFragment));

		// Ajout des onglets � la barre d'action
		actionBar.addTab(infosGTab);
		actionBar.addTab(hospiTab);
		actionBar.addTab(historiqueTab);
		actionBar.addTab(codiTab);
	}

}
