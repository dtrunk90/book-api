import hudson.plugins.git.BranchSpec
import hudson.plugins.git.GitSCM
import hudson.plugins.git.UserRemoteConfig
import hudson.plugins.git.extensions.impl.CleanCheckout
import jenkins.model.Jenkins
import org.jenkinsci.plugins.workflow.job.WorkflowJob
import org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition

println "--> Creating pipeline jobs"

def jenkins = Jenkins.instance

def jobs = [
        'book-api-ci'             : 'Jenkinsfile',
        'book-api-security-scan'  : 'dependencyCheck.Jenkinsfile'
]

//def remoteUrl = "file:///var/jenkins_home/workspace/book-api"
def remoteUrl = "https://github.com/dtrunk90/book-api.git"

jobs.each { jobName, jenkinsfilePath ->
    if (jenkins.getItem(jobName) == null) {
        def remoteConfig = new UserRemoteConfig(remoteUrl, null, null, null)
        def scm = new GitSCM(
                [remoteConfig],
                [new BranchSpec("*/main")],
                false,
                Collections.emptyList(),
                null,
                null,
                [new CleanCheckout()]
        )

        def job = jenkins.createProject(WorkflowJob, jobName)
        job.definition = new CpsScmFlowDefinition(scm, jenkinsfilePath)
        job.save()

        println "--> Created job: ${jobName}"
    } else {
        println "--> Job '${jobName}' already exists, skipping"
    }
}
