package jp.ac.meijou.android.s221205072;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import java.util.Optional;

import jp.ac.meijou.android.s221205072.databinding.ActivityMain3Binding;
import jp.ac.meijou.android.s221205072.databinding.ActivityMainBinding;

public class MainActivity3 extends AppCompatActivity {


    private ActivityMain3Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //明示的Intent
        binding.button.setOnClickListener(view -> {
            var intent = new Intent (this, SubActivity.class);
            startActivity(intent);
        });

        //暗黙的Intent
        binding.button2.setOnClickListener(view -> {
            var intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://www.yahoo.co.jp"));
            startActivity(intent);
        });
        //文字を送信
        binding.button4.setOnClickListener(view -> {
            var intent = new Intent(this, SubActivity.class);
            intent.putExtra("text",binding.EditText.getText().toString());
            startActivity(intent);
        });


        binding.button5.setOnClickListener(view -> {
            var intent = new Intent(this,SubActivity.class);
            getActivityResult.launch(intent);
        });
    }

    private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                switch (result.getResultCode()) {
                    case RESULT_OK:
                        Optional.ofNullable(result.getData())
                                .map(data -> data.getStringExtra("ret"))
                                .map(text -> "Result: " + text)
                                .ifPresent(text -> binding.EditText2.setText(text));
                        break;
                    case RESULT_CANCELED:
                        binding.EditText2.setText("Result: Canceled");
                        break;
                    default:
                        binding.EditText2.setText("Result: Unknown(" + result.getResultCode() + ")");
                        break;
                }
            }
    );

}