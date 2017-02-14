package eu.operando.core.watchdog;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Vector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WdAppTests
{
	@Test
	public void testStartAllChecks_MultipleSchedulers_StartTaskCalledOnEach()
	{
		// Set up - mock some schedulers.
		WdTaskScheduler mockWdTaskScheduler1 = mock(WdTaskScheduler.class);
		WdTaskScheduler mockWdTaskScheduler2 = mock(WdTaskScheduler.class);
		
		// Set up - configure the app with the mock schedulers.
		Vector<WdTaskScheduler> taskSchedulers = new Vector<WdTaskScheduler>();
		taskSchedulers.add(mockWdTaskScheduler1);
		taskSchedulers.add(mockWdTaskScheduler2);
		IWdConfiguration configuration = new WdConfigurationManual(taskSchedulers);
		WdApp app = new WdApp(configuration);
		
		// Exercise - call startAllTasks.
		app.startAllTasks();
		
		// Verify - check that each scheduler has startTask called on it.
		verify(mockWdTaskScheduler1).startTask();
		verify(mockWdTaskScheduler2).startTask();
	}
}
