package eu.operando.core.watchdog;

import java.util.Vector;

import eu.operando.Utils;
import eu.operando.moduleclients.ClientEmailServices;
import eu.operando.moduleclients.ClientOspEnforcement;
import eu.operando.moduleclients.ClientUserDeviceEnforcement;

public class WdConfigurationFromPropertiesFile implements IWdConfiguration
{
	// Properties file property names.
	private static final String PROPERTY_NAME_CHECK_OSP_PRIVACY_POLICIES = "checkOspPrivacyPolicies";
	private static final String PROPERTY_NAME_PERIOD_MILLIS_OSP_PRIVACY_POLICIES_CHECK = "periodMillisOspPrivacyPoliciesCheck";
	private static final String PROPERTY_NAME_CHECK_OSP_PRIVACY_OPTIONS = "checkOspPrivacyOptions";
	private static final String PROPERTY_NAME_PERIOD_MILLIS_OSP_PRIVACY_OPTIONS_CHECK = "periodMillisOspPrivacyOptionsCheck";
	private static final String PROPERTY_NAME_CHECK_USER_PRIVACY_SETTINGS = "checkUserPrivacySettings";
	private static final String PROPERTY_NAME_PERIOD_MILLIS_USER_PRIVACY_SETTINGS_CHECK = "periodMillisUserPrivacySettingsCheck";
	private static final String PROPERTY_NAME_ORIGIN_USER_DEVICE_ENFORCEMENT = "originUserDeviceEnforcement";
	private static final String PROPERTY_NAME_ORIGIN_OSP_ENFORCEMENT = "originOspEnforcement";
	private static final String PROPERTY_NAME_ORIGIN_EMAIL_SERVICES = "originEmailServices";
	private static final String PROPERTY_NAME_USER_ID = "userId";
	private static final String PROPERTY_NAME_OSP_ID = "ospId";
	private static final String PROPERTY_NAME_EMAIL_ADDRESS_FOR_NOTIFICATIONS = "emailAddressForNotifications";

	private Vector<WdTaskScheduler> wdTaskSchedulers = new Vector<WdTaskScheduler>();

	public WdConfigurationFromPropertiesFile(String propertiesFileLocation)
	{

		registerScheduler(new WdTaskOspPrivacyPoliciesCheck(), propertiesFileLocation, PROPERTY_NAME_CHECK_OSP_PRIVACY_POLICIES,
				PROPERTY_NAME_PERIOD_MILLIS_OSP_PRIVACY_POLICIES_CHECK);

		registerScheduler(new WdTaskOspPrivacyOptionsCheck(), propertiesFileLocation, PROPERTY_NAME_CHECK_OSP_PRIVACY_OPTIONS,
				PROPERTY_NAME_PERIOD_MILLIS_OSP_PRIVACY_OPTIONS_CHECK);

		WdTaskUserPrivacySettingsCheck wdTaskUserPrivacySettingsCheck = configureWdTaskUserPrivacySettingsCheck(propertiesFileLocation);
		registerScheduler(wdTaskUserPrivacySettingsCheck, propertiesFileLocation, PROPERTY_NAME_CHECK_USER_PRIVACY_SETTINGS,
				PROPERTY_NAME_PERIOD_MILLIS_USER_PRIVACY_SETTINGS_CHECK);
	}

	private WdTaskUserPrivacySettingsCheck configureWdTaskUserPrivacySettingsCheck(String propertiesFileLocation)
	{
		String originUserDeviceEnforcement = Utils.loadPropertyString(propertiesFileLocation, PROPERTY_NAME_ORIGIN_USER_DEVICE_ENFORCEMENT);
		String originOspEnforcement = Utils.loadPropertyString(propertiesFileLocation, PROPERTY_NAME_ORIGIN_OSP_ENFORCEMENT);
		String originEmailServices = Utils.loadPropertyString(propertiesFileLocation, PROPERTY_NAME_ORIGIN_EMAIL_SERVICES);
		String emailAddressForNotifications = Utils.loadPropertyString(propertiesFileLocation, PROPERTY_NAME_EMAIL_ADDRESS_FOR_NOTIFICATIONS);

		// Which user, which OSP?
		// TODO - in the next prototype this should be a list of all userId-ospId pairs. Perhaps read from the DB and put into a hashmap.
		int userId = Utils.loadPropertyInt(propertiesFileLocation, PROPERTY_NAME_USER_ID);
		int ospId = Utils.loadPropertyInt(propertiesFileLocation, PROPERTY_NAME_OSP_ID);

		WdTaskUserPrivacySettingsCheck wdTaskUserPrivacySettingsCheck = new WdTaskUserPrivacySettingsCheck(new ClientUserDeviceEnforcement(originUserDeviceEnforcement),
				new ClientOspEnforcement(originOspEnforcement), new ClientEmailServices(originEmailServices), userId, ospId, emailAddressForNotifications);
		return wdTaskUserPrivacySettingsCheck;
	}

	private void registerScheduler(WdTask taskToRegister, String propertiesFileLocation, String propertyNameTaskEnabled, String propertyNamePeriodOfTask)
	{
		boolean shouldRegister = Utils.loadPropertyBool(propertiesFileLocation, propertyNameTaskEnabled);
		if (shouldRegister)
		{
			int periodOfTaskRepetition = Utils.loadPropertyInt(propertiesFileLocation, propertyNamePeriodOfTask);
			WdTaskScheduler wdTaskScheduler = new WdTaskScheduler(taskToRegister, periodOfTaskRepetition);
			wdTaskSchedulers.add(wdTaskScheduler);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see eu.operando.core.watchdog.IWdConfiguration#getWdTaskSchedulers()
	 */
	@Override
	public Vector<WdTaskScheduler> getWdTaskSchedulers()
	{
		return wdTaskSchedulers;
	}
}
