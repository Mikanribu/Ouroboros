package epf.domethic.ouroboros.outils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.StrictMode;
import android.util.Log;

//Permet de parser JSON venant d'une url et de retourner un JSONObject
public class ParserJSON extends Activity {
	
	//D�claration des variables
	static String json =""; 
	static JSONObject jObj = null; // JSONObject qui va �tre renvoy�
	static InputStream is = null;	

	// constructor
    public ParserJSON() {
 
    }
 
    public JSONObject getJSONFromUrl(String url) {
 
        // Faire une requ�te HTTP
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            
            HttpParams httpParameters = httpPost.getParams();
            // Cr�ation d'un timeout en millisecondes jusqu'a l'�tablissement d'une connexion.
            int timeoutConnection = 7500;
            HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
            // Cr�ation du socket timeout par d�faut (SO_TIMEOUT) 
            // en millisecondes qui est le timeout d'attente des donn�es.
            int timeoutSocket = 7500;
            HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
            //R�cup�ration de la r�ponse HTTP
            HttpResponse httpResponse = httpClient.execute(httpPost);
            
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();  
            
        //R�cup�ration des erreurs
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
         
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n"); //Ajout d'un saut de ligne
            }
            is.close();
            json = sb.toString();
        //R�cup�ration des erreurs
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
 
        // On essaye de parser l'objet String en JSON object
        try {
            jObj = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
 
        // retourne JSON Object
        Log.v("TAG","Fin fonction JSONParser"+jObj);
        return jObj; 
    }
}
 