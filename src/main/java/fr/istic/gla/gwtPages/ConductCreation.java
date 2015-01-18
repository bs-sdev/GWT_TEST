package fr.istic.gla.gwtPages;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;

import fr.istic.gla.jsontools.JsonTool;
import fr.istic.gla.model.Conducteur;

public class ConductCreation extends HttpRequestGWT {

	private String urlHttp;
	
	private JSONObject jsonToSend;

	public ConductCreation() {
		super();
		this.urlHttp = "http://localhost:8080/rest/conducteur/create";
		jsonToSend = new JSONObject();
	}
	
	public void addPanelToPage(final ConductContentLayout pageCond) {
		
		FlowPanel bodyConduct = generateForm(pageCond);
		
		FlowPanel panel = new FlowPanel();

		panel.add(bodyConduct);
		
		SimplePanel myWidget = new SimplePanel();

		myWidget.add(panel);
		
		pageCond.setBody(myWidget.asWidget());
	}
	
	

	public FlowPanel generateForm(final ConductContentLayout pageCond) {

		FlowPanel panel = new FlowPanel();
		panel.setSize("380px", "200px");

		Label lbl1 = new Label("Nom : ");
		TextBox txt1 = new TextBox();
		txt1.getElement().setId("conducteur-nom");
		

		Label lbl2 = new Label("Prénom : ");
		TextBox txt2 = new TextBox();

		Label lbl3 = new Label("Date Naissance : ");
		TextBox txt3 = new TextBox();

		Label lbl4 = new Label("Mail : ");
		TextBox txt4 = new TextBox();
		
		Button valider = new Button();
		valider.setText("Valider");
		
		valider.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				//Window.alert("bordel => " + DOM.getElementById("conducteur-nom").getAttribute("value"));
				Window.alert("bordel => " + RootPanel.get("conducteur-nom").asWidget());
				
				TextBox youhou = (TextBox) RootPanel.get("conducteur-nom").asWidget();
				
				Window.alert(youhou.getElement().getNodeValue());
				
				ConductCreation conductCreation = new ConductCreation();
				conductCreation.createConducteur(pageCond.getContent());
			}
		});

		panel.add(lbl1);
		panel.add(txt1);
		panel.add(lbl2);
		panel.add(txt2);
		panel.add(lbl3);
		panel.add(txt3);
		panel.add(lbl4);
		panel.add(txt4);
		panel.add(valider);

		return panel;
	}
	
	public void createConducteur(final ConductContentLayout pageCond) {
	
		RequestCallback callback = new RequestCallback() {
			public void onResponseReceived(Request arg0, Response response) {
				
				Window.alert("Dans le requestCallBack => " + response.getStatusCode());
				
				if (response.getStatusCode() == 200) {
					jsonToSend = (JSONObject) getFromForm(pageCond);
				}
				else {
					Window.alert("L");
				}
				
			}
			
			public void onError(Request request, Throwable exception) {
				Window.alert(exception.getMessage());
			}
		};
		Window.alert("msg1");
		String strHeader = "Content-Type,application/json";
		Window.alert("msg2\n" + callback.toString() + "\n" + strHeader + "\n" +jsonToSend.toString());
		this.executePOST(urlHttp, callback, strHeader , jsonToSend.toString());
		Window.alert("msg3");
	}
	
	public JSONValue getFromForm(ConductContentLayout pageCond) {
		Conducteur conducteur = new Conducteur();

		Window.alert("fuck");
		
		Window.alert(RootPanel.get("conducteur-nom").getElement().getInnerText());
		
		if( RootPanel.get("conducteur-nom").getElement().getString() != null   ) {
			conducteur.setNom(RootPanel.get("conducteur-nom").getElement().getInnerText());
			Window.alert("conducteur.getNom => " + conducteur.getNom());
		}
		
		JSONObject json = new JSONObject();
		
		json.put("nom", new JSONString(conducteur.getNom()));
		
		return json;
	}

}
