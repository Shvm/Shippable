import java.util.Calendar;
import java.util.List;

import org.eclipse.egit.github.core.Issue;


/**
 * This class is used to calculate the solution.
 * @author Shivam Sahu
 *
 */
public class Solution {
	private int firstAnswer;
	private int secondAnswer;
	private int thirdAnswer;
	private int fourthAnswer;

	
	/**
	 * this method takes the total no of issues and calulate the results accordingly
	 * @param issues
	 */
	public void getSolution(List<Issue> issues) {
		Calendar last24Hrs = Calendar.getInstance();
		last24Hrs.add(Calendar.DATE, -1);
		Calendar lastSevenDays = Calendar.getInstance();
		lastSevenDays.add(Calendar.DATE, -7);
		for (Issue issue : issues) {

			//Check if issue is opened in last 24 Hours.
			if ("open".equals(issue.getState())
					&& (issue.getCreatedAt().compareTo(last24Hrs.getTime()) > 0)) {
				firstAnswer++;
				secondAnswer++;
			}

			//Check if issue is opened in last 24 Hours and less than & 7 days.
			if ("open".equals(issue.getState())
					&& (issue.getCreatedAt().compareTo(lastSevenDays.getTime()) > 0)
					&& (issue.getCreatedAt().compareTo(last24Hrs.getTime()) < 0)) {
				firstAnswer++;
				thirdAnswer++;
			}
			
			//Check if issue is opened in more than 7 days.
			if ("open".equals(issue.getState())
					&& (issue.getCreatedAt().compareTo(lastSevenDays.getTime()) < 0)) {
				firstAnswer++;
				fourthAnswer++;
			}

		}
	}

	/**
	 * @return int
	 */
	public int getFirstAnswer() {
		return firstAnswer;
	}
	
	/**
	 * @return int
	 */
	public int getSecondAnswer() {
		return secondAnswer;
	}

	/**
	 * @return int
	 */
	public int getThirdAnswer() {
		return thirdAnswer;
	}

	/**
	 * @return int
	 */
	public int getFourthAnswer() {
		return fourthAnswer;
	}

}
