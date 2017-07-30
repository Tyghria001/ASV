import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.entities.*;
import com.uwetrottmann.tmdb2.services.SearchService;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import retrofit2.Call;
import retrofit2.Callback;

import static org.mockito.Mockito.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SearchServiceTest {

    private final String COMPANY_NAME = "Disney Pixar";

    Tmdb tmdb;
    SearchService searchService;
    Call<CompanyResultsPage> call;

    @Mock
    Tmdb tmdbMock;
    @Mock
    SearchService searchServiceMock;
    @Mock
    Call<CompanyResultsPage> callMock;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        tmdb = new Tmdb("b041e3dc3beef2b03b96c835b6b052ff");
        searchService = tmdb.searchService();
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        tmdb = null;
        searchService = null;
        call = null;
        searchServiceMock = null;
        callMock = null;
    }

    @Test
    public void Test_Callback() {
        when(tmdbMock.searchService()).thenAnswer(i -> {
            Callback callback = i.getArgument(0);
            callback.notify();
            return null;
        });
    }

    @Test
    public void Should_Find_Company() throws IOException {
        call = searchService.company(COMPANY_NAME, 1);
        CompanyResultsPage companyResult = call.execute().body(); // Get all company results
        for (Company company : companyResult.results) { // Search for our company in the results
            assertEquals(company.name, COMPANY_NAME, COMPANY_NAME);
        }
    }

    @Test
    public void Test_Search_Company() throws IOException {
        when(searchServiceMock.company(COMPANY_NAME, 1)).thenReturn(callMock);
        call = searchServiceMock.company(COMPANY_NAME, 1);
        assertEquals(call, callMock);
    }

    @Test
    public void Should_ThrowNullPointerException() throws IOException {
        call = searchService.company(COMPANY_NAME, 999999);
        CompanyResultsPage companyResult = call.execute().body();
        thrown.expect(NullPointerException.class);
        String firstCompanyName = companyResult.results.get(0).name;
    }

}
