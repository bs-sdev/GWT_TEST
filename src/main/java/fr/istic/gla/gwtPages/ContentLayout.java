package fr.istic.gla.gwtPages;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;





/**
 * Classe qui permet de créer des contenus de pages avec un Panel
 * 
 * 
 * @author benjamin
 *
 */
public abstract class ContentLayout {
	
	private SimplePanel header;
	
	private SimplePanel body;
	
	private SimplePanel footer;
	
	
	private Label lbl;
	
	private TextBox txt;
	

	/**
	 * @param panel
	 */
	public ContentLayout() {
		super();
		this.header = new SimplePanel();
		this.body = new SimplePanel();
		this.footer = new SimplePanel();
		this.lbl = new Label();
		this.txt = new TextBox();		
	}

	/**
	 * @return the header
	 */
	public SimplePanel getHeader() {
		return this.header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(Widget widget) {
		this.header.clear();
		this.header = (SimplePanel) widget;
		RootPanel.get().add(this.header);
	}

	/**
	 * @return the body
	 */
	public SimplePanel getBody() {
		return this.body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(Widget widget) {
		this.body.clear();
		this.body = (SimplePanel) widget;
		RootPanel.get().add(this.body);
	}

	/**
	 * @return the footer
	 */
	public SimplePanel getFooter() {
		return this.footer;
	}

	/**
	 * @param footer the footer to set
	 */
	public void setFooter(Widget widget) {
		this.footer.clear();
		this.footer = (SimplePanel) widget;
	}

	public void setBody() {
		// TODO Auto-generated method stub
		
	}
	
	public void setHeader() {
		// TODO Auto-generated method stub
		
	}
	

	public Label getLbl() {
		return lbl;
	}

	public void setLbl(Label lbl) {
		this.lbl = lbl; 
	}

	public TextBox getTxt() {
		return txt;
	}

	public void setTxt(TextBox txt) {
		this.txt = txt;
	}
}
