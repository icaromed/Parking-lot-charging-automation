package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        String LOGIN = "LOGIN";
        String SENHA = "SENHA";

        WebDriver driver = new FirefoxDriver();
        driver.get("https://portal.valeti.com/");
        driver.getTitle();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // logs into the Valeti system
        List<WebElement> inputs = driver.findElements(By.tagName("input"));

        WebElement loginTextBox = inputs.get(0);
        WebElement senhaTextBox = inputs.get(1);

        WebElement submitButton = driver.findElement(By.cssSelector(".button.button-md.button-default.button-default-md"));

        senhaTextBox.click();

        loginTextBox.sendKeys(LOGIN);
        senhaTextBox.sendKeys(SENHA);

        submitButton.click();


        // Opens the drop-down menu and get into the mensalists's page

        WebElement navbar = driver.findElement(By.tagName("app-navbar"));
        WebElement button = navbar.findElement(By.cssSelector(".bar-buttons.bar-buttons-md.bar-button.bar-button-md.bar-button-default" +
                ".bar-button-default-md.bar-button-menutoggle.bar-button-menutoggle-md"));

        // explicit wait so the button is clickable
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        button.click();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        driver.quit();

    }
}