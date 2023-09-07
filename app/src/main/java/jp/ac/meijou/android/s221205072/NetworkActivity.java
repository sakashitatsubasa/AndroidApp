package jp.ac.meijou.android.s221205072;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.Optional;

import jp.ac.meijou.android.s221205072.databinding.ActivityNetworkBinding;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkActivity extends AppCompatActivity {
    private final OkHttpClient okHttpClient = new OkHttpClient();

    private final Moshi moshi = new Moshi.Builder().build();

    private final JsonAdapter<Gist> gistJsonAdapter = moshi.adapter(Gist.class);
    private ActivityNetworkBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNetworkBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        var request = new Request.Builder()
                .url("https://placehold.jp/350x350.png")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // 通信に失敗した時に呼ばれる
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws
                    IOException {
                var bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                runOnUiThread(() -> binding.imageView.setImageBitmap(bitmap));
            }
        });
        binding.button18.setOnClickListener(view -> {
            var text = binding.editTextText2.getText().toString();
            var url = Uri.parse("https://placehold.jp/3d4070/ffffff/500x500.png")
                    .buildUpon()
                    .appendQueryParameter("text",text)
                    .build()
                    .toString();

            getImage(url);
        });


    //getImage("https://placehold.jp/500x500.png");
    }

    private void getImage(String url) {
// リクエスト先に画像URLを指定
        var request = new Request.Builder()
                .url(url)
                .build();

// 非同期通信でリクエスト
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
// 通信に失敗した時に呼􏰃れる
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
// InputStreamをBitmapに変換
                var bitmap = BitmapFactory.decodeStream(response.body().byteStream());
// UIスレッド以外で更新するとクラッシュする􏰁で、UIスレッド上で実行させる

            runOnUiThread(() -> binding.imageView.setImageBitmap(bitmap));
        }
    });
    }
}

