package epf.domethic.ouroboros.activity;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.listener.MyTabsListener;

public class RechercheGeneralePatientFragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActivity().getActionBar();

		// --------------------Initialisation des onglets----------------------------

		// Cr�ation des fragments associ�s aux onglets
		// Cr�ation des onglets et des �couteurs associ�s

		// Recherche g�n�rale
		RechercheGeneralePatientFragment rechercheGFragment = new RechercheGeneralePatientFragment();
		ActionBar.Tab rechercheGTab = actionBar.newTab().setText("Recherche G�n�rale");
		rechercheGTab.setTabListener(new MyTabsListener(rechercheGFragment));

		// Recherche m�dicale
		RechercheMedicaleFragment rechercheMedicaleFragment = new RechercheMedicaleFragment();
		ActionBar.Tab rechercheMedTab = actionBar.newTab().setText("Recherche M�dicale");
		rechercheMedTab.setTabListener(new MyTabsListener(rechercheMedicaleFragment));

		// Dossiers Transf�r�s
		DossiersTransferesFragment dossiersTransfereFragment = new DossiersTransferesFragment();
		ActionBar.Tab dossTransTab = actionBar.newTab().setText("Dossiers Transf�r�s");
		dossTransTab.setTabListener(new MyTabsListener(dossiersTransfereFragment));

		// Codification
		CodificationFragment codiFragment = new CodificationFragment();
		ActionBar.Tab codiTab = actionBar.newTab().setText("Codification");
		codiTab.setTabListener(new MyTabsListener(codiFragment));

		// Ajout des onglets � la barre d'action
		actionBar.addTab(rechercheGTab);
		actionBar.addTab(rechercheMedTab);
		actionBar.addTab(dossTransTab);
	}

	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();

		ActionBar actionBar = getActivity().getActionBar();
		actionBar.removeAllTabs();

	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
		View view = inflater.inflate(R.layout.fragment_recherche_patients,container, false);
		return view;
	}
	
}
