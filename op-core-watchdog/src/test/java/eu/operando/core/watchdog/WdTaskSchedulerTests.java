package eu.operando.core.watchdog;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class WdTaskSchedulerTests
{
	private static final int NUMBER_OF_RUNS_EXPECTED = 1000;
	private static final WdTaskStub TASK = new WdTaskStub();
	private static final int PERIOD_MILLIS = 2;
	
	@Test
	public void testStartChecks_TaskScheduledWithTimerToOccurOnceEveryPeriodStartingImmediately() throws InterruptedException
	{
		// Set up
		WdTaskScheduler wdTaskScheduler = new WdTaskScheduler(TASK, PERIOD_MILLIS);
		
		// Exercise
		wdTaskScheduler.startTask();
		
		//Wait
		int timeNumberOfRunsShouldReachExpected = (NUMBER_OF_RUNS_EXPECTED - 1) * PERIOD_MILLIS;
		int delayInBetweenConsecutiveRuns = PERIOD_MILLIS / 2;
		int millisDelayAfterTaskRunExpectedNumberOfTimesBeforeRunAnotherTime = timeNumberOfRunsShouldReachExpected + delayInBetweenConsecutiveRuns;
		Thread.sleep(millisDelayAfterTaskRunExpectedNumberOfTimesBeforeRunAnotherTime);
		
		// Verify
		int numberOfRunsActual = TASK.getNumberOfRuns();
		assertThat(numberOfRunsActual, is(greaterThanOrEqualTo(NUMBER_OF_RUNS_EXPECTED - 1)));
		assertThat(numberOfRunsActual, is(lessThanOrEqualTo(NUMBER_OF_RUNS_EXPECTED + 1)));
	}
}
