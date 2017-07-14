import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.entities.*;
import com.uwetrottmann.tmdb2.services.SearchService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import retrofit2.Call;
import retrofit2.Callback;

import static org.mockito.Mockito.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SearchServiceTest {

    private final String API_KEY = "b041e3dc3beef2b03b96c835b6b052ff";
    private final String COMPANY_NAME = "Disney Pixar";

    Tmdb tmdb = new Tmdb(API_KEY);
    SearchService service = tmdb.searchService();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void Test_Callback(){
        Tmdb tmdb = mock(Tmdb.class);
        when(tmdb.searchService()).thenAnswer(i ->{
            Callback callback = i.getArgument(0);
            callback.notify();
            return null;
        });
    }

    @Test
    public void Should_Find_Company() throws IOException {
        // Create call for our company
        Call<CompanyResultsPage> call = service.company(COMPANY_NAME, 1);
        // Get all company results
        CompanyResultsPage companyResult = call.execute().body();
        // Search for our company in the results
        for(Company company : companyResult.results){
            assertEquals(company.name, COMPANY_NAME, COMPANY_NAME);
        }
    }

    @Test
    public void Test_Search_Company() throws IOException {
        // Mock SearchService
        SearchService searchService = mock(SearchService.class);
        // Mock Call<CompanyResultsPage>
        Call<CompanyResultsPage> call = mock(Call.class);
        // define return value for method company()
        when(searchService.company(COMPANY_NAME,1)).thenReturn(call);
        //assert that the call is the same for our Company
        assertEquals(searchService.company(COMPANY_NAME,1), call);
    }

    @Test
    public void Should_ThrowNullPointerException() throws IOException {
        // Create call for our company with a huge amount
        Call<CompanyResultsPage> call = service.company(COMPANY_NAME, 999999);
        // Get all company results
        CompanyResultsPage companyResult = call.execute().body();
        thrown.expect(NullPointerException.class);
        String firstCompanyName = companyResult.results.get(0).name;
    }

}
