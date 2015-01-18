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
import fr.istic.gla.model.Covoiturage;
import fr.istic.gla.model.Evenement;

public class EvtListerTout extends HttpRequestGWT{
	
	private String urlHttp;

	/**
	 * 
	 */
	public EvtListerTout() {
		super();
		this.urlHttp = "http://localhost:8080/rest/evenement/";
	}
	
	public void getEvtAll(final EvtContentLayout pageEvt){
		
		RequestCallback callback = new RequestCallback() {

			public void onResponseReceived(Request arg0, Response response) {
				Window.alert("Dans le callback response = " + response.getText());
				Evenement evenement = new Evenement();

				JsonTool myTool = new JsonTool();

				JSONArray jsonArray = JSONParser
						.parseStrict(response.getText()).isArray();

				List<Evenement> listEvenement = myTool.fromJson(jsonArray, evenement);
				
				FlexTable tableToGenerate = new FlexTable();

				generateHeaderTable(tableToGenerate);
				
				generateCovoiturages(listEvenement, tableToGenerate);
				
				SimplePanel myWidget = new SimplePanel();

				myWidget.add(tableToGenerate);
				
				pageEvt.setBody(myWidget.asWidget());
			}

			public void onError(Request arg0, Throwable arg1) {
				Window.alert("getEvtAll ERROR");
			}

		};
		
		this.executeGET(urlHttp, callback);
	}
	
	private void generateHeaderTable(FlexTable flexTable) {
		
		flexTable.setHTML(0, 0, "Lieu évènement");
		flexTable.setHTML(0, 1, "Covoiturage");
		
		flexTable.setBorderWidth(2);
	}
	
	private void generateCovoiturages(List<Evenement> listEvt, FlexTable flexTable) {
		
		int row = 1;
		
		for( Evenement evenement : listEvt ) {
			if(evenement.getLieuEvt() != null) {
				flexTable.setHTML(row, 0, evenement.getLieuEvt());
			}
			else {
				flexTable.setHTML(row, 0, "non renseigné");
			}

			if(evenement.getCovoiturage()  != null) {
				ListBox listCovoit = getListBox(true, evenement.getCovoiturage());
				
				flexTable.setWidget(row, 1, listCovoit);
			}
			else {
				flexTable.setHTML(row, 1, "non renseigné");
				
				// Test 
				//ListBox listCovoit = getListBoxTest(true);
				//flexTable.setWidget(row, 1, listCovoit);
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
	    widget.addStyleName("ListBox-evt-covoits");
	    
	    for(int i = 0; i < 10; i++) {
	    	widget.addItem("blaaaaaaaa");
	    }
	    
	    if(!dropdown)
	    	widget.setVisibleItemCount(3);
	    return widget;
	}
}
