package com.crossover.rajesh_ta_framework.workflows;

import com.crossover.rajesh_ta_framework.base.Configurations;
import com.crossover.rajesh_ta_framework.base.DriverFactory;
import com.crossover.rajesh_ta_framework.pages.web.AvailableJobsPage;
import com.crossover.rajesh_ta_framework.pages.web.ForCandidatesPage;
import com.crossover.rajesh_ta_framework.pages.web.HomePage;
import com.crossover.rajesh_ta_framework.pojo.JobDetail;
import java.util.List;

/**
 *
 * @author Rajesh
 */
public class CrossoverWorkFlowImpl implements CrossoverWorkFlows {

    @Override
    public HomePage openCrossoverHomePage() {
        DriverFactory.getDriver().get(Configurations.URL);
        return new HomePage();
    }

    @Override
    public ForCandidatesPage navigateToForCandidatesPage(HomePage homePage) {
        return homePage.clickForCandidates();
    }

    @Override
    public AvailableJobsPage navigateToAvailableJobsPage(ForCandidatesPage forCandidatesPage) {
        return forCandidatesPage.clickSeeAvailableJobs();
    }

    @Override
    public List<JobDetail> searchByJobTitleKeywords(AvailableJobsPage availableJobsPage, String jobTitleKeywords) {
        return availableJobsPage.enterJobTitleKeywords(jobTitleKeywords)
                .clickSearchJobs()
                .getAllJobDetails();
    }

    @Override
    public List<JobDetail> searchByCategory(AvailableJobsPage availableJobsPage, String category) {
        return availableJobsPage.selectACategoryByText(category)
                .getAllJobDetails();
    }

    @Override
    public HomePage navigateBackToHomePage(AvailableJobsPage availableJobsPage) {
        return availableJobsPage.clickCrossoverLogo();
    }

}
