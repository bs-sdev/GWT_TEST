package fr.istic.gla.gwtPages;

import java.util.Date;
import java.util.List;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

import fr.istic.gla.jsontools.JsonTool;
import fr.istic.gla.model.Covoiturage;



public class CovoitListerTout extends HttpRequestGWT {

	private String urlHttp;
	
	/**
	 * 
	 */
	public CovoitListerTout() {
		super();
		this.urlHttp = "http://localhost:8080/rest/covoiturage/";
	}
	
	public void getCovoiturageAll(final CovoitContentLayout pageCovoit){
		
		RequestCallback callback = new RequestCallback() {
			
			public void onResponseReceived(Request arg0, Response response) {
				Covoiturage covoit = new Covoiturage();

				JsonTool myTool = new JsonTool();
				
				JSONArray jsonArray = JSONParser.parseStrict(response.getText()).isArray();
				
				List<Covoiturage> listCovoit  = myTool.fromJson(jsonArray, covoit);
				
				//Window.alert("" + pageCovoit.getLbl());
				//Window.alert("" + pageCovoit.getTxt());
				
				//Window.alert(RootPanel.get("lbl").toString());
				
				//RootPanel.get().remove(RootPanel.get("lbl").asWidget());
				
				//Window.alert(RootPanel.get("lbl").toString());
				
				FlexTable tableToGenerate = new FlexTable();

				generateHeaderTable(tableToGenerate);
				
				generateCovoiturages(listCovoit, tableToGenerate);
				
				SimplePanel myWidget = new SimplePanel();

				myWidget.add(tableToGenerate);
				
				pageCovoit.setBody(myWidget.asWidget());
				
				
			}
			
			public void onError(Request arg0, Throwable arg1) {
				Window.alert("getCovoiturageAll ERROR");
			}
			
		};

		this.executeGET(urlHttp, callback);
	}
	
	
	private void generateHeaderTable(FlexTable flexTable) {
		
		flexTable.setHTML(0, 0, "Évènement");
		flexTable.setHTML(0, 1, "Lieu");
		flexTable.setHTML(0, 2, "Date");
		flexTable.setHTML(0, 3, "Conducteur");
		
		flexTable.setBorderWidth(2);
	}
	
	
	private void generateCovoiturages(List<Covoiturage> listCovoit, FlexTable flexTable) {
		
		int row = 1;
		
		for( Covoiturage covoit : listCovoit ) {
			if(covoit.getEvenement() != null) {
				flexTable.setHTML(row, 0, covoit.getEvenement().getLieuEvt());
			}
			else {
				flexTable.setHTML(row, 0, "non renseigné");
			}

			if(covoit.getLieuDepart() != null) {
				flexTable.setHTML(row, 1, covoit.getLieuDepart());
			}
			else {
				flexTable.setHTML(row, 1, "non renseigné");
			}
			
			if(covoit.getDateDepart() != null) {
				flexTable.setHTML(row, 2, covoit.getDateDepart().toString());
			}
			else {
				flexTable.setHTML(row, 2, "non renseigné");
			}
			
			if(covoit.getConducteur() != null) {
				flexTable.setHTML(row, 3, covoit.getConducteur().getNom() + " " + covoit.getConducteur().getPrenom());
			}
			else {
				flexTable.setHTML(row, 3, "non renseigné");
			}
			
			row++;
		}
	}
}
