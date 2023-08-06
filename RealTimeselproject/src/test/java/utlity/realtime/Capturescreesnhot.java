package utlity.realtime;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Capturescreesnhot {

	
	public void Capturescreesnhot(WebDriver driver2, String name) throws IOException {
		File filename=((TakesScreenshot) driver2).getScreenshotAs(OutputType.FILE);
		String destinationpath=System.getProperty("user.dir")+"\\Screenshot\\"+name+getdate()+".png";
		FileUtils.copyFile(filename, new File(destinationpath));
		}
	public String getdate() {
		Date d=new Date();
		
		String date=d.toString().replace(" ", "-").replace(":","-");
		return date;
	}
	}


