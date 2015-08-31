package jreyes.movie;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

@Path("/movies")
public class MovieResource {

    MovieDAO dao = new MovieDAO();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Movie> findAll() throws UnsupportedEncodingException {
        System.out.println("findAll");
        return dao.findAll();
    }

    @GET @Path("search/{title}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Movie> findByTitle(@PathParam("title") String title){
        System.out.println("findByTitle: "+title);
        return dao.findByTitle(title);
    }
    //curl -i -H "Accept: application/json" http://localhost:8080/movieApp/rest/movies/search/genre/Drama
    @GET @Path("search/genre/{genre}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Movie> findByGenre(@PathParam("genre") String genre){
        System.out.println("findByGenre: "+genre);
        return dao.findByGenre(genre);
    }

    @GET @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Movie findById(@PathParam("id") UUID id) {
        System.out.println("findById " + id);
        return dao.findById(id);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Movie create(Movie movie) {
        return dao.create(movie);
    }

    @PUT @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Movie update(Movie movie) {
        System.out.println("Updating movie: " + movie.getTitle());
        dao.update(movie);
        return movie;
    }

    @DELETE @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void remove(@PathParam("id") UUID id) {
        System.out.println("removing: "+id);
        dao.remove(id);
    }
}
