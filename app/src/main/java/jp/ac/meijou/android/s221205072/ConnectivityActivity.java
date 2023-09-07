package jp.ac.meijou.android.s221205072;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Bundle;

import java.util.stream.Collectors;

import jp.ac.meijou.android.s221205072.databinding.ActivityConnectivityBinding;
import android.net.LinkAddress;
public class ConnectivityActivity extends AppCompatActivity {
    private ActivityConnectivityBinding binding;
    private ConnectivityManager connectivityManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConnectivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        connectivityManager = getSystemService(ConnectivityManager.class);
        var currentNetwork = connectivityManager.getActiveNetwork();
        updateTransport(currentNetwork);
        updateIpAddress(currentNetwork);
    }

    private void updateTransport(Network network) {
        var caps = connectivityManager.getNetworkCapabilities(network);

        if (caps != null) {
            String transport;
            if (caps.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                transport = "モバイル通信";
            } else if (caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                transport = "WiFi";
            } else {
                transport = "そ􏰁他";
            }
            binding.textView4.setText(transport);
        }
    }

    private void updateIpAddress(Network network) {
        var linkProperties = connectivityManager.getLinkProperties(network);

        if (linkProperties != null) {
            var addresses = linkProperties.getLinkAddresses().stream()
                    .map(LinkAddress::toString)
                    .collect(Collectors.joining("\n"));
            binding.textView6.setText(addresses);
        }
    }

}