package eu.operando.server.watchdog;

import java.util.Vector;

public class WatchdogApplication
{
	private WatchdogClientI client = null;

	public WatchdogApplication(WatchdogClientI client)
	{
		this.client = client;		
	}
	
	/**
	 * Retrieve the current and required privacy settings for this user with this OSP.
	 * Compare them, and if there's a mismatch, notify an analyst.
	 */
	public void runPrivacySettingsCheck(int userId, int ospId)
	{
		//Get the user's current and required privacy settings with this OSP.
		Vector<PrivacySetting> privacySettingsCurrent = client.getPrivacySettingsCurrent(userId, ospId);
		Vector<PrivacySetting> privacySettingsRequired = client.getPrivacySettingsRequired(userId, ospId);
		
		//If there's a mismatch, send an email.
		if (!privacySettingsCurrent.equals(privacySettingsRequired))
		{
			client.notifyPrivacyAnalystAboutUserPrivacySettingDiscrepancy(userId, ospId);
		}
	}
}
