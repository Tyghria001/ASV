import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.entities.TvResultsPage;
import com.uwetrottmann.tmdb2.entities.TvShow;
import com.uwetrottmann.tmdb2.entities.Videos;
import com.uwetrottmann.tmdb2.services.TvService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import retrofit2.Call;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TvServiceTest {

    Tmdb tmdb;
    TvService tvService;

    @Mock
    List<TvShow> list;
    @Mock
    TvShow tvShow;

    @Before
    public void setUp() {
        tmdb = new Tmdb("b041e3dc3beef2b03b96c835b6b052ff");
        tvService = tmdb.tvService();
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        tmdb = null;
        tvService = null;
        list = null;
        tvShow = null;
    }

    @Test()
    public void Should_ReturnVideos_When_ExecuteRequest() throws IOException {
        Call<Videos> call = tvService.videos(1, null);
        Videos videos = call.execute().body();
        assertNotNull(videos);
    }

    @Test
    public void Test_ReturnTvShow() {
        when(list.get(0)).thenReturn(tvShow);
        TvShow result = list.get(0);
        assertEquals(result, tvShow);
        verify(list).get(0);
    }

}
