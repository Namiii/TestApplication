package nami.testproject.util;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class ConnectionAvailability {
	private ConnectivityManager connectivityManager;

	public ConnectionAvailability(ConnectivityManager connectivityManager) {
		this.connectivityManager = connectivityManager;
	}

	public boolean isAvailable() {
		NetworkInfo i = connectivityManager.getActiveNetworkInfo();
		return i != null && i.isConnected() && i.isAvailable();
	}
}
