package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        /* -- Setup settings -- */

        // Valeti login
        String LOGIN = "login";
        String SENHA = "senha";

        // Selenium general settings
        WebDriver driver = new FirefoxDriver();
        driver.get("https://portal.valeti.com/");
        driver.getTitle();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        /* -- On the login page, log into the Valeti system -- */
        // find the input boxes and submit button
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        WebElement loginTextBox = inputs.get(0);
        WebElement senhaTextBox = inputs.get(1);
        WebElement submitButton = driver.findElement(By.cssSelector(".button.button-md.button-default.button-default-md"));

        // send the login data and submit
        loginTextBox.sendKeys(LOGIN);
        senhaTextBox.sendKeys(SENHA);
        submitButton.click();

        /* --On the home page, opens the drop-down menu -- */
        // find the menu button
        WebElement navbar = driver.findElement(By.tagName("app-navbar"));
        WebElement buttonMenuBar = navbar.findElement(By.cssSelector(".bar-buttons.bar-buttons-md.bar-button.bar-button-md.bar-button-default" +
                ".bar-button-default-md.bar-button-menutoggle.bar-button-menutoggle-md"));

        // explicit wait so the button is clickable and click
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            buttonMenuBar.click();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        /* --after menu-bar is opened, chooses the right menu option -- */
        // find the menu bar
        WebElement menuBar = driver.findElement(By.className("menu-inner"));
        List<WebElement> menuButtons = menuBar.findElements(By.tagName("button"));

        WebElement monthlyButton = null;

        // iterates faster between buttons
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(100));

        // iterates through the buttons and find the right one
        for(WebElement button : menuButtons){
            try {
                button.findElement(By.cssSelector(".icon.icon-md.ion-ios-person-outline.item-icon"));
                monthlyButton = button;
                break;
            } catch (NoSuchElementException ignored) {
            }
        }

        // use old implicitly wait
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        // try to click the button and go to monthly payer's page
        try {
            monthlyButton.click();
        } catch (NullPointerException e){
            System.out.println("Botao de mensalista nao encontrado");
            System.out.println("Encerrando...");
            driver.quit();
        }

        /* --selects the parking lot -- */

        //...

        /* -- exit -- */
        driver.quit();

    }
}