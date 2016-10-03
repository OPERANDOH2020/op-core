package eu.operando.core.watchdog;

public class WdTaskStub extends WdTask
{	
	private int numberOfRuns = 0;
	
	@Override
	public void run()
	{
		numberOfRuns++;
	}

	public int getNumberOfRuns()
	{
		return numberOfRuns;
	}
}
