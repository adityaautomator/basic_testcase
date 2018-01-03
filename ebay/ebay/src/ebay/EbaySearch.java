package ebay;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.testng.annotations.BeforeTest;

public class EbaySearch {

	@BeforeTest
	public void File_prop() {

		File path = new File("Properties.properties ");
		FileInputStream fileInput = null;

		try {
			fileInput = new FileInputStream(path);
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();

		}

	}
	
	@Test
	
	public  void browser_initiate()
	{
		
		WebDriver driver =new ChromeDriver();
				
				
	}
	
}
