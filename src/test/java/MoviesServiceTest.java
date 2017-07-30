import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.entities.*;
import com.uwetrottmann.tmdb2.services.MoviesService;
import com.uwetrottmann.tmdb2.services.SearchService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import retrofit2.Call;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MoviesServiceTest {

    Tmdb tmdb;
    MoviesService moviesService;
    Call<MovieResultsPage> call;

    @Mock
    MoviesService moviesServiceMock;
    @Mock
    Call<MovieResultsPage> callMock;

    @Before
    public void setUp() {
        tmdb = new Tmdb("b041e3dc3beef2b03b96c835b6b052ff");
        moviesService = tmdb.moviesService();
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        tmdb = null;
        moviesService = null;
        call = null;
        moviesServiceMock = null;
        callMock = null;
    }

    @Test
    public void Test_ReturnPopularCall() throws IOException {
        when(moviesServiceMock.popular(1, null)).thenReturn(callMock);
        call = moviesServiceMock.popular(1, null);
        assertEquals(call, callMock);
    }

    @Test
    public void Should_Find_Latest_Movie() throws IOException {
        SearchService searchService = tmdb.searchService();
        Movie latestMovie = moviesService.latest().execute().body();
        call = searchService.movie(latestMovie.title,
                null,
                latestMovie.original_language,
                latestMovie.adult,
                null,
                null,
                null
        );
        List<Movie> results = call.execute().body().results;
        for (Movie foundMovie : results) {  //Check if the latest movie is found
            assertEquals(foundMovie.title, latestMovie.title, latestMovie.title);
        }
    }

}
