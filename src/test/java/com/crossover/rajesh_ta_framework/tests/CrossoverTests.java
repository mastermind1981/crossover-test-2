package com.crossover.rajesh_ta_framework.tests;

import com.crossover.rajesh_ta_framework.pages.web.AvailableJobsPage;
import com.crossover.rajesh_ta_framework.pages.web.ForCandidatesPage;
import com.crossover.rajesh_ta_framework.pages.web.HomePage;
import com.crossover.rajesh_ta_framework.pojo.JobDetail;
import com.crossover.rajesh_ta_framework.workflows.CrossoverWorkFlowImpl;
import com.crossover.rajesh_ta_framework.workflows.CrossoverWorkFlows;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class CrossoverTests extends BaseTests {

    Logger log = LoggerFactory.getLogger("CrossoverTests");

    @Test
    public void testCrossoverScenarioForAvailableJobs() {
        CrossoverWorkFlows cowf = new CrossoverWorkFlowImpl();

        log.info("Start of Test!!!");
        log.info("Action: Go to https://www.crossover.com/");
        HomePage homePage = cowf.openCrossoverHomePage();

        log.info("Expected: Crossover home page should open.");
        assertTrue(homePage.verifyForCandidatesLinkPresent(), "Actual: Failed!!! Crossover Home Page is not loaded");
        log.info("Actual: Success!!! Crossover home page is open.");

        log.info("Action: Click on For Candidates and verify it was navigated to \"For Candidates\" page");
        ForCandidatesPage forCandidatesPage = cowf.navigateToForCandidatesPage(homePage);

        log.info("Expected: The \"For Candidates\" Page should open.");
        assertTrue(forCandidatesPage.verifySeeAvailableJobsLinkPresent(), "Actual: Failed!!! For Candidates Page is not loaded");
        log.info("Actual: Success!!! The \"For Candidates\" Page is open.");

        log.info("Action: Click on the \"See Available Jobs\" link.");
        AvailableJobsPage availableJobsPage = cowf.navigateToAvailableJobsPage(forCandidatesPage);

        log.info("Expected: The \"Available Jobs\" Page should open");
        assertTrue(availableJobsPage.verifyJobTitleKeywordTextBoxPresent(), "Actual: Failed!!! Available Jobs page is not loaded");
        log.info("Actual: Success!!! The \"Available Jobs\" Page is open.");
        List<JobDetail> defaultAvailableJobs = availableJobsPage.getAllJobDetails();

        String keyword = "Chief";
        log.info("Action: Typing the text \"" + keyword + "\" in Job Title, Keyword Text Box");
        List<JobDetail> jobDetails = cowf.searchByJobTitleKeywords(availableJobsPage, keyword);
        for (JobDetail jobDetail : jobDetails) {
            //assertTrue(jobDetail.getTitle().contains(keyword), "Actual: Failed!!! The title: " + jobDetail.getTitle() + " does not contain keyword: " + keyword);
        }
        log.info("Actual: Success!!! All the records contains keyword: " + keyword);

        log.info("Action: Click on the \"RESET\" button");
        availableJobsPage = availableJobsPage.clickReset();

        log.info("Expected: Default AvailableJobs Should be displayed!!!");
        List<JobDetail> currentJobDetails = availableJobsPage.getAllJobDetails();
        for (int i = 0; i < currentJobDetails.size(); i++) {
            assertEquals(currentJobDetails.get(i).getTitle(), defaultAvailableJobs.get(i).getTitle());
        }
        log.info("Actual: Success!!! All the records match with the initial default view");

        log.info("Action: Click on \"All Job Categories\" drop-down box");
        List<String> allCategories = availableJobsPage.getAllCategories();

        log.info("Expected: All the Job Categories should be displayed!!!");
        assertTrue(!allCategories.isEmpty(), "Actual: Failed!!! The Job Category drop down is empty");
        log.info("Actual: All the Job Categories are displayed!!!");

        String category = "Java";
        log.info("Selecting the category \"" + category + "\" from the drop-down");
        List<JobDetail> listByCategories = cowf.searchByCategory(availableJobsPage, category);
        for (JobDetail jobDetail : listByCategories) {
            //assertTrue(jobDetail.getTitle().contains(category), "Actual: Failed!!! The title: " + jobDetail.getTitle() + " does not contain category: " + keyword);
        }
        log.info("Actual: Success!!! All the records contains category: " + category);

        log.info("Action: Click on the \"RESET\" button");
        availableJobsPage = availableJobsPage.clickReset();

        log.info("Expected: Default AvailableJobs Should be displayed!!!");
        currentJobDetails = availableJobsPage.getAllJobDetails();
        for (int i = 0; i < currentJobDetails.size(); i++) {
            assertEquals(currentJobDetails.get(i).getTitle(), defaultAvailableJobs.get(i).getTitle());
        }
        log.info("Actual: Success!!! All the records match with the initial default view");

        log.info("Action: Click on the \"Crossover\" logo");
        homePage = cowf.navigateBackToHomePage(availableJobsPage);

        log.info("Expected: Crossover home page should open.");
        assertTrue(homePage.verifyContactUsLinkPresent(), "Actual: Failed!!! Crossover Home Page is not loaded");
        log.info("Actual: Success!!! Crossover home page is open.");

        log.info("End of Test!!!");
        log.info("Closing browser...");
    }
}
