package fr.istic.gla.server;

import java.util.Collection;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.istic.gla.shared.Book;
import fr.istic.gla.shared.BookItf;

public interface MyService {

	public abstract Collection<Book> list();

	public abstract BookItf findById(String arg0);

	public abstract BookItf deleteById(String arg0);

}