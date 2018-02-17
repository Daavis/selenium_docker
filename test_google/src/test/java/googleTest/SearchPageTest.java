package googleTest;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Parallelized.class)
public class SearchPageTest extends GridParallelTestBase {

	public SearchPageTest(String browserName) {
		super(browserName);
	}
	
	@Test
	public void serach() throws MalformedURLException {
		
		System.out.println("Test is started for: "+ browserName);
		driver.get("http://www.google.pl");
		SearchPage searchPage = new SearchPage(driver);
		assertTrue(searchPage.isInitialized());
		searchPage.enterSearch("Dawid");
		searchPage.submit();
		
		assertTrue(searchPage.isInitialized());
		assertTrue(searchPage.confirmationHeader().contains("wikipedia"));
		takeScreenShot();
		System.out.println("Test is finished for: "+ browserName);
	}
		
    @After
    public void quitDriver() {
        driver.quit();
    }
}
