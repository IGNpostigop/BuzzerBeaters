package es.uma.informatica.sii.buzzerbeaters.test;



import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
public class BuzzerBeatersIT {
  private static final String UNIDAD_PERSISTENCIA_PRUEBAS = "BuzzerBeatersIT";
private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  
  private static String baseURL;
  private static Map<String, String> propiedadesExtra = new HashMap<>();
  
  @BeforeClass
  public static void setupClass() {
	  String server="localhost";
		try (InputStream is = BuzzerBeatersIT.class.getClassLoader().getResourceAsStream("pom.properties")) {
			Properties pomProperties = new Properties();
			pomProperties.load(is);
			server=pomProperties.getProperty("server.host");
			String databaseURL = "jdbc:mysql://"+server+":3306/sii";
			propiedadesExtra.put("javax.persistence.jdbc.url", databaseURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
		baseURL="http://"+server+":8080/BuzzerBeaters-war";
	  
  }
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
    BaseDatos.inicializaBaseDatos(UNIDAD_PERSISTENCIA_PRUEBAS, propiedadesExtra);
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void inicio() {
    driver.get(baseURL);
    driver.manage().window().setSize(new Dimension(1115, 1380));
    driver.findElement(By.id("saludo")).click();
    driver.findElement(By.id("saludo")).click();
    assertThat(driver.findElement(By.id("saludo")).getText(), is("Hola mundo"));
  }
}