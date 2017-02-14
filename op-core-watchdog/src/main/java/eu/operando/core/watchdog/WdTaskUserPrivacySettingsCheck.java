package eu.operando.core.watchdog;

import java.util.Vector;

import eu.operando.api.model.PrivacySetting;
import eu.operando.moduleclients.ClientEmailServices;
import eu.operando.moduleclients.ClientOspEnforcement;
import eu.operando.moduleclients.ClientUserDeviceEnforcement;

public class WdTaskUserPrivacySettingsCheck extends WdTask
{
	private ClientUserDeviceEnforcement clientUserDeviceEnforcement = null;
	private ClientOspEnforcement clientOspEnforcement = null;
	private ClientEmailServices clientEmailServices = null;
	private int userId = -1;
	private int ospId = -1;
	private String emailAddressForNotifications = "";

	public WdTaskUserPrivacySettingsCheck(ClientUserDeviceEnforcement clientUserDeviceEnforcement, ClientOspEnforcement clientOspEnforcement,
			ClientEmailServices clientEmailServices, int userId, int ospId, String emailAddressForNotifications)
	{
		this.clientUserDeviceEnforcement = clientUserDeviceEnforcement;
		this.clientOspEnforcement = clientOspEnforcement;
		this.clientEmailServices = clientEmailServices;
		this.userId = userId;
		this.ospId = ospId;
		this.emailAddressForNotifications = emailAddressForNotifications;
	}

	@Override
	public void run()
	{
		runPrivacySettingsCheck();
	}

	/**
	 * Retrieve the current and required privacy settings for this user with this OSP. Compare them, and if there's a mismatch, notify an analyst.
	 */
	private void runPrivacySettingsCheck()
	{
		// Get the user's current and required privacy settings with this OSP.
		Vector<PrivacySetting> privacySettingsCurrent = clientUserDeviceEnforcement.getPrivacySettingsCurrent(userId, ospId);
		Vector<PrivacySetting> privacySettingsRequired = clientOspEnforcement.getPrivacySettingsRequired(userId, ospId);

		// If there's a mismatch, send an email.
		if (!privacySettingsCurrent.equals(privacySettingsRequired))
		{
			String content = "The privacy settings for user " + userId + " with OSP " + ospId + " are not as required. This requires action.";
			String subject = "Privacy settings discrepancy";

			clientEmailServices.sendEmail(emailAddressForNotifications, subject, content);
		}
	}
}
