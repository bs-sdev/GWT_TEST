package fr.istic.gla.gwtPages;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class VehiculeContentLayout extends ContentLayout {
	
	public VehiculeContentLayout() {
		this.setHeader();
	}

	public VehiculeContentLayout getContent() {
		return this;
	}
	
	@Override
	public void setHeader() {
		this.getHeader().clear();
		
		SimplePanel myWidget = new SimplePanel();
		
		FlowPanel myFlowPanel = new FlowPanel();
		
		com.google.gwt.user.client.ui.Button vehiculeLoad = new Button();
		vehiculeLoad.setText("Chargement du véhicule");
		
		com.google.gwt.user.client.ui.Button vehiculeLoadAll = new Button();
		vehiculeLoadAll.setText("Chargement des véhicules");
		
		this.getLbl().setText("Saisir un id : ");
		this.getLbl().getElement().setId("lbl");
		
		vehiculeLoad.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				
				int number = Integer.parseInt(getContent().getTxt().getValue() != "" ? getContent().getTxt().getValue() : "1");
				
				VehiculeById requestVehiculeById = new VehiculeById(number);
				requestVehiculeById.getVehiculeById(getContent());
			}
		});
		
		vehiculeLoadAll.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				
				int number = Integer.parseInt(getContent().getTxt().getValue() != "" ? getContent().getTxt().getValue() : "1");
				
				VehiculeListerTout requestVehiculeListerTout = new VehiculeListerTout();
				requestVehiculeListerTout.getVehiculeAll(getContent());	
			}
		});

		myFlowPanel.add(vehiculeLoad);
		myFlowPanel.add(vehiculeLoadAll);
		myFlowPanel.add(this.getLbl());
		myFlowPanel.add(this.getTxt());
		
		myWidget.add(myFlowPanel);
		
		this.setHeader(myWidget);
	}
	
	
}
