package pl.dalk.statapp.scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pl.dalk.statapp.dao.entity.League;
import pl.dalk.statapp.dao.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Scanner {
    final static int QUARTERS_AMOUNT = 4;
    final static int WAIT_PERIOD = 3000;
    public static void main(String[] args) throws InterruptedException {
        String chromeDriverPath = "E:/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://dalk.pl/fiba/terminarz.html?&WHurl=%2Fcompetition%2F27313%2Fmatch%2F1554184%2Fplaybyplay%3F");
        Thread.sleep(WAIT_PERIOD);

        for(int i = 0; i < QUARTERS_AMOUNT; i++){
            String pbpLink = "pbp_link" + (i+1);
            System.out.println("SZukando " + pbpLink);
            WebElement quarterButton = driver.findElement(By.className(pbpLink));
            quarterButton.click();
            Thread.sleep(WAIT_PERIOD);
            WebElement playByPlayDiv = driver.findElement(By.id("playbyplay"));
            List<WebElement> incidents = playByPlayDiv.findElements(By.cssSelector("div"));
            int counter = 0;
            for(WebElement incident : incidents){
                counter++;
                String classname = incident.getAttribute("class");
                if(!classname.contains("pbp-team")){
                    continue;
                }
                if(incident.getText().length()<10){
                    continue;
                }
                if(classname.contains("team1")){
                    //System.out.println("Team 1");
                }
                else if(classname.contains("team2")){
                    //System.out.println("Team 2");
                }
                WebElement action = null;
                System.out.println("_____________________________________");
                try{
                    action = incident.findElement(By.className("pbp-action"));
                } catch (NoSuchElementException n){
                    System.err.println(n.getMessage());
                }
                //System.out.println("Akcja - " + action.getText());
                String[] actionLines = action.getText().split(", ");
                try {
                    //System.out.println(actionLines[0]);
                    //System.out.println(actionLines[1]);
                    System.out.println(actionLines[2]);
                } catch(IndexOutOfBoundsException e){
                    System.out.println();
                }
                WebElement period = incident.findElement(By.className("pbp-time"));
                String periodStr = period.getText().split("\\r?\\n")[0];
                //System.out.println(periodStr);
                if(action.getText().contains("Period end")){
                    break;
                }
                System.out.println("_____________________________________");
            }
        }

        System.out.println("XDD");
        driver.close();
        driver.quit();
        League league = new League();
        Player player = new Player();


    }
}

