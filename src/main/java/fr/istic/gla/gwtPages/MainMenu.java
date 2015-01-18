package fr.istic.gla.gwtPages;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class MainMenu {
	
	public Widget getMenu(){
		final SimplePanel myWidget = new SimplePanel();
		
		FlowPanel myFlowPanel = new FlowPanel();
		
		Button pageCovoiturages = generateBtnCovoit(myWidget);
		
		Button pageEvenements = generateBtnEvt(myWidget);
		
		Button pageVehicules = generateBtnVehicule(myWidget);
		
		Button pageCovoitureurs = generateBtnCovoitureurs(myWidget);
		
		myFlowPanel.add(pageCovoiturages);
		myFlowPanel.add(pageEvenements);
		myFlowPanel.add(pageVehicules);
		myFlowPanel.add(pageCovoitureurs);
		
		myWidget.add(myFlowPanel);
		
		return myWidget;
	}
	
	private Button generateBtnCovoit(final SimplePanel myWidget) {
		com.google.gwt.user.client.ui.Button pageCovoiturages = new Button();
		pageCovoiturages.setText("Covoiturages ");
		
		pageCovoiturages.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				RootPanel.get().clear();
				RootPanel.get().add(myWidget);
				RootPanel.get().add(new CovoitContentLayout().getHeader());
			}
		});
		
		return pageCovoiturages;
	}
	
	private Button generateBtnEvt(final SimplePanel myWidget) {
		com.google.gwt.user.client.ui.Button pageEvenements = new Button();
		pageEvenements.setText("Evenements");
		
		pageEvenements.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				RootPanel.get().clear();
				RootPanel.get().add(myWidget);
				RootPanel.get().add(new EvtContentLayout().getHeader());
			}
		});
		
		return pageEvenements;
	}
	
	private Button generateBtnVehicule(final SimplePanel myWidget) {
		com.google.gwt.user.client.ui.Button pageVehicules = new Button();
		pageVehicules.setText("Vehicules");
		
		pageVehicules.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				RootPanel.get().clear();
				RootPanel.get().add(myWidget);
				RootPanel.get().add(new VehiculeContentLayout().getHeader());
			}
		});
		
		return pageVehicules;
	}
	
	private Button generateBtnCovoitureurs(final SimplePanel myWidget) {
		com.google.gwt.user.client.ui.Button pageCovoitureurs = new Button();
		pageCovoitureurs.setText("Covoitureurs");
		
		pageCovoitureurs.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				RootPanel.get().clear();
				RootPanel.get().add(myWidget);
				RootPanel.get().add(new ConductContentLayout().getHeader());
			}
		});
		
		return pageCovoitureurs;
	}
	
	
	
}
