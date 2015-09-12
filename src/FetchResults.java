import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.IssueService;


/**
 * Fetch results from the given URL
 * @author Shivam Sahu
 *
 */
public class FetchResults {
		private String url;
		FetchResults () {
			
		}
		FetchResults (String url) {
			this.url = url;
		}
		public Solution fetchResults() throws IOException {
			
		//Setup the client to hit the github api.
		GitHubClient client = new GitHubClient();
		String [] token = this.url.split("/");
		
		//Extract username and repository from URL
		String userName = token[token.length -2];
		String repository = token[token.length-1];
		
		//use issue service to get total issues from given repository.
		IssueService issueService = new IssueService(client);
		List<Issue> issues = issueService.getIssues(userName, repository, new HashMap<String, String>());
		
		//find the solution from the list of issues.
		Solution solution = new Solution();
		solution.getSolution(issues);
		return solution;
	}

}
