import com.uwetrottmann.tmdb2.Tmdb;
import com.uwetrottmann.tmdb2.entities.Company;
import com.uwetrottmann.tmdb2.entities.CompanyResultsPage;
import com.uwetrottmann.tmdb2.services.SearchService;
import org.junit.Test;
import retrofit2.Call;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SearchServiceTest {

    private final String API_KEY = "b041e3dc3beef2b03b96c835b6b052ff";
    private final String COMPANY_NAME = "Disney Pixar";

    Tmdb tmdb = new Tmdb(API_KEY);
    SearchService searchService = tmdb.searchService();

    @Test
    public void Should_Find_Company() throws IOException {
        Call<CompanyResultsPage> call = searchService.company(COMPANY_NAME, 1);
        CompanyResultsPage companyResult = call.execute().body();
        for(Company company : companyResult.results){
            assertEquals(company.name, COMPANY_NAME, COMPANY_NAME);
        }
    }

}
