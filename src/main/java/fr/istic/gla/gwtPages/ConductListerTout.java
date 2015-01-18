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
import fr.istic.gla.model.Conducteur;
import fr.istic.gla.model.Covoiturage;
import fr.istic.gla.model.Evenement;

public class ConductListerTout extends HttpRequestGWT {

	private String urlHttp;
	
	public ConductListerTout() {
		super();
		this.urlHttp = "http://localhost:8080/rest/conducteur/";
	}
	
	public void getConductAll(final ConductContentLayout pageCond){
		Window.alert("getConductAll");
		
		RequestCallback callback = new RequestCallback() {

			public void onResponseReceived(Request arg0, Response response) {
				Window.alert("Dans le callback response = "
						+ response.getText());
				Conducteur conducteur = new Conducteur();

				JsonTool myTool = new JsonTool();

				JSONArray jsonArray = JSONParser
						.parseStrict(response.getText()).isArray();

				List<Conducteur> listConducteur = myTool.fromJson(jsonArray, 
						conducteur);

				FlexTable tableToGenerate = new FlexTable();

				generateHeaderTable(tableToGenerate);
				
				generateCovoiturages(listConducteur, tableToGenerate);
				
				SimplePanel myWidget = new SimplePanel();

				myWidget.add(tableToGenerate);
				
				pageCond.setBody(myWidget.asWidget());
				
			}

			public void onError(Request arg0, Throwable arg1) {
				Window.alert("getConductAll ERROR");
			}

		};
		
		Window.alert("getConductAll FIN");
		this.executeGET(urlHttp, callback);
	}
	
	private void generateHeaderTable(FlexTable flexTable) {
		
		flexTable.setHTML(0, 0, "Nom");
		flexTable.setHTML(0, 1, "Prénom");
		flexTable.setHTML(0, 2, "Date Naissance");
		flexTable.setHTML(0, 3, "Mail");
		flexTable.setHTML(0, 4, "Covoiturage");
		flexTable.setHTML(0, 5, "Vehicule");
		
		
		flexTable.setBorderWidth(2);
	}
	
	private void generateCovoiturages(List<Conducteur> listCond, FlexTable flexTable) {
		
		int row = 1;
		
		for( Conducteur conducteur : listCond ) {
			if(conducteur.getNom() != null) {
				flexTable.setHTML(row, 0, conducteur.getNom());
			}
			else {
				flexTable.setHTML(row, 0, "non renseigné");
			}
			
			if(conducteur.getPrenom() != null) {
				flexTable.setHTML(row, 1, conducteur.getPrenom());
			}
			else {
				flexTable.setHTML(row, 1, "non renseigné");
			}
			
			if(conducteur.getDateNaissance() != null) {
				flexTable.setHTML(row, 2, conducteur.getDateNaissance() + "");
			}
			else {
				flexTable.setHTML(row, 2, "non renseigné");
			}
			
			if(conducteur.getMail() != null) {
				flexTable.setHTML(row, 3, conducteur.getMail());
			}
			else {
				flexTable.setHTML(row, 3, "non renseigné");
			}
			
			if(conducteur.getCovoiturages() != null) {
				ListBox listCovoit = getListBox(true, conducteur.getCovoiturages());
				
				flexTable.setWidget(row, 4, listCovoit);
			}
			else {
				flexTable.setHTML(row, 4, "non renseigné");
				
				// Test 
				//ListBox listCovoit = getListBoxTest(true);
				//flexTable.setWidget(row, 1, listCovoit);
			}

			if(conducteur.getVehicule() != null) {
				flexTable.setHTML(row, 5, conducteur.getVehicule().getMarque() + " " + conducteur.getVehicule().getModele());
			}
			else {
				flexTable.setHTML(row, 5, "non renseigné");
			}
			
			row++;
		}
	}
	
	/**
	 * Fonction permettant de récupérer l'ensemble des lieux de départs de covoiturages dans
	 * dans une liste de covoiturages issue d'un évènement
	 * 
	 * @param dropdown
	 * @param listCovoit
	 * @return
	 */
	private ListBox getListBox(boolean dropdown, List<Covoiturage> listCovoit) {
	    ListBox widget = new ListBox();
	    widget.addStyleName("ListBox-evt-covoits");
	    
	    for(Covoiturage covoiturage : listCovoit) {
	    	widget.addItem(covoiturage.getLieuDepart());
	    }
	    
	    if(!dropdown)
	    	widget.setVisibleItemCount(3);
	    return widget;
	}
	
	/**
	 * Fonction de test 
	 * 
	 * @param dropdown
	 * @return
	 */
	private ListBox getListBoxTest(boolean dropdown) {
	    ListBox widget = new ListBox();
	    widget.addStyleName("ListBox-cond-covoits");
	    
	    for(int i = 0; i < 10; i++) {
	    	widget.addItem("blaaaaaaaa");
	    }
	    
	    if(!dropdown)
	    	widget.setVisibleItemCount(3);
	    return widget;
	}
}
