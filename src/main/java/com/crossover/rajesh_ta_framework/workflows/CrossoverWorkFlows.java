package com.crossover.rajesh_ta_framework.workflows;

import com.crossover.rajesh_ta_framework.pages.web.AvailableJobsPage;
import com.crossover.rajesh_ta_framework.pages.web.ForCandidatesPage;
import com.crossover.rajesh_ta_framework.pages.web.HomePage;
import com.crossover.rajesh_ta_framework.pojo.JobDetail;
import java.util.List;

/**
 *
 * @author Rajesh
 */
public interface CrossoverWorkFlows {

    public HomePage openCrossoverHomePage();

    public ForCandidatesPage navigateToForCandidatesPage(HomePage homePage);

    public AvailableJobsPage navigateToAvailableJobsPage(ForCandidatesPage forCandidatesPage);

    public List<JobDetail> searchByJobTitleKeywords(AvailableJobsPage availableJobsPage, String jobTitleKeywords);

    public List<JobDetail> searchByCategory(AvailableJobsPage availableJobsPage, String category);

    public HomePage navigateBackToHomePage(AvailableJobsPage availableJobsPage);
}
