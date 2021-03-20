package runner;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WhenDoSteps {
    private AppiumDriver driver;

    @Given("yo tengo abierta mi aplicacion WhenDo")
    public void yoTengoAbiertaMiAplicacionWhenDo() throws MalformedURLException {
        // configuracion para la conexion
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("deviceName","HUAWEI Y7 2018");
        capabilities.setCapability("platformVersion","8.0.0");
        capabilities.setCapability("appPackage","com.vrproductiveapps.whendo");
        capabilities.setCapability("appActivity",".ui.HomeActivity");
        capabilities.setCapability("platformName","Android");

        // driver apunte a nuestro appiumDesktop
        driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

        // implicit:un tiempo de espera para todos los componentes de UI. el explicit sobreescribe al implicit
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @When("yo selecciono el boton {string}")
    public void yoSeleccionoElBoton(String nombreControl) {
        switch(nombreControl){
            case "add":
                driver.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
                break;
            case "recordatorio":
                driver.findElement(By.id("com.vrproductiveapps.whendo:id/note_item_reminder")).click();
                break;
            case "save":
                driver.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();
                break;
            default:
                break;
        }
    }


    @And("yo selecciono datos para crear el Recordatorio")
    public void yoSeleccionoDatosParaCrearElRecordatorio() {
        //add date
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/due_date")).click();
        driver.findElement(By.id("android:id/button3")).click();
        driver.findElement(By.id("android:id/button1")).click();
        //add time
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/time")).click();
        driver.findElement(By.id("android:id/button1")).click();
        //add reminder
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/reminder")).click();
        driver.findElement(By.xpath("//android.widget.RadioButton[3]")).click();
        //add repeat
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/repeat")).click();
        driver.findElement(By.xpath("//android.widget.RadioButton[3]")).click();
    }

    @And("yo agrego el titulo {string} y la nota {string}")
    public void yoAgregoElTituloYLaNota(String titulo, String nota) {
        //add title
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys(titulo);
        //add Note
        driver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys(nota);
    }


    @Then("el titulo {string} y la nota {string} tienen que mostrarse en mi lista")
    public void elTituloYLaNotaTienenQueMostrarseEnLaMiLista(String titulo, String nota) {
        boolean isDisplayedTitle=driver.findElement(By.xpath("//android.widget.TextView[@text='"+titulo+"']")).isDisplayed();
        Assert.assertTrue("ERROR el titulo no es mostrado en la lista",isDisplayedTitle);

        boolean isDisplayedNote=driver.findElement(By.xpath("//android.widget.TextView[@text='"+nota+"']")).isDisplayed();
        Assert.assertTrue("ERROR la nota no es mostrado en la lista",isDisplayedNote);
    }



}
