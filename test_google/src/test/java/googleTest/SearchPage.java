package googleTest;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends PageObject {

	@FindBy(id="lst-ib")
	private WebElement searchInput;
	

	@FindBy(css="input[name=\"btnK\"]")
	private WebElement submitButton;
	
	@FindBy(css="div._NId:nth-child(1)  div.g:nth-child(1) cite")
	private WebElement header;
	
	public SearchPage(WebDriver driver) {
		super(driver);
	}
	
	public boolean isInitialized() {
		return searchInput.isDisplayed();
	}
	
	public void enterSearch(String data){
		this.searchInput.clear();
		this.searchInput.sendKeys(data);
		this.searchInput.sendKeys(Keys.ESCAPE);
	}
	
	public boolean isReturnInitialized() {
		return header.isDisplayed();
	}
	

	public String confirmationHeader(){
		return header.getText();
	}
	
	public void submit(){
		submitButton.click();
	}
}
