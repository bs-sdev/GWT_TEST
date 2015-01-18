package fr.istic.gla.gwtPages;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;



public class HttpRequestExecutor implements EntryPoint {
	
	public void onModuleLoad() {
		
		MainMenu myMenu = new MainMenu();
		
		RootPanel.get().add(myMenu.getMenu());		

		// appel à une fonction qui modifiera mon Panel
		
		// Créer un fichier Java qui servira de page GWT => elle va contenir 
		// un header, un footer et un contenu (absolutePanel)
		// Il faut que le contenu soit un singleton.
		
		
		/*public class Singleton {
		    public static Singleton getInstance() {
		        if (null == instance) { // Premier appel
		            instance = new Singleton();
		        }
		        return instance;
		    }
		
		    private Singleton() {}
		
		
		    private static Singleton instance; => ici mettre un simple Panel
		}*/
		
		// Penser à la classe Widget dans le setBody (Objet GWT !!!!)
		
		
		// panel que l'on ajoute au rootPanel
		
		// Faire une classe ContentLayout (qui sera un Panel en fait, mais faire gaffe
		// à ne pas faire d'extends, mais faire une variable statique qui sera un simplePanel
		// afin d'éviter d'implémenter toutes les fonctions de merde inhérente au Panel de 
		// GWT.
		// Dans cette classe il y aura un header et un footer en plus. Pendant l'appel
		// a un setter d'un des 3 éléments, penser à faire, DEDANS, un clear avant un add !!!! et
		// (le setter aura en paramètre un Widget)
		
		
		// classe abstraite page => ça permettra de remplir le body		
		// Bien penser à faire cette classe abstraite
		// Possède un Panel en protected, et les fonctions getPanelWidget (retourne le .asWidget ) => affichage
		// possède une 2e fonction getPanel qui permettra d'ajouter les champs private de chaque classe concrete (attribut)
		//
		
		// Passer en paramètre le callBack dans les fonctions permettant de récupérer le résultat d'une requête
		// Plus de boutons avec les requetes dedans direct. L'idée est, sur clique d'un bouton du menu, charger la 
		// page du type d'objet associé et, par défaut, je charge la liste d'éléments. Et dedans, refaire un bouton permettant
		// de faire un get de l'élément par son id.
		
	}
}
