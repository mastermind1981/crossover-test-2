package com.crossover.rajesh_ta_framework.pages.web;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private final By forCandidatesLink = By.xpath("//a//span[text()='For Candidates']");
    private final By contactUsLink = By.partialLinkText("Contact us");

    public ForCandidatesPage clickForCandidates() {
        clickElementWithJavascript(waitForElement(forCandidatesLink));
        return new ForCandidatesPage();
    }

    public boolean verifyForCandidatesLinkPresent() {
        return isElementPresent(forCandidatesLink, 10);
    }

    public boolean verifyContactUsLinkPresent() {
        return isElementPresent(contactUsLink, 10);
    }
}
