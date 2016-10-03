package eu.operando.core.watchdog;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Vector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import eu.operando.api.model.PrivacySetting;
import eu.operando.moduleclients.ClientEmailServices;
import eu.operando.moduleclients.ClientOspEnforcement;
import eu.operando.moduleclients.ClientUserDeviceEnforcement;

@RunWith(MockitoJUnitRunner.class)
public class WdTaskUserPrivacySettingsCheckTests
{
	@Mock
	private ClientUserDeviceEnforcement clientUserDeviceEnforcement;
	
	@Mock
	private ClientOspEnforcement clientOspEnforcement;
	
	@Mock
	private ClientEmailServices clientEmailServices;
	
	@InjectMocks
	private WdTaskUserPrivacySettingsCheck application;
	
	@Test
	public void testRunPrivacySettingsCheck_SettingsMatch_RequestToEmailAnalystNotSent()
	{
		//Set up
		Vector<PrivacySetting> settingsCurrent = new Vector<PrivacySetting>();
		settingsCurrent.add(new PrivacySetting(1, "description", "name", "settingKey", "settingValue"));
		when(clientUserDeviceEnforcement.getPrivacySettingsCurrent(anyInt(), anyInt())).thenReturn(settingsCurrent);
		
		Vector<PrivacySetting> settingsRequired = new Vector<PrivacySetting>();
		settingsRequired.add(new PrivacySetting(1, "description", "name", "settingKey", "settingValue"));
		when(clientOspEnforcement.getPrivacySettingsRequired(anyInt(), anyInt())).thenReturn(settingsRequired);
		
		//Exercise
		application.run();
		
		//Verify
		verify(clientEmailServices, never()).sendEmail(anyString(), anyString(), anyString());
	}
	
	@Test
	public void testRunPrivacySettingsCheck_SettingsDoNotMatch_RequestToEmailAnalyst()
	{
		//Set up
		Vector<PrivacySetting> settingsCurrent = new Vector<PrivacySetting>();
		settingsCurrent.add(new PrivacySetting(1, "description", "name", "settingKey", "settingValue"));
		when(clientUserDeviceEnforcement.getPrivacySettingsCurrent(anyInt(), anyInt())).thenReturn(settingsCurrent);

		Vector<PrivacySetting> settingsRequired = new Vector<PrivacySetting>();
		settingsRequired.add(new PrivacySetting(1, "different description", "name", "settingKey", "different settingValue"));
		when(clientOspEnforcement.getPrivacySettingsRequired(anyInt(), anyInt())).thenReturn(settingsRequired);

		//Exercise
		application.run();

		//Verify
		verify(clientEmailServices).sendEmail(anyString(), anyString(), anyString());
	}
}
