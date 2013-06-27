package epf.domethic.ouroboros.activity;

import org.json.JSONArray;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import epf.domethic.ouroboros.R;
import epf.domethic.ouroboros.activity.ListeGaucheHospiDMPFragment.OnRadioSelectedListener;
import epf.domethic.ouroboros.activity.ListerPatientsFragment.OnPatientSelectedListener;
import epf.domethic.ouroboros.model.Patient;
import epf.domethic.ouroboros.model.Radio;
import epf.domethic.ouroboros.widget.AnimationLayout;

//Activit� de base de l'application contenant tous les fragments
public class HospitalisationsActivity extends SherlockFragmentActivity implements 
	AnimationLayout.Listener, OnPatientSelectedListener, OnRadioSelectedListener {
	/** Called when the activity is first created. */

	private int position;
	private Patient patient;
	public static Context appContext;

	//Variables concernant le menu slide
	public final static String TAG = "Demo";
	protected LinearLayout mList;
	protected AnimationLayout mLayout;

	// Contenu du menu
	private TextView tvFonction;
	private TextView tvDeconnexion;
	private TextView tvRecherche;
	private TextView tvCreationDossier;
	private TextView tvAjoutDoc;
	private TextView tvTransfert;
	private TextView tvCodification;
	private TextView tvArchives;
	private TextView tvMonCompte;
	private TextView tvHospitalisations;

	// Bo�te de dialogue pour les fonctions non impl�ment�es
	AlertDialog.Builder boite;

	//D�claration des fragments
	AfficherPatientFragment detail_patient = new AfficherPatientFragment();
	ListerPatientsFragment liste_patients = new ListerPatientsFragment();
	AfficherRadioFragment fragment_radio = new AfficherRadioFragment();
	
	public int fonction;
	
	JSONArray personnes = null;

	// url ou l'on peut acc�der au JSON des s�cr�taires m�dicales.
	static String urlsecMed = "http://raw.github.com/Mikanribu/Ouroboros/master/json_secretaires_med";

	// url ou l'on peut acc�der au JSON des m�decins.
	static String urlMed = "http://raw.github.com/Mikanribu/Ouroboros/master/json_medecins";

	//Lors de la cr�ation de l'activit�
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hospitalisations); //utilise le xml 
		
		//r�cup�re les ressources
		Resources r = getResources();
		Drawable myDrawable;

		//permet � la barre d'action d'�tre en mode navigation
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		//L'ic�ne de lancement ne renvoie pas � la premi�re activit�
		getSupportActionBar().setHomeButtonEnabled(true); 
		
		// Pas d'affichage du nom de l'application dans la barre d'action
		getSupportActionBar().setDisplayShowTitleEnabled(false);

		//Affectation des layouts pour le slide menu
		mList = (LinearLayout) findViewById(R.id.animation_layout_sidebar);
		mLayout = (AnimationLayout) findViewById(R.id.animation_layout);
		mLayout.setListener(this); //listener du slide menu affect�

		
		// Cr�ation de la bo�te de dialogue qui sera affich�e lorsque l'utilisateur cliquera sur des boutons du menu non d�velopp�s
		ContextThemeWrapper ctw = new ContextThemeWrapper( this, R.style.ThemeHoloDialog );
		boite = new AlertDialog.Builder(ctw);
		boite.setTitle("La fonction n'est pas encore impl�ment�e!");
		boite.setIcon(R.drawable.travaux);
		boite.setMessage("Cette fonction n'a pas �t� d�velopp�e dans cette version.");
		boite.setNegativeButton("Retour", null);

		// D�connecter l'utilisateur
		tvDeconnexion = (TextView) findViewById(R.id.tvDeconnexion);
		
		//Intention permettant de revenir � l'activit� Connexion
		final Intent intent_connexion = new Intent(	HospitalisationsActivity.this, ConnexionActivity.class);
		
		//Si l'utilisateur clique sur le textView il retourne � l'activit� de connexion
		tvDeconnexion.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(intent_connexion); 
			}
		});

		//On r�cup�re l'intention pr�c�dente
		Intent intent = getIntent ();
			
		// R�cuperer le pseudo entr� en connexion
		String nom = intent.getStringExtra("nom");
		String prenom = intent.getStringExtra("prenom");
								
		if(intent.getStringExtra("fonction")!=null){
			fonction = Integer.parseInt(intent.getStringExtra("fonction"));
		}
			   
		// Si l'utilisateur est un m�decin.
		if (fonction == 1) {	
			myDrawable = r.getDrawable(R.drawable.barre_haut);
			getSupportActionBar().setBackgroundDrawable(myDrawable);
			
			//on instancie le textView hospitalisations
			tvHospitalisations = (TextView) findViewById(R.id.tvHospitalisation);
			//Si l'utilisateur  clique sur ce textview
			tvHospitalisations.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					FragmentManager fragmentManager = getSupportFragmentManager();
					FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
					//on retire les onglets de la barre d'action s'il y en a
					getSupportActionBar().removeAllTabs();
					//si le fragment du d�tail du patient n'est pas affich�
					if(!detail_patient.isResumed())
						//on remplace le fragment actuel par celui du d�tail du patient
						fragmentTransaction.replace(R.id.deuxTiers, detail_patient);
					//si le fragment de la liste des patients n'est pas affich�e
					if(!liste_patients.isResumed())
						//on remplace le fragment actuel par celui de la liste des patients
						fragmentTransaction.replace(R.id.tiers, liste_patients);
					//on commite la transaction de fragments
					fragmentTransaction.commit();
					
				}
			});
				
			// Fonction Recherche non impl�ment�e: renvoie vers la boite de dialogue
			tvRecherche = (TextView) findViewById(R.id.tvRecherche);
			tvRecherche.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					boite.show();
				}
			});
	
			// Fonction Cr�ation dossier non impl�ment�e: renvoie vers la boite de dialogue
			tvCreationDossier = (TextView) findViewById(R.id.tvCreationDossier);
			tvCreationDossier.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					boite.show();
				}
			});
	
			// Fonction Ajout document non impl�ment�e: renvoie vers la boite de dialogue
			tvAjoutDoc = (TextView) findViewById(R.id.tvAjoutDocument);
			tvAjoutDoc.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					boite.show();
				}
			});
	
			// Fonction Transfert non impl�ment�e: renvoie vers la boite de dialogue
			tvTransfert = (TextView) findViewById(R.id.tvTransfert);
			tvTransfert.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					boite.show();
				}
			});
	
			// Fonction A codifier non impl�ment�e: renvoie vers la boite de dialogue
			tvCodification = (TextView) findViewById(R.id.tvACodifier);
			tvCodification.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					boite.show();
				}
			});
		
			// Fonction Archives non impl�ment�e: renvoie vers la boite de dialogue
			tvArchives = (TextView) findViewById(R.id.tvArchives);
			tvArchives.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					boite.show();
				}
			});
	
			// Fonction Mon compte non impl�ment�e: renvoie vers la boite de dialogue
			tvMonCompte = (TextView) findViewById(R.id.tvMonCompte);
			tvMonCompte.setOnClickListener(new View.OnClickListener() {	
				@Override
				public void onClick(View v) {
					boite.show();
				}
			});
	
			// Affiche dans le menu le nom du m�decin connect� et sa fonction
			tvFonction = (TextView) findViewById(R.id.tvNomMedecin);
			tvFonction.setText(prenom + " " + nom);
			
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			fragmentTransaction.add(R.id.deuxTiers, detail_patient);
			fragmentTransaction.add(R.id.tiers, liste_patients);
			fragmentTransaction.commit();
		}
			
		// Si l'utilisateur est une secr�taire m�dicale
		else if (fonction == 2) {
			setContentView(R.layout.menu_secretaire_medicale);
					myDrawable = r.getDrawable(R.drawable.barre_hautsecadm);
			getSupportActionBar().setBackgroundDrawable(myDrawable);
	
			// Affiche dans le menu le nom de la secr�taire m�dicale connect�e et sa fonction
			tvFonction = (TextView) findViewById(R.id.tvNomSecretaireMedicale);
			tvFonction.setText(prenom + " " + nom);
		}
		
		// Si l'utilisateur n'est ni m�decin, ni secr�taire m�dicale.
		else {
			Toast.makeText(getApplicationContext(),"Ce type d'utilisateur n'a pas encore �t� impl�ment�.",Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onPatientSelected(int position, Patient patient) {
		this.position = position;
		detail_patient.getDetailPatient(patient);	
	}
	
	public void onRadioSelected(int position, Radio radio){
		this.position = position;
		
		if(!fragment_radio.isResumed()){
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			fragmentTransaction.replace(R.id.deuxTiers, fragment_radio);
			fragmentTransaction.commit();
		}
		fragment_radio.getVueRadio(radio);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the options menu from XML
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.accueil, menu);

		// Get the SearchView and set the searchable configuration
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_recherche).getActionView();
		// Assumes current activity is the searchable activity
		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		searchView.setIconifiedByDefault(false); // Do not iconify the widget;
													// expand it by default

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem) {
		if (menuItem.getItemId() == android.R.id.home) {
			mLayout.toggleSidebar();
		}
		return (super.onOptionsItemSelected(menuItem));
	}

	//Lorsqu'on clique � un endroit du layout de contenu, le menu slide se r�tracte
	public void onClickContentButton(View v) {
		mLayout.toggleSidebar();
	}

	@Override
	public void onBackPressed() {
		if (mLayout.isOpening()) {
			mLayout.closeSidebar();
		} else {
			finish();
		}
	}

	@Override
	public void onSidebarOpened() {
		Log.d(TAG, "opened");
	}

	@Override
	public void onSidebarClosed() {
		Log.d(TAG, "closed");
	}

	
	@Override
	public boolean onContentTouchedWhenOpening() {
		Log.d(TAG, "going to close sidebar");
		mLayout.closeSidebar();
		return true;
	}
	
}
