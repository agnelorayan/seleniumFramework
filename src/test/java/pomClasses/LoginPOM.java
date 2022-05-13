package pomClasses;

import org.openqa.selenium.By;

public class LoginPOM {
	
	//lamda login

    public By email = By.xpath("//input[@name='email']");
    public By pwd_labmda = By.xpath("//input[@name='password']");
    public By login_lambda = By.xpath("//button[text()='Login']");
    public By automation_tab = By.xpath("//a[text()='Automation Testing']");
}
