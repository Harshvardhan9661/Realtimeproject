package basepackage;

import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;

public class Baseclass {
	public static Properties prod;
	@BeforeClass
	public void setup1() throws IOException {
		prod=new Properties();
		FileInputStream   fi=new FileInputStream("./RealTime.properties");
		prod.load(fi);
		}}
