package fr.istic.gla.gwtPages;

import java.util.List;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;

import fr.istic.gla.jsontools.JsonTool;
import fr.istic.gla.model.Evenement;
import fr.istic.gla.model.Vehicule;

public class VehiculeListerTout extends HttpRequestGWT {
private String urlHttp;
	
	public VehiculeListerTout() {
		super();
		this.urlHttp = "http://localhost:8080/rest/vehicule/";
	}
	
	public void getVehiculeAll(final VehiculeContentLayout pageVehicule){
		Window.alert("getVehiculeAll");
		
		RequestCallback callback = new RequestCallback() {

			public void onResponseReceived(Request arg0, Response response) {
				Window.alert("Dans le callback response = "
						+ response.getText());
				
				Vehicule vehicule = new Vehicule();
				
				JsonTool myTool = new JsonTool();

				JSONArray jsonArray = JSONParser
						.parseStrict(response.getText()).isArray();

				List<Vehicule> listVehicule = myTool.fromJson(jsonArray, vehicule);
				
				FlexTable tableToGenerate = new FlexTable();

				generateHeaderTable(tableToGenerate);
				
				generateVehicules(listVehicule, tableToGenerate);
				
				SimplePanel myWidget = new SimplePanel();

				myWidget.add(tableToGenerate);
				
				pageVehicule.setBody(myWidget.asWidget());
			}

			public void onError(Request arg0, Throwable arg1) {
				// TODO Auto-generated method stub
				Window.alert("getVehiculeAll ERROR");
			}

		};
		
		Window.alert("getVehiculeAll FIN");
		this.executeGET(urlHttp, callback);
	}
	
	private void generateHeaderTable(FlexTable flexTable) {
		
		flexTable.setHTML(0, 0, "Marque");
		flexTable.setHTML(0, 1, "Modèle");
		flexTable.setHTML(0, 2, "NbPlace");
		flexTable.setHTML(0, 3, "Conducteur");
		flexTable.setHTML(0, 4, "Covoiturage");
		
		flexTable.setBorderWidth(2);
	}
	
	private void generateVehicules(List<Vehicule> listVehicule, FlexTable flexTable) {
		
		int row = 1;
		
		for( Vehicule vehicule : listVehicule ) {
			if(vehicule.getMarque() != null) {
				flexTable.setHTML(row, 0, vehicule.getMarque());
			}
			else {
				flexTable.setHTML(row, 0, "non renseigné");
			}

			if(vehicule.getModele()  != null) {
				flexTable.setHTML(row, 1, vehicule.getModele());
			}
			else {
				flexTable.setHTML(row, 1, "non renseigné");
			}
			
			if(vehicule.getNbPlace()  > 0) {
				flexTable.setHTML(row, 2, vehicule.getNbPlace() + "");
			}
			else {
				flexTable.setHTML(row, 2, "non renseigné");
			}
			
			if(vehicule.getConducteur() != null) {
				flexTable.setHTML(row, 3, vehicule.getConducteur().getNom() + " " + vehicule.getConducteur().getPrenom());
			}
			else {
				flexTable.setHTML(row, 3, "non renseigné");
			}
			
			if(vehicule.getCovoiturage() != null) {
				flexTable.setHTML(row, 4, vehicule.getCovoiturage().getLieuDepart());
			}
			else {
				flexTable.setHTML(row, 4, "non renseigné");
			}
			
			row++;
		}
	}
}
