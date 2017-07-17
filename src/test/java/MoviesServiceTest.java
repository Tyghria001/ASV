import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.entities.Company;
import com.uwetrottmann.tmdb2.entities.CompanyResultsPage;
import com.uwetrottmann.tmdb2.entities.MovieResultsPage;
import com.uwetrottmann.tmdb2.services.MoviesService;
import com.uwetrottmann.tmdb2.services.SearchService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import retrofit2.Call;
import retrofit2.Callback;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MoviesServiceTest {

    private final String API_KEY = "b041e3dc3beef2b03b96c835b6b052ff";

    Tmdb tmdb = new Tmdb(API_KEY);
    MoviesService moviesService = tmdb.moviesService();

    @Test
    public void Test_Popular_Movie() throws IOException {
        // Mock moviesService
        MoviesService moviesService = mock(MoviesService.class);
        // Mock Call<MovieResultsPage>
        Call<MovieResultsPage> call = mock(Call.class);
        // define return value for method popular()
        when(moviesService.popular(1, "Disney")).thenReturn(call);
        //assert that the call is the same for our Movie
        assertEquals(moviesService.popular(1, "Disney"), call);
    }


}
