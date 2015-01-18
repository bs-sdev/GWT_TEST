package fr.istic.gla.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.istic.gla.shared.Book;
import fr.istic.gla.shared.BookItf;

/*
 * This class is an example of services class
 */

@Path("/books")
public class BookResource implements MyService {

	private List<Book> books = new ArrayList<Book>();

	EntityManager manager;

	public BookResource() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("dev");
		manager = factory.createEntityManager();
		EntityTransaction t = manager.getTransaction();
		t.begin();
		for (int i = 0; i < 20; i++) {
            manager.persist(new Book( "Title " + i, "Author " + i, new Double(Math.random()*20).intValue()));
        }
		t.commit();
		

	}

	/* (non-Javadoc)
	 * @see fr.istic.gla.server.MyService#list()
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Book> list() {
		return manager.createQuery("select e from Book as e").getResultList();
	}

	/* (non-Javadoc)
	 * @see fr.istic.gla.server.MyService#findById(java.lang.String)
	 */
	@GET
	@Path("search/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public BookItf findById(@PathParam("id") String arg0) {
		return manager.find(Book.class, Integer.parseInt(arg0));
	}

	/* (non-Javadoc)
	 * @see fr.istic.gla.server.MyService#deleteById(java.lang.String)
	 */
	@DELETE
	@Path("delete/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public BookItf deleteById(@PathParam("id") String arg0) {
		EntityTransaction t = manager.getTransaction();
		t.begin();
		Book b = manager.find(Book.class, Integer.parseInt(arg0));
		manager.remove(b);
		t.commit();
		return b;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}

}
