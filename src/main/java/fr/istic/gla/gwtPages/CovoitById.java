package fr.istic.gla.gwtPages;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import fr.istic.gla.jsontools.JsonTool;
import fr.istic.gla.model.Covoiturage;

public class CovoitById extends HttpRequestGWT{
	private String urlHttp;
	
	/**
	 * 
	 */
	public CovoitById(int id) {
		super();
		this.urlHttp = "http://localhost:8080/rest/covoiturage/" + id;
	}
	
	public void getCovoiturageById(final CovoitContentLayout pageCovoit) {
		
		RequestCallback callback = new RequestCallback() {

			public void onResponseReceived(Request arg0, Response response) {
				Covoiturage covoit = new Covoiturage();
					
				JsonTool myTool = new JsonTool();
				
				JSONValue jsonValue = JSONParser.parseStrict(response.getText());
				
				Covoiturage covoiturage  = myTool.fromJson(jsonValue, covoit);

				FlowPanel bodyCovoitById = generateForm(covoiturage);
				
				FlowPanel panel = new FlowPanel();

				panel.add(bodyCovoitById);
				
				SimplePanel myWidget = new SimplePanel();

				myWidget.add(panel);
				
				pageCovoit.setBody(myWidget.asWidget());

			}
			
			public void onError(Request arg0, Throwable arg1) {
				Window.alert("getCovoiturageById ERROR");
			}
			
		};
		
		this.executeGET(urlHttp, callback);
	}
	
	/**
	 * 
	 */
	public FlowPanel generateForm(Covoiturage covoiturage) {
		Label lbl1 = new Label("Evenement : ");
		TextBox txt1 = new TextBox();
		
		if(covoiturage.getEvenement() != null) {
			txt1.setValue(covoiturage.getEvenement().getLieuEvt());
		}
		else {
			txt1.setValue("non renseigné");
		}
		
		Label lbl2 = new Label("Lieu : ");
		TextBox txt2 = new TextBox();
		
		if(covoiturage.getLieuDepart() != null) {
			txt2.setValue(covoiturage.getLieuDepart());
		}
		else {
			txt2.setValue("non renseigné");
		}
		
		Label lbl3 = new Label("Date : ");
		TextBox txt3 = new TextBox();
		
		if(covoiturage.getDateDepart() != null) {
			txt3.setValue(covoiturage.getDateDepart().toString());
		}
		else {
			txt3.setValue("non renseigné");
		}
		
		Label lbl4 = new Label("Conducteur : ");
		TextBox txt4 = new TextBox();
		
		if(covoiturage.getConducteur() != null) {
			txt4.setValue(covoiturage.getConducteur().getNom() + " " + covoiturage.getConducteur().getPrenom());
		}
		else {
			txt4.setValue("non renseigné");
		}
		
		FlowPanel panel = new FlowPanel();
		panel.setSize("380px", "200px");
		
		panel.add(lbl1);
		panel.add(txt1);
		panel.add(lbl2);
		panel.add(txt2);
		panel.add(lbl3);
		panel.add(txt3);
		panel.add(lbl4);
		panel.add(txt4);
		
		return panel;
	}
	
	
	

}
