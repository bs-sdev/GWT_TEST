package fr.istic.gla.gwtPages;

import java.util.List;

import fr.istic.gla.jsontools.JsonTool;
import fr.istic.gla.model.Conducteur;
import fr.istic.gla.model.Covoiturage;
import fr.istic.gla.model.Evenement;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;


public class ConductById extends HttpRequestGWT {
	
	private String urlHttp;

	/**
	 * 
	 */
	public ConductById(int id) {
		super();
		this.urlHttp = "http://localhost:8080/rest/conducteur/" + id;
	}

	public void getConductById(final ConductContentLayout pageCond) {
		
		RequestCallback callback = new RequestCallback() {
			
			public void onResponseReceived(Request arg0, Response response) {
				Window.alert("Dans le callback response = "  + response.getText());
				Conducteur conduct = new Conducteur();
					
				JsonTool myTool = new JsonTool();
				
				JSONValue jsonValue = JSONParser.parseStrict(response.getText());
				
				Conducteur conducteur  = myTool.fromJson(jsonValue, conduct);
				
				FlowPanel bodyConduct = generateForm(conducteur);
				
				FlowPanel panel = new FlowPanel();

				panel.add(bodyConduct);
				
				SimplePanel myWidget = new SimplePanel();

				myWidget.add(panel);
				
				pageCond.setBody(myWidget.asWidget());
			}
			
			public void onError(Request arg0, Throwable arg1) {
				Window.alert("getConductById ERROR");
			}
			
		};
		
		this.executeGET(urlHttp, callback);
	}
	
	public FlowPanel generateForm(Conducteur conducteur) {
		
		FlowPanel panel = new FlowPanel();
		panel.setSize("380px", "200px");
		FlexTable flexTable = new FlexTable();
		
		Label lbl1 = new Label("Nom : ");
		TextBox txt1 = new TextBox();
		txt1.getElement().setId("conducteur-nom");

		if(conducteur.getNom() != null) {
			txt1.setValue(conducteur.getNom());
		}
		else {
			txt1.setValue("non renseigné");
		}
		
		Label lbl2 = new Label("Prénom : ");
		TextBox txt2 = new TextBox();

		if(conducteur.getPrenom() != null) {
			txt2.setValue(conducteur.getPrenom());
		}
		else {
			txt2.setValue("non renseigné");
		}
		
		Label lbl3 = new Label("Date Naissance : ");
		TextBox txt3 = new TextBox();

		if(conducteur.getDateNaissance() != null) {
			txt3.setValue(conducteur.getDateNaissance().toString());
		}
		else {
			txt3.setValue("non renseigné");
		}
		
		Label lbl4 = new Label("Mail : ");
		TextBox txt4 = new TextBox();

		if(conducteur.getMail() != null) {
			txt4.setValue(conducteur.getMail());
		}
		else {
			txt4.setValue("non renseigné");
		}
		
		Label lbl5 = new Label("Covoiturage : ");
		TextBox txt5 = new TextBox();
		txt5.setValue("");
		
		if(conducteur.getCovoiturages() != null) {
			flexTable = this.generateCovoitFromConduct(conducteur.getCovoiturages());
		}
		else {
			txt5.setValue("non renseigné");
		}
		
		Label lbl6 = new Label("Vehicule : ");
		TextBox txt6 = new TextBox();

		if(conducteur.getVehicule() != null) {
			txt6.setValue(conducteur.getVehicule().getMarque() + " " + conducteur.getVehicule().getModele());
		}
		else {
			txt6.setValue("non renseigné");
		}
		
		panel.add(lbl1);
		panel.add(txt1);
		panel.add(lbl2);
		panel.add(txt2);
		panel.add(lbl3);
		panel.add(txt3);
		panel.add(lbl4);
		panel.add(txt4);
		panel.add(lbl5);
		
		if(!txt5.toString().equalsIgnoreCase("")) {
			panel.add(txt5);
		}
		else {
			panel.add(flexTable);
		}
		
		panel.add(lbl6);
		panel.add(txt6);

		return panel;
	}
	
	public FlexTable generateCovoitFromConduct(List<Covoiturage> listCovoit) {
		FlexTable flexTable = new FlexTable();
		int row = 0;
		if(!listCovoit.isEmpty()) {
		
			for( Covoiturage covoit : listCovoit ) {
				flexTable.setHTML(row, 0, covoit.getLieuDepart()); 
				row++;
			}
		
		}
		
		return flexTable;
	}
}
