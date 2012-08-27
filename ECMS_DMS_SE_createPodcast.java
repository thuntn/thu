package ecms.functionnal.dms.siteexplorer.createnode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class ECMS_DMS_SE_createPodcast extends EcmsBase {
//add a podcast document in an article document  
@Test(groups={"general","article"})
  public void addPodcastDocumentinArticledocument() {
	//choose a drive
	waitForAndGetElementByXpath("//a[@title='Show Drives']").click();
	waitForAndGetElementByXpath("//a[@title='Sites Management']").click();
	//create an article
	gotoAddnewConntent();
	createNewArticle("myarticle", "myarticle", "greet", "Good afternoon");
    //create a new podcast
	gotoAddnewConntent();
    createNewPodcast("podcast", "podcast", "hay365.net");
    //check result for success
    waitForAndGetElementByXpath("//a[@title='Set up your browsing preferences']").click();
    waitForAndGetElementByLinkText("Advanced").click();
    WebElement eStructure = waitForAndGetElementById("enableStructure");
    if(eStructure.isSelected()!=true)
    eStructure.click();
    waitForAndGetElementByLinkText("Save").click();
    pause(5000);
    waitForAndGetElementByXpath("//div[@title='Sites Management']").click();
    pause(2000);
    if(isElementPresent(By.linkText("myarticle")))
    {
    	System.out.println("Create an article document successfully!");
    	pause(1000);
    	action.doubleClick(waitForAndGetElementByLinkText("myarticle")).build().perform();
    	pause(2000);
    	if(isElementPresent(By.linkText("podcast")))
    	{	System.out.println("Create a podcast document successfully!");
    	//delete podcast
    	deleteNode("podcast ");
    	}
    	pause(4000);
    	//delete article document
    	waitForAndGetElementByXpath("//div[@title='Sites Management']").click();
    	pause(3000);
    	deleteNode("myarticle ");
    	pause(2000);
    	
    }
}
@Test(groups={"general","content"})
public void addPodcastDocumentinContentFolder() {
	
	//create a content folder
	try {
		createNewContentFolder("myfolder", "");
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	action.doubleClick(waitForAndGetElementByLinkText("myfolder")).build().perform();
  //create a new podcast
	gotoAddnewConntent();
  createNewPodcast("podcast", "podcast", "hay365.net");
  //check result for success

  waitForAndGetElementByXpath("//div[@title='Sites Management']").click();
  pause(2000);
  if(isElementPresent(By.linkText("myfolder")))
  {
  	System.out.println("Create a content folder successfully!");
  	pause(1000);
  	action.doubleClick(waitForAndGetElementByLinkText("myfolder")).build().perform();
  	pause(2000);
  	if(isElementPresent(By.linkText("podcast")))
  	{	System.out.println("Create a podcast document successfully!");
  	//delete podcast
  	deleteNode("podcast ");
  	}
  	pause(4000);
  	//delete article document
  	waitForAndGetElementByXpath("//div[@title='Sites Management']").click();
  	pause(3000);
  	deleteNode("myfolder ");
  	pause(2000);
  	
  }
	
}
//add a podcast document in a document folder
@Test(groups={"general","document"})
public void addPodcastDocumentinDocumentFolder() {
	
	//create a document folder
    createNewDocumentFolder("mydocumentfolder","");
	action.doubleClick(waitForAndGetElementByLinkText("mydocumentfolder")).build().perform();
  //create a new podcast
	gotoAddnewConntent();
  createNewPodcast("podcast", "podcast", "hay365.net");
  //check result for success

  waitForAndGetElementByXpath("//div[@title='Sites Management']").click();
  pause(2000);
  if(isElementPresent(By.linkText("mydocumentfolder")))
  {
  	System.out.println("Create a content folder successfully!");
  	pause(1000);
  	action.doubleClick(waitForAndGetElementByLinkText("mydocumentfolder")).build().perform();
  	pause(2000);
  	if(isElementPresent(By.linkText("podcast")))
  	{	System.out.println("Create a podcast document successfully!");
  	//delete podcast
  	deleteNode("podcast ");
  	}
  	pause(4000);
  	//delete article document
  	waitForAndGetElementByXpath("//div[@title='Sites Management']").click();
  	pause(3000);
  	deleteNode("mydocumentfolder ");
  	pause(2000);
  	
  }
	
}
  @BeforeSuite(groups={"general","article"})
  public void beforeSuite() {
	  driver.manage().window().maximize();
	  loginEcms("john", "gtn");
	  enterSiteForm();
	  
  }

  @AfterSuite(groups={"general","article"})
  public void afterSuite() {
	  logoutEcms();
	  driver.close();
  }

}
