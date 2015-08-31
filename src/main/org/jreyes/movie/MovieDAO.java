package jreyes.movie;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;
import java.util.List;
import java.util.UUID;

public class MovieDAO {
    public List<Movie> findAll(){
        Session session = null;
        Result<Movie> movies = null;
        try{
            session = CassandraConnector.connect();
            MappingManager manager = new MappingManager(session);
            MovieAccessor movieAccessor = manager.createAccessor(MovieAccessor.class);
            movies = movieAccessor.getAll();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return movies.all();
    }

    public List<Movie> findByTitle(String title){
        Session session = null;
        Result<Movie> movies = null;
        try{
            session = CassandraConnector.connect();
            MappingManager manager = new MappingManager(session);
            MovieAccessor movieAccessor = manager.createAccessor(MovieAccessor.class);
            movies = movieAccessor.findByTitle(title);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return movies.all();
    }

    public List<Movie> findByGenre(String genre){
        Session session = null;
        Result<Movie> movies = null;
        try{
            session = CassandraConnector.connect();
            MappingManager manager = new MappingManager(session);
            MovieAccessor movieAccessor = manager.createAccessor(MovieAccessor.class);
            movies = movieAccessor.findByGenre(genre);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return movies.all();
    }

    public Movie findById(UUID id){
        Session session = null;
        Movie movies = null;
        try{
            session = CassandraConnector.connect();
            MappingManager manager = new MappingManager(session);
            MovieAccessor movieAccessor = manager.createAccessor(MovieAccessor.class);
            movies = movieAccessor.findById(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return movies;
    }

    public Movie save(Movie movie){
        return movie.getId() == null ? update(movie) : create(movie);
    }

    public Movie create(Movie movie){
        System.out.println("Creating movie...");
        Session session = null;
        UUID id = UUID.randomUUID();
        try{
            session = CassandraConnector.connect();
            MappingManager manager = new MappingManager(session);
            MovieAccessor movieAccessor = manager.createAccessor(MovieAccessor.class);
            movieAccessor.create(
                    id,
                    movie.getTitle(),
                    movie.getYear(),
                    movie.getPicture(),
                    movie.getDescription(),
                    movie.getGenres());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        movie.setId(id);
        System.out.println("Movie created: " + movie.getId());
        return movie;
    }

    public Movie update(Movie movie){
        System.out.println("Updating movie...");
        Session session = null;
        try{
            session = CassandraConnector.connect();
            MappingManager manager = new MappingManager(session);
            MovieAccessor movieAccessor = manager.createAccessor(MovieAccessor.class);
            movie = movieAccessor.update(
                    movie.getId(),
                    movie.getTitle(),
                    movie.getYear(),
                    movie.getPicture(),
                    movie.getDescription(),
                    movie.getGenres());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println("Movie updated");
        return movie;
    }

    public boolean remove(UUID id){
        Session session = null;
        ResultSet rs = null;
        try{
            session = CassandraConnector.connect();
            MappingManager manager = new MappingManager(session);
            MovieAccessor movieAccessor = manager.createAccessor(MovieAccessor.class);
            rs = movieAccessor.delete(id);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return rs.wasApplied();
    }
}