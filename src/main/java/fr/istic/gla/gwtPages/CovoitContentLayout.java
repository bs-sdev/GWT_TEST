package fr.istic.gla.gwtPages;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;

public class CovoitContentLayout extends ContentLayout{

	public CovoitContentLayout() {
		this.setHeader();
	}
	
	public CovoitContentLayout getContent(){
		return this;
	}
	
	@Override
	public void setHeader() {
		this.getHeader().clear();
		
		SimplePanel myWidget = new SimplePanel();
		
		FlowPanel myFlowPanel = new FlowPanel();
		
		Button covoitLoad = new Button();
		covoitLoad.setText("Chargement du Covoiturage ");
		
		Button covoitLoadAll = new Button();
		covoitLoadAll.setText("Chargement des Covoiturages");
		
		this.getLbl().setText("Saisir un id : ");
		this.getLbl().getElement().setId("lbl");
		
		covoitLoad.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				
				int number = Integer.parseInt(getContent().getTxt().getValue() != "" ? getContent().getTxt().getValue() : "1");
				
				CovoitById requestCovoitById = new CovoitById(number);
				requestCovoitById.getCovoiturageById(getContent());
			}
		});
		
		covoitLoadAll.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				CovoitListerTout requestListerTout = new CovoitListerTout();
				requestListerTout.getCovoiturageAll(getContent());
			}
		});

		myFlowPanel.add(covoitLoad);
		myFlowPanel.add(covoitLoadAll);
		myFlowPanel.add(this.getLbl());
		myFlowPanel.add(this.getTxt());
		
		myWidget.add(myFlowPanel);
		
		this.getHeader().add(myWidget);
	}

	
}
