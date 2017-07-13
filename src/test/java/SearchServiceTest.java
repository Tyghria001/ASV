import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.entities.Company;
import com.uwetrottmann.tmdb2.entities.CompanyResultsPage;
import com.uwetrottmann.tmdb2.services.SearchService;
import org.junit.Test;
import retrofit2.Call;
import static org.mockito.Mockito.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SearchServiceTest {

    private final String API_KEY = "b041e3dc3beef2b03b96c835b6b052ff";
    private final String COMPANY_NAME = "Disney Pixar";

    Tmdb tmdb = new Tmdb(API_KEY);
    SearchService service = tmdb.searchService();

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

}
