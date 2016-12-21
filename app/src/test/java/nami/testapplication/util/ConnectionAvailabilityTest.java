package nami.testapplication.util;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import nami.testproject.util.ConnectionAvailability;

public class ConnectionAvailabilityTest {
	private ConnectionAvailability connectionAvailability;
	private ConnectivityManager mockConnectivityManager;
	private NetworkInfo mockNetworkInfo;

	@Before
	public void setup() {
		mockConnectivityManager = Mockito.mock(ConnectivityManager.class);
		mockNetworkInfo = Mockito.mock(NetworkInfo.class);
		connectionAvailability = new ConnectionAvailability(mockConnectivityManager);
	}

	@Test
	public void isAvailable_returnsTrue() {
		Mockito.when(mockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
		Mockito.when(mockConnectivityManager.getActiveNetworkInfo().isAvailable()).thenReturn(true);
		Mockito.when(mockConnectivityManager.getActiveNetworkInfo().isConnected()).thenReturn(true);
		Assert.assertTrue(connectionAvailability.isAvailable());
	}

	@Test
	public void isAvailable_notAvailable_returnsFalse() {
		Mockito.when(mockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
		Mockito.when(mockConnectivityManager.getActiveNetworkInfo().isAvailable()).thenReturn(false);
		Assert.assertFalse(connectionAvailability.isAvailable());
	}

	@Test
	public void isAvailable_notConnected_returnsFalse() {
		Mockito.when(mockConnectivityManager.getActiveNetworkInfo()).thenReturn(mockNetworkInfo);
		Mockito.when(mockConnectivityManager.getActiveNetworkInfo().isConnected()).thenReturn(false);
		Assert.assertFalse(connectionAvailability.isAvailable());
	}

	@Test
	public void isAvailable_noNetworkInfo_returnsFalse() {
		Assert.assertFalse(connectionAvailability.isAvailable());
	}
}