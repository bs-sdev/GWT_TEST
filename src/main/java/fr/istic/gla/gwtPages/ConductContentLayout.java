package fr.istic.gla.gwtPages;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class ConductContentLayout extends ContentLayout{

	public ConductContentLayout() {
		this.setHeader();
	}
	
	public ConductContentLayout getContent() {
		return this;
	}
	@Override
	public void setHeader() {
		this.getHeader().clear();
		
		SimplePanel myWidget = new SimplePanel();
		
		FlowPanel myFlowPanel = new FlowPanel();
		
		Button conducteurLoad = new Button();
		conducteurLoad.setText("Chargement du covoitureur");
		
		Button conducteurLoadAll = new Button();
		conducteurLoadAll.setText("Chargement des covoitureurs");
		
		Button conducteurCreation = new Button();
		conducteurCreation.setText("Création d'un covoitureur");
		
		this.getLbl().setText("Saisir un id : ");
		this.getLbl().getElement().setId("lbl");
		
		conducteurLoad.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				
				int number = Integer.parseInt(getContent().getTxt().getValue() != "" ? getContent().getTxt().getValue() : "1");
				
				ConductById requestConductById = new ConductById(number);
				requestConductById.getConductById(getContent());	
			}
		});
		
		conducteurLoadAll.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				ConductListerTout requestConductListerTout = new ConductListerTout();
				requestConductListerTout.getConductAll(getContent());	
			}
		});
		
		conducteurCreation.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				ConductCreation conductCreation = new ConductCreation();
				conductCreation.addPanelToPage(getContent());
			}
		});

		myFlowPanel.add(conducteurLoad);
		myFlowPanel.add(conducteurLoadAll);
		myFlowPanel.add(conducteurCreation);
		myFlowPanel.add(this.getLbl());
		myFlowPanel.add(this.getTxt());
		
		myWidget.add(myFlowPanel);
		
		this.setHeader(myWidget);
	}
}
