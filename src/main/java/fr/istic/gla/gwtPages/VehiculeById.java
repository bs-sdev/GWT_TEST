package fr.istic.gla.gwtPages;

import java.util.List;

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

import fr.istic.gla.jsontools.JsonTool;
import fr.istic.gla.model.Covoiturage;
import fr.istic.gla.model.Evenement;
import fr.istic.gla.model.Vehicule;

public class VehiculeById extends HttpRequestGWT {
	
	private String urlHttp;

	/**
	 * 
	 */
	public VehiculeById(int id) {
		super();
		this.urlHttp = "http://localhost:8080/rest/vehicule/" + id;
	}
	
	public void getVehiculeById(final VehiculeContentLayout pageEvt) {
		Window.alert("getVehiculeById");
		
		RequestCallback callback = new RequestCallback() {
			
			public void onResponseReceived(Request arg0, Response response) {
				Window.alert("Dans le callback response = "  + response.getText());
				Vehicule vhl = new Vehicule();
					
				JsonTool myTool = new JsonTool();
				
				JSONValue jsonValue = JSONParser.parseStrict(response.getText());
				
				Vehicule vehicule = myTool.fromJson(jsonValue, vhl);
				
				FlowPanel bodyEvtById = generateForm(vehicule);
				
				FlowPanel panel = new FlowPanel();

				panel.add(bodyEvtById);
				
				SimplePanel myWidget = new SimplePanel();

				myWidget.add(panel);
				
				pageEvt.setBody(myWidget.asWidget());
			}
			
			public void onError(Request arg0, Throwable arg1) {
				Window.alert("getVehiculeById ERROR");
			}
			
		};
		
		Window.alert("getVehiculeById FIN");
		this.executeGET(urlHttp, callback);
	}
	
	public FlowPanel generateForm(Vehicule vehicule) {
		Label lbl1 = new Label("Marque");
		TextBox txt1 = new TextBox();
		
		if(vehicule.getMarque() != null) {
			txt1.setValue(vehicule.getMarque());
		}
		else {
			txt1.setValue("non renseigné");
		}
		
		Label lbl2 = new Label("Modèle");
		TextBox txt2 = new TextBox();
		
		if(vehicule.getModele() != null) {
			txt2.setValue(vehicule.getModele());
		}
		else {
			txt2.setValue("non renseigné");
		}
		
		Label lbl3 = new Label("NbPlace");
		TextBox txt3 = new TextBox();
		
		if(vehicule.getNbPlace() > 0) {
			txt3.setValue("" + vehicule.getNbPlace());
		}
		else {
			txt3.setValue("non renseigné");
		}
		
		Label lbl4 = new Label("Conducteur");
		TextBox txt4 = new TextBox();
		
		Window.alert("Nom : " + vehicule.getConducteur().getNom());
		
		if(vehicule.getConducteur() != null) {
			txt4.setValue(vehicule.getConducteur().getNom() + " " + vehicule.getConducteur().getPrenom());
		}
		else {
			txt4.setValue("non renseigné");
		}
		
		Label lbl5 = new Label("Covoiturage : ");
		TextBox txt5 = new TextBox();
		
		if(vehicule.getCovoiturage() != null) {
			txt5.setValue(vehicule.getCovoiturage().getLieuDepart());
		}
		else {
			txt5.setValue("non renseigné");
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
		panel.add(lbl5);
		panel.add(txt5);

		
		return panel;
	}

	
}
