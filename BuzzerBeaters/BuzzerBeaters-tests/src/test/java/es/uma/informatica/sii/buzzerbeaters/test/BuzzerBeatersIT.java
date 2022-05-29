package es.uma.informatica.sii.buzzerbeaters.test;
// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
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
import java.net.MalformedURLException;
import java.net.URL;
public class BuzzerBeatersIT {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void inicioAdmin() {
    driver.get("http://localhost:8080/BuzzerBeaters-war/");
    driver.manage().window().setSize(new Dimension(876, 1075));
    driver.findElement(By.linkText("Login para administradores")).click();
    driver.findElement(By.id("adminLogin:user")).click();
    driver.findElement(By.id("adminLogin:user")).sendKeys("Fulanito");
    driver.findElement(By.id("adminLogin:pass")).sendKeys("pass");
    driver.findElement(By.id("adminLogin:botonLogin")).click();
    driver.findElement(By.cssSelector("h3")).click();
    assertThat(driver.findElement(By.id("mensBienv")).getText(), is("Bienvenido Fulanito"));
  }
  @Test
  public void loginnormal() {
    driver.get("http://localhost:8080/BuzzerBeaters-war/");
    driver.manage().window().setSize(new Dimension(876, 1040));
    driver.findElement(By.id("login:user")).click();
    driver.findElement(By.id("login:user")).sendKeys("Menganito");
    driver.findElement(By.id("login:pass")).sendKeys("pass");
    driver.findElement(By.id("login:botonLogin")).click();
    driver.findElement(By.cssSelector("html")).click();
    assertThat(driver.findElement(By.id("mensBienv")).getText(), is("Bienvenido a la Página principal de clientes"));
  }
  @Test
  public void altaClienteIndividual() {
    driver.get("http://localhost:8080/BuzzerBeaters-war/");
    driver.manage().window().setSize(new Dimension(876, 1040));
    driver.findElement(By.linkText("Login para administradores")).click();
    driver.findElement(By.id("adminLogin:user")).click();
    driver.findElement(By.id("adminLogin:user")).sendKeys("Fulanito");
    driver.findElement(By.id("adminLogin:pass")).sendKeys("pass");
    driver.findElement(By.id("adminLogin:botonLogin")).click();
    driver.findElement(By.id("botonAltaIndividual")).click();
    driver.findElement(By.id("altaCIndividual:username")).click();
    driver.findElement(By.id("altaCIndividual:username")).sendKeys("uno");
    driver.findElement(By.id("altaCIndividual:pass")).sendKeys("pass");
    driver.findElement(By.id("altaCIndividual:nombre")).sendKeys("unomismo");
    driver.findElement(By.id("altaCIndividual:apellido")).sendKeys("apellido");
    driver.findElement(By.id("altaCIndividual:fechaNacimiento")).sendKeys("1800-02-03");
    driver.findElement(By.id("altaCIndividual:identificacion")).sendKeys("11111p");
    driver.findElement(By.id("altaCIndividual:direccion")).sendKeys("calle pito");
    driver.findElement(By.id("altaCIndividual:ciudad")).sendKeys("Malaga");
    driver.findElement(By.id("altaCIndividual:pais")).sendKeys("España");
    driver.findElement(By.id("altaCIndividual:cp")).sendKeys("29000");
    driver.findElement(By.id("altaCIndividual:botonAltaCIndiv")).click();
    driver.findElement(By.id("altaCIndividual")).click();
    assertThat(driver.findElement(By.id("altaCIndividual:boton")).getText(), is("El cliente se ha dado de alta correctamente"));
  }
  @Test
  public void altaEmpresa() {
    driver.get("http://localhost:8080/BuzzerBeaters-war/");
    driver.manage().window().setSize(new Dimension(876, 1040));
    driver.findElement(By.linkText("Login para administradores")).click();
    driver.findElement(By.id("adminLogin:user")).click();
    driver.findElement(By.id("adminLogin:user")).sendKeys("Fulanito");
    driver.findElement(By.id("adminLogin:pass")).sendKeys("pass");
    driver.findElement(By.id("adminLogin:botonLogin")).click();
    driver.findElement(By.id("botonAltaEmpresa")).click();
    driver.findElement(By.id("altaCEmpresa:razonSocial")).click();
    driver.findElement(By.id("altaCEmpresa:razonSocial")).sendKeys("empre");
    driver.findElement(By.id("altaCEmpresa:identificacion")).click();
    driver.findElement(By.id("altaCEmpresa:identificacion")).sendKeys("b290909090");
    driver.findElement(By.id("altaCEmpresa:direccion")).click();
    driver.findElement(By.id("altaCEmpresa:direccion")).sendKeys("calle industria");
    driver.findElement(By.id("altaCEmpresa:ciudad")).sendKeys("Malaga");
    driver.findElement(By.id("altaCEmpresa:pais")).sendKeys("España");
    driver.findElement(By.id("altaCEmpresa:cp")).sendKeys("29018");
    driver.findElement(By.id("altaCEmpresa:botonAltaCEmpresa")).click();
    driver.findElement(By.id("altaCEmpresa")).click();
    assertThat(driver.findElement(By.id("altaCEmpresa:botonEmpresa")).getText(), is("Empresa creada correctamente"));
  }
  @Test
  public void altaPA() {
    driver.get("http://localhost:8080/BuzzerBeaters-war/");
    driver.manage().window().setSize(new Dimension(876, 1040));
    driver.findElement(By.linkText("Login para administradores")).click();
    driver.findElement(By.id("adminLogin:user")).click();
    driver.findElement(By.id("adminLogin:user")).sendKeys("Fulanito");
    driver.findElement(By.id("adminLogin:pass")).sendKeys("pass");
    driver.findElement(By.id("adminLogin:botonLogin")).click();
    driver.findElement(By.id("botonAltaPersAut")).click();
    driver.findElement(By.id("altaPersonaAutorizada:username")).click();
    driver.findElement(By.id("altaPersonaAutorizada:username")).sendKeys("Lolo");
    driver.findElement(By.id("altaPersonaAutorizada:pass")).click();
    driver.findElement(By.id("altaPersonaAutorizada:pass")).sendKeys("pass");
    driver.findElement(By.id("altaPersonaAutorizada:nombre")).sendKeys("Lolo");
    driver.findElement(By.id("altaPersonaAutorizada:apellido")).sendKeys("Loloa");
    driver.findElement(By.id("altaPersonaAutorizada:identificacion")).sendKeys("22222p");
    driver.findElement(By.id("altaPersonaAutorizada:direccion")).sendKeys("grillo");
    driver.findElement(By.id("altaPersonaAutorizada:fn")).sendKeys("2000-06-23");
    driver.findElement(By.id("altaPersonaAutorizada:botonPA")).click();
    driver.findElement(By.id("altaPersonaAutorizada")).click();
    assertThat(driver.findElement(By.id("altaPersonaAutorizada:boton")).getText(), is("Alta correccta"));
  }
  @Test
  public void anyadirAutorizacion() {
    driver.get("http://localhost:8080/BuzzerBeaters-war/");
    driver.manage().window().setSize(new Dimension(876, 1040));
    driver.findElement(By.linkText("Login para administradores")).click();
    driver.findElement(By.id("adminLogin:user")).click();
    driver.findElement(By.id("adminLogin:user")).sendKeys("Fulanito");
    driver.findElement(By.id("adminLogin:pass")).click();
    driver.findElement(By.id("adminLogin:pass")).sendKeys("pass");
    driver.findElement(By.id("adminLogin:botonLogin")).click();
    driver.findElement(By.id("botonAnyadirAut")).click();
    driver.findElement(By.id("anyadirAutorizacion:idPersAut")).click();
    driver.findElement(By.id("anyadirAutorizacion:idPersAut")).sendKeys("4");
    driver.findElement(By.id("anyadirAutorizacion:idEmpresa")).click();
    driver.findElement(By.id("anyadirAutorizacion:idEmpresa")).sendKeys("2");
    driver.findElement(By.id("anyadirAutorizacion:tipoAutorizacion")).click();
    driver.findElement(By.id("anyadirAutorizacion:tipoAutorizacion")).sendKeys("a");
    driver.findElement(By.id("anyadirAutorizacion:botonanyadirAutorizacion")).click();
    driver.findElement(By.id("anyadirAutorizacion")).click();
    assertThat(driver.findElement(By.id("anyadirAutorizacion:botonAut")).getText(), is("Alta Correcta"));
  }
  @Test
  public void abreCuentaSegregada() {
    driver.get("http://localhost:8080/BuzzerBeaters-war/");
    driver.manage().window().setSize(new Dimension(876, 1040));
    driver.findElement(By.linkText("Login para administradores")).click();
    driver.findElement(By.id("adminLogin:user")).click();
    driver.findElement(By.id("adminLogin:user")).sendKeys("Fulanito");
    driver.findElement(By.id("adminLogin:pass")).sendKeys("pass");
    driver.findElement(By.id("adminLogin:botonLogin")).click();
    driver.findElement(By.id("abreSegregada")).click();
    driver.findElement(By.id("abreSegregada:clienteid")).click();
    driver.findElement(By.id("abreSegregada:clienteid")).sendKeys("1");
    driver.findElement(By.id("abreSegregada:cliente")).click();
    driver.findElement(By.id("abreSegregada:cliente")).sendKeys("11111111F");
    driver.findElement(By.id("abreSegregada:iban")).click();
    driver.findElement(By.id("abreSegregada:iban")).sendKeys("es2021030128310040002352");
    driver.findElement(By.id("abreSegregada:swift")).click();
    driver.findElement(By.id("abreSegregada:swift")).sendKeys("unicajaes");
    driver.findElement(By.id("abreSegregada:comision")).click();
    driver.findElement(By.id("abreSegregada:comision")).sendKeys("12");
    driver.findElement(By.id("abreSegregada:ibanReferencia")).click();
    driver.findElement(By.id("abreSegregada:ibanReferencia")).sendKeys("iban1234");
    driver.findElement(By.id("abreSegregada:boton")).click();
    driver.findElement(By.id("abreSegregada:usuario")).click();
    assertThat(driver.findElement(By.id("abreSegregada:usuario")).getText(), is("Cuenta segregada abierta correctamente."));
  }
  
  @Test
  public void abreCuentaPooled() {
    driver.get("http://localhost:8080/BuzzerBeaters-war/");
    driver.manage().window().setSize(new Dimension(876, 1040));
    driver.findElement(By.linkText("Login para administradores")).click();
    driver.findElement(By.id("adminLogin:user")).click();
    driver.findElement(By.id("adminLogin:user")).sendKeys("Fulanito");
    driver.findElement(By.id("adminLogin:pass")).click();
    driver.findElement(By.id("adminLogin:pass")).sendKeys("pass");
    driver.findElement(By.id("adminLogin:botonLogin")).click();
    driver.findElement(By.id("abrePooled")).click();
    driver.findElement(By.id("abrirCuentaPooled:idcliente")).click();
    driver.findElement(By.id("abrirCuentaPooled:idcliente")).sendKeys("1");
    driver.findElement(By.id("abrirCuentaPooled:cliente")).sendKeys("11111111F");
    driver.findElement(By.id("abrirCuentaPooled:iban")).sendKeys("iban1234");
    driver.findElement(By.id("abrirCuentaPooled:swift")).sendKeys("este");
    driver.findElement(By.id("abrirCuentaPooled:comision")).sendKeys("12");
    driver.findElement(By.id("abrirCuentaPooled:boton")).click();
    driver.findElement(By.id("abrirCuentaPooled:usuario")).click();
    assertThat(driver.findElement(By.id("abrirCuentaPooled:usuario")).getText(), is("Cuenta pooled abierta correctamente."));
  }
  @Test
  public void quitarPersonaAutorizada() {
    driver.get("http://localhost:8080/BuzzerBeaters-war/");
    driver.manage().window().setSize(new Dimension(876, 1040));
    driver.findElement(By.linkText("Login para administradores")).click();
    driver.findElement(By.id("adminLogin:user")).click();
    driver.findElement(By.id("adminLogin:user")).sendKeys("Fulanito");
    driver.findElement(By.id("adminLogin:pass")).click();
    driver.findElement(By.id("adminLogin:pass")).sendKeys("pass");
    driver.findElement(By.id("adminLogin:botonLogin")).click();
    driver.findElement(By.id("botonQuitarAut")).click();
    driver.findElement(By.id("eliminarAutorizados:idPersAut")).click();
    driver.findElement(By.id("eliminarAutorizados:idPersAut")).sendKeys("5");
    driver.findElement(By.id("eliminarAutorizados:botoneliminarAutorizacion")).click();
    driver.findElement(By.id("eliminarAutorizados")).click();
    assertThat(driver.findElement(By.id("eliminarAutorizados:botonMessage")).getText(), is("Baja Correcta"));
  }
  @Test
  public void darDeBajaCliente() {
    driver.get("http://localhost:8080/BuzzerBeaters-war/");
    driver.manage().window().setSize(new Dimension(876, 1040));
    driver.findElement(By.linkText("Login para administradores")).click();
    driver.findElement(By.id("adminLogin:user")).click();
    driver.findElement(By.id("adminLogin:user")).sendKeys("Fulanito");
    driver.findElement(By.id("adminLogin:pass")).click();
    driver.findElement(By.id("adminLogin:pass")).sendKeys("pass");
    driver.findElement(By.id("adminLogin:botonLogin")).click();
    driver.findElement(By.id("bajaCliente")).click();
    driver.findElement(By.id("bajaCliente:id")).click();
    driver.findElement(By.id("bajaCliente:id")).sendKeys("2");
    driver.findElement(By.id("bajaCliente:boton")).click();
    driver.findElement(By.id("bajaCliente")).click();
    assertThat(driver.findElement(By.id("bajaCliente:botonMessage")).getText(), is("Cliente correctamente dado de baja"));
  }
  @Test
  public void cerrarPooledAccount() {
    driver.get("http://localhost:8080/BuzzerBeaters-war/");
    driver.manage().window().setSize(new Dimension(876, 1040));
    driver.findElement(By.linkText("Login para administradores")).click();
    driver.findElement(By.id("adminLogin:user")).click();
    driver.findElement(By.id("adminLogin:user")).sendKeys("Fulanito");
    driver.findElement(By.id("adminLogin:pass")).click();
    driver.findElement(By.id("adminLogin:pass")).sendKeys("pass");
    driver.findElement(By.id("adminLogin:botonLogin")).click();
    driver.findElement(By.id("borrarPooled")).click();
    driver.findElement(By.id("cerrarPooled:iban")).click();
    driver.findElement(By.id("cerrarPooled:iban")).sendKeys("iban");
    driver.findElement(By.id("cerrarPooled:botonCerrar")).click();
    driver.findElement(By.id("cerrarPooled")).click();
    assertThat(driver.findElement(By.id("cerrarPooled:usuario")).getText(), is("Cuenta pooled correctamente dada de baja"));
  }
  @Test
  public void modificarIndividual() {
    driver.get("http://localhost:8080/BuzzerBeaters-war/");
    driver.manage().window().setSize(new Dimension(876, 1040));
    driver.findElement(By.linkText("Login para administradores")).click();
    driver.findElement(By.id("adminLogin:user")).click();
    driver.findElement(By.id("adminLogin:user")).sendKeys("Fulanito");
    driver.findElement(By.id("adminLogin:pass")).click();
    driver.findElement(By.id("adminLogin:pass")).sendKeys("pass");
    driver.findElement(By.id("adminLogin:botonLogin")).click();
    driver.findElement(By.id("modificarCIndividual")).click();
    driver.findElement(By.id("modificarCIndividual:id")).click();
    driver.findElement(By.id("modificarCIndividual:id")).sendKeys("1");
    driver.findElement(By.id("modificarCIndividual:nombreInd")).sendKeys("Mengano");
    driver.findElement(By.id("modificarCIndividual:fechaNacimientoid")).sendKeys("2006-10-22");
    driver.findElement(By.id("modificarCIndividual:apellidoInd")).sendKeys("Zutano");
    driver.findElement(By.id("modificarCIndividual:identificacionInd")).sendKeys("111187645t");
    driver.findElement(By.id("modificarCIndividual:direccionInd")).sendKeys("la que sea");
    driver.findElement(By.id("modificarCIndividual:ciudadInd")).sendKeys("Malaga");
    driver.findElement(By.id("modificarCIndividual:cpInd")).sendKeys("29019");
    driver.findElement(By.id("modificarCIndividual:paisInd")).sendKeys("España");
    driver.findElement(By.id("modificarCIndividual:botonInd")).click();
    driver.findElement(By.id("modificarCIndividual:usuario")).click();
    assertThat(driver.findElement(By.id("modificarCIndividual:usuario")).getText(), is("Cliente individual modificado."));
  }
  @Test
  public void modificarPA() {
    driver.get("http://localhost:8080/BuzzerBeaters-war/");
    driver.manage().window().setSize(new Dimension(876, 1040));
    driver.findElement(By.linkText("Login para administradores")).click();
    driver.findElement(By.id("adminLogin:user")).click();
    driver.findElement(By.id("adminLogin:user")).sendKeys("Fulanito");
    driver.findElement(By.id("adminLogin:pass")).click();
    driver.findElement(By.id("adminLogin:pass")).sendKeys("pass");
    driver.findElement(By.cssSelector("tr:nth-child(3) > td:nth-child(1)")).click();
    driver.findElement(By.id("adminLogin:botonLogin")).click();
    driver.findElement(By.id("modificarPA")).click();
    driver.findElement(By.id("modificarPA:id")).click();
    driver.findElement(By.id("modificarPA:id")).sendKeys("4");
    driver.findElement(By.id("modificarPA:nombre")).click();
    driver.findElement(By.id("modificarPA:nombre")).click();
    driver.findElement(By.id("modificarPA:nombre")).click();
    driver.findElement(By.id("modificarPA:nombre")).click();
    driver.findElement(By.id("modificarPA:nombre")).sendKeys("carlos");
    driver.findElement(By.id("modificarPA:apellido")).sendKeys("carlos");
    driver.findElement(By.id("modificarPA:identificacion")).sendKeys("33344t");
    driver.findElement(By.id("modificarPA:direccion")).sendKeys("esta misma");
    driver.findElement(By.id("modificarPA:fn")).sendKeys("1976-02-19");
    driver.findElement(By.id("modificarPA:botonMODPA")).click();
    driver.findElement(By.id("modificarPA:boton")).click();
    assertThat(driver.findElement(By.id("modificarPA:boton")).getText(), is("Persona autorizada modificada."));
  }
  @Test
  public void modificarEmpresa() {
    driver.get("http://localhost:8080/BuzzerBeaters-war/");
    driver.manage().window().setSize(new Dimension(876, 1040));
    driver.findElement(By.linkText("Login para administradores")).click();
    driver.findElement(By.id("adminLogin:user")).click();
    driver.findElement(By.id("adminLogin:user")).sendKeys("Fulanito");
    driver.findElement(By.id("adminLogin:pass")).click();
    driver.findElement(By.id("adminLogin:pass")).sendKeys("pass");
    driver.findElement(By.id("adminLogin:botonLogin")).click();
    driver.findElement(By.id("modificarCEmpresa")).click();
    driver.findElement(By.id("modificarCEmpresa:id")).click();
    driver.findElement(By.id("modificarCEmpresa:id")).sendKeys("2");
    driver.findElement(By.id("modificarCEmpresa:rzemp")).click();
    driver.findElement(By.id("modificarCEmpresa:rzemp")).sendKeys("empreSA");
    driver.findElement(By.id("modificarCEmpresa:identificacionEmp")).click();
    driver.findElement(By.id("modificarCEmpresa:identificacionEmp")).sendKeys("4563738j");
    driver.findElement(By.id("modificarCEmpresa:direccionEmp")).sendKeys("industrial");
    driver.findElement(By.id("modificarCEmpresa:ciudadEmp")).sendKeys("Jaen");
    driver.findElement(By.id("modificarCEmpresa:cpEmp")).sendKeys("14000");
    driver.findElement(By.id("modificarCEmpresa:paisEmp")).sendKeys("España");
    driver.findElement(By.id("modificarCEmpresa:botonEmp")).click();
    driver.findElement(By.id("modificarCEmpresa")).click();
    assertThat(driver.findElement(By.id("modificarCEmpresa:usuario")).getText(), is("Empresa modificada."));
  }
  @Test
  public void cerrarSegregada() {
    driver.get("http://localhost:8080/BuzzerBeaters-war/");
    driver.manage().window().setSize(new Dimension(876, 1040));
    driver.findElement(By.id("login")).click();
    driver.findElement(By.linkText("Login para administradores")).click();
    driver.findElement(By.id("adminLogin:user")).click();
    driver.findElement(By.id("adminLogin:user")).sendKeys("Fulanito");
    driver.findElement(By.id("adminLogin:pass")).click();
    driver.findElement(By.id("adminLogin:pass")).sendKeys("pass");
    driver.findElement(By.id("adminLogin:botonLogin")).click();
    driver.findElement(By.id("cerrarSegregada")).click();
    driver.findElement(By.id("cerrarSegregada:iban")).click();
    driver.findElement(By.id("cerrarSegregada:iban")).sendKeys("segiban");
    driver.findElement(By.id("cerrarSegregada:botonCerrar")).click();
    driver.findElement(By.id("cerrarSegregada")).click();
    assertThat(driver.findElement(By.id("cerrarSegregada:usuario")).getText(), is("Cuenta segregada correctamente dada de baja."));
  }
  
}
