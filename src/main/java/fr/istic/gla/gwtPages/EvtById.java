package fr.istic.gla.gwtPages;

import java.util.ArrayList;
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

public class EvtById extends HttpRequestGWT {
	private String urlHttp;

	/**
	 * 
	 */
	public EvtById(int id) {
		super();
		this.urlHttp = "http://localhost:8080/rest/evenement/" + id;
	}
	
	
	public void getEvtById(final EvtContentLayout pageEvt) {
		
		RequestCallback callback = new RequestCallback() {
			
			public void onResponseReceived(Request arg0, Response response) {
				Window.alert("Dans le callback response = "  + response.getText());
				Evenement evt = new Evenement();
					
				JsonTool myTool = new JsonTool();
				
				JSONValue jsonValue = JSONParser.parseStrict(response.getText());
				
				Evenement evenement  = myTool.fromJson(jsonValue, evt);
				
				FlowPanel bodyEvtById = generateForm(evenement);
				
				FlowPanel panel = new FlowPanel();

				panel.add(bodyEvtById);
				
				SimplePanel myWidget = new SimplePanel();

				myWidget.add(panel);
				
				pageEvt.setBody(myWidget.asWidget());
			}
			
			public void onError(Request arg0, Throwable arg1) {
				Window.alert("getCovoiturageById ERROR");
			}
		};
		
		this.executeGET(urlHttp, callback);		
	}
	
	public FlowPanel generateForm(Evenement evenement) {
		Label lbl1 = new Label("Lieu : ");
		TextBox txt1 = new TextBox();

		if(evenement.getLieuEvt() != null) {
			txt1.setValue(evenement.getLieuEvt());
		}
		else {
			txt1.setValue("non renseigné");
		}
		
		Label lbl2 = new Label("Covoiturage : ");
		TextBox txt2 = new TextBox();
		txt2.setValue("");
		
		FlexTable flexTable = new FlexTable();
		
		if(evenement.getCovoiturage() != null) {
			flexTable = this.generateCovoitFromEvt(evenement.getCovoiturage());
		}
		else {
			txt2.setValue("non renseigné");
		}
		
		
		FlowPanel panel = new FlowPanel();
		panel.setSize("380px", "200px");
		
		panel.add(lbl1);
		panel.add(txt1);
		panel.add(lbl2);
		
		if(!txt2.toString().equalsIgnoreCase("")) {
			panel.add(txt2);
		}
		else {
			panel.add(flexTable);
		}
		
		return panel;
	}
	
	public FlexTable generateCovoitFromEvt(List<Covoiturage> listCovoit) {
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