package fr.istic.gla.gwtPages;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class EvtContentLayout extends ContentLayout {
	
	public EvtContentLayout() {
		this.setHeader();
	}
	
	public EvtContentLayout getContent(){
		return this;
	}
	
	@Override
	public void setHeader() {
		this.getHeader().clear();
		
		SimplePanel myWidget = new SimplePanel();
		
		FlowPanel myFlowPanel = new FlowPanel();
		
		com.google.gwt.user.client.ui.Button evtLoad = new Button();
		evtLoad.setText("Chargement de l'évènement ");
		
		com.google.gwt.user.client.ui.Button evtLoadAll = new Button();
		evtLoadAll.setText("Chargement des évènements");
		
		this.getLbl().setText("Saisir un id : ");
		this.getLbl().getElement().setId("lbl");
		
		evtLoad.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				
				int number = Integer.parseInt(getContent().getTxt().getValue() != "" ? getContent().getTxt().getValue() : "1");
				
				EvtById requestEvtById = new EvtById(number);
				requestEvtById.getEvtById(getContent());
				
			}
		});
		
		evtLoadAll.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				EvtListerTout requestEvtListerTout = new EvtListerTout();
				requestEvtListerTout.getEvtAll(getContent());
				
			}
		});

		myFlowPanel.add(evtLoad);
		myFlowPanel.add(evtLoadAll);
		myFlowPanel.add(this.getLbl());
		myFlowPanel.add(this.getTxt());
		
		myWidget.add(myFlowPanel);
		
		this.setHeader(myWidget);
	}
}
