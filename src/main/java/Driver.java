import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.entities.*;
import com.uwetrottmann.tmdb2.services.MoviesService;
import com.uwetrottmann.tmdb2.services.SearchService;
import retrofit2.Call;

import java.io.IOException;

public class Driver {

    private static final String API_KEY = "b041e3dc3beef2b03b96c835b6b052ff";

    public static void main(String[] args) throws IOException {

        Tmdb tmdb = new Tmdb(API_KEY);
        MoviesService movieService = tmdb.moviesService();

        Call<MovieResultsPage> call = movieService.popular(1,"Disney");
        MovieResultsPage results = call.execute().body();
        for(Movie movie : results.results){
            System.out.println(movie.title);
        }


    }
}
