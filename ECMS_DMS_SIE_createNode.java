package ecms.functionnal.dms.siteexplorer.createnode;

import java.util.List;
import org.exoplatform.selenium.platform.ecms.EcmsBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class ECMS_DMS_SIE_createNode extends EcmsBase{

//create a content folder inside another content folder

@Test(groups={"general","content"})
  public void createContentFolderInsideContentFolder() {

	 //create a node at root path
	pause(2000); 
	try {
		createNewContentFolder("Book", "Name");
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 pause(3000);
	 if(isElementPresent(By.linkText("Book")))
	 {
		 //create a sub-node
		 WebElement eFolder= waitForAndGetElementByLinkText("Book");
		 action.doubleClick(eFolder).build().perform();
		 pause(2000);
		 try {
			createNewContentFolder("sub", "subTitle");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 pause(2000);
		 if(!isElementPresent(By.linkText("sub")))
			 System.out.println("Create sub node unsuccessfully!");
		 //delete sub-node
		 else
		 { 
			 pause(1000);
			 deleteNode("sub ");
			          
		 //delete parent node
		 waitForAndGetElementByXpath("//div[@title='Sites Management']").click();
		 pause(3000);
		 deleteNode("Book ");
		 }
	 }
	 else 
		 System.out.println("Create pa" +
		 		"rent node unsuccessfully!");
}

//create Document 
@Test(groups={"general","document"})
public void createContentFolderInsideDocumentFolder()
{
	
		 //create a node at root path
		 pause(2000); 
		 createNewDocumentFolder("Book", "Name");
		 pause(3000);
		 if(isElementPresent(By.linkText("Book")))
		 {
			 //create a sub-node
			 WebElement eFolder= waitForAndGetElementByLinkText("Book");
			 action.doubleClick(eFolder).build().perform();
			 pause(5000);
			 waitForAndGetElementByLinkText("New Folder").click();
			 Select typeFolder= new Select(waitForAndGetElementByName("type"));
			 List<WebElement> allOption= typeFolder.getOptions();
			 for (WebElement option: allOption)
			 {
				if(option.getText().equals("Content Folder"))
					{ System.out.println("Fail");break;}
				else  System.out.println("Cannot add a content folder inside a document folder");
					
			 }
			 //delete parent node
			 waitForAndGetElementByXpath("//div[@title='Sites Management']").click();
			 pause(3000);
			 deleteNode("Book ");
			 
		 }
		 else 
			 System.out.println("Create pa" +
			 		"rent node unsuccessfully!");
} 

//create a content folder inside Locked Document Folder, File Plan
@Test(groups={"general","lockedDocument"})
public void createContentFolderinsideLockedDocument()
{
	pause(1000); 
	
	 //create a node at root path
	 pause(2000); 
	 createNewDocumentFolder("Book", "Name");
	 WebElement eDoc = waitForAndGetElementByLinkText("Book");
	 action.contextClick(eDoc).build().perform();
	 waitForAndGetElementByLinkText("Lock").click();
	 pause(3000);
	 if(isElementPresent(By.linkText("Book")))
	 {
		 //create a sub-node
		 WebElement eFolder= waitForAndGetElementByLinkText("Book");
		 action.doubleClick(eFolder).build().perform();
		 pause(2000);
		 waitForAndGetElementByLinkText("New Folder").click();
		 Select typeFolder= new Select(waitForAndGetElementByName("type"));
		 List<WebElement> allOption= typeFolder.getOptions();
		 for (WebElement option: allOption)
		 {
			if(option.getText().equals("Content Folder"))
				{ System.out.println("Fail");break;}
			else  System.out.println("Cannot add a content folder inside a locked document folder");	
		 }
		        
		 //delete parent node
		 waitForAndGetElementByXpath("//div[@title='Sites Management']").click();
		 pause(5000);
		 deleteNode("Book (Locked by john)");
		 
	 }
	 else 
		 System.out.println("Create document folder unsuccessfully!"); 
	
}
// create a content folder inside a locked file plan
@Test(groups={"general","lockedFilePlan"})
public void createContentFolderinsideLockedFilePlan()
{
		
	 // create a File Plan
	 	gotoAddnewConntent();
	 	createNewFilePlan("fPlan", "cat identifier", "Disposition", "eXo", "trigger");
	 	pause(4000);
	 	waitForAndGetElementByXpath("//div[@title='Sites Management']").click();
	 	pause(5000);
	 	WebElement fPlan = waitForAndGetElementByLinkText("fPlan");
	 	pause(2000);
		action.contextClick(fPlan).build().perform();
		pause(2000);
		waitForAndGetElementByLinkText("Lock").click();
		pause(3000);
		if(isElementPresent(By.linkText("fPlan")))
		{
		 //create a sub-node
		 WebElement eFolder= waitForAndGetElementByLinkText("fPlan");
		 action.doubleClick(eFolder).build().perform();
		 pause(2000);
		 waitForAndGetElementByLinkText("New Folder").click();
		 pause(1000);
		 Select typeFolder= new Select(waitForAndGetElementByName("type"));
		 List<WebElement> allOption= typeFolder.getOptions();
		 for (WebElement option: allOption)
				 {
					if(option.getText().equals("Content Folder"))
						{ System.out.println("Fail");break;}
					else  System.out.println("Cannot add a content folder inside a locked file plan");	
				 }
			pause(1000);	        
				 //delete parent node
			waitForAndGetElementByXpath("//div[@title='Sites Management']").click();
			pause(5000);
			deleteNode("fPlan (Locked by john)");
				 
			 }
			 else 
				 System.out.println("Create document folder unsuccessfully!"); 
}

  @BeforeClass(groups={"general","content","document","lockedDocument","lockedFilePlan"})
  public void beforeClass() {
	  driver.manage().window().maximize();
	  loginEcms("john", "gtn");
	  enterSiteForm();
  }

  @AfterClass(groups={"general","content","document","lockedDocument","lockedFilePlan"})
  public void afterClass() {
	  logoutEcms();
	  driver.close();
  }

  @BeforeTest(groups={"general","content","document","lockedDocument","lockedFilePlan"})
  public void beforeTest() {
		
  }

  @AfterTest(groups={"general","content","document","lockedDocument","lockedFilePlan"})
  public void afterTest() {
  }

}
