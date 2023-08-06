package utlity.realtime;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retrylisteners implements IRetryAnalyzer{
     public int counter=0;
     public int repeatecount=1;
	public boolean retry(ITestResult result) {
	     if(counter<repeatecount) {
	    	 counter++;
	    	 return true;
	     }
		return false;
	}
}
