package com.crossover.rajesh_ta_framework.pages.web;

import org.openqa.selenium.By;

public class ForCandidatesPage extends BasePage {

    private final By seeAvailableJobsLink = By.linkText("SEE AVAILABLE JOBS");

    public AvailableJobsPage clickSeeAvailableJobs() {
        waitForElement(seeAvailableJobsLink).click();
        return new AvailableJobsPage();
    }

    public boolean verifySeeAvailableJobsLinkPresent() {
        return isElementPresent(seeAvailableJobsLink);
    }
}
