package com.vinsguru.listener;

import com.vinsguru.util.Config;
import com.vinsguru.util.Constants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

//esta clase va a guardar la imagen del error cuando el test falla.
public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        //de donde obtiene el driver?
        // to variable driver assign the value of the result in class (ITestResult)
        TakesScreenshot driver = (TakesScreenshot) result.getTestContext().getAttribute(Constants.DRIVER);
        //put in the variable screenshot the capture of the image in the driver as type base64
        String screenshot = driver.getScreenshotAs(OutputType.BASE64);
        //give format: put screenshot in "%s"
        String htmlImageFormat = "<img width=700px src='data:image/png;base64,%s' />";
        //format the variable screenshot using the format in htmlImageFormat
        String htmlImage = String.format(htmlImageFormat, screenshot);
        //System.out.print("OJO:el formato de la imagen es; "+htmlImage);
        Reporter.log(htmlImage);
        //ITestListener.super.onTestFailure(result);
    }
}
