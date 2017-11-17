package com.crossover.rajesh_ta_framework.pages.web;

import com.crossover.rajesh_ta_framework.pojo.JobDetail;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AvailableJobsPage extends BasePage {

    private final By jobTitleKeywordsTextBox = By.xpath("//input[@placeholder='Job title, keywords']");
    private final By pullDownButton = By.xpath("//*[contains(@class,'pull-right') and contains(@class,'caret')]");
    private final By searchJobsButton = By.xpath("//button[text()='SEARCH JOBS']");
    private final By resetButton = By.xpath("//button[text()='RESET']");
    private final By dropDownList = By.xpath("//li[@class='ui-select-choices-group']//div[contains(@class,'ui-select-choices-row')]//span[@class='ng-binding']");
    private final By crossoverLogoLink = By.xpath("//div[@class='logo']");

    public HomePage clickCrossoverLogo() {
        waitForElement(crossoverLogoLink).click();
        return new HomePage();
    }

    public AvailableJobsPage enterJobTitleKeywords(String text) {
        waitForElement(jobTitleKeywordsTextBox).sendKeys(text);
        return this;
    }

    public AvailableJobsPage clickPullDown() {
        waitForElement(pullDownButton).click();
        return this;
    }

    public AvailableJobsPage clickSearchJobs() {
        waitForElement(searchJobsButton).click();
        return this;
    }

    public AvailableJobsPage clickReset() {
        waitForElement(resetButton).click();
        return this;
    }

    public List<String> getAllCategories() {
        clickPullDown();
        List<WebElement> dropDownCategories = waitForElementsBy(dropDownList);
        List<String> options = new ArrayList<>();
        for (WebElement dropDownCategory : dropDownCategories) {
            options.add(dropDownCategory.getText());
        }
        return options;
    }

    public AvailableJobsPage selectACategoryByText(String text) {
        driver.findElement(By.xpath("//li[@class='ui-select-choices-group']//div[contains(@class,'ui-select-choices-row')]//span[text()='" + text + "']")).click();
        return this;
    }

    public List<JobDetail> getAllJobDetails() {
        List<JobDetail> jobDetails = new ArrayList<>();
        List<WebElement> jobDetailsElements = waitForElementsBy(By.xpath("//a[@ng-repeat='job in data.jobs']"));
        for (WebElement jobDetailsElement : jobDetailsElements) {
            JobDetail jobDetail = new JobDetail();
            jobDetail.setTitle(jobDetailsElement.findElement(By.className("title")).getText());
            int workTime = Integer.parseInt(jobDetailsElement.findElement(By.className("work-time")).getText().replace("h/week", "").trim());
            jobDetail.setWorkTime(workTime);
            String salaryHrTxt = jobDetailsElement.findElement(By.className("salary-hr")).getText().replaceAll("\\$(.*)\\/(.*)", "$1").trim().replace(",", "");
            jobDetail.setSalaryPerHour(Integer.parseInt(salaryHrTxt));
            String salaryYearTxt = jobDetailsElement.findElement(By.className("salary-year")).getText().replaceAll("\\$(.*)\\/(.*)", "$1").trim().replace(",", "");
            jobDetail.setSalaryPerYear(Integer.parseInt(salaryYearTxt));
            jobDetails.add(jobDetail);
        }
        return jobDetails;
    }

    public boolean verifyJobTitleKeywordTextBoxPresent() {
        return isElementPresent(jobTitleKeywordsTextBox);
    }

    public boolean verifyResetButtonDisappear() {
        return hasNoElementAsExpected(resetButton);
    }
}
