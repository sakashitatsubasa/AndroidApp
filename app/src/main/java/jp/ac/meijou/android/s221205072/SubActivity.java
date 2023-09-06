package jp.ac.meijou.android.s221205072;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import jp.ac.meijou.android.s221205072.databinding.ActivityMain2Binding;
import jp.ac.meijou.android.s221205072.databinding.ActivityMain3Binding;
import jp.ac.meijou.android.s221205072.databinding.ActivitySubBinding;

public class SubActivity extends AppCompatActivity {
    private ActivitySubBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(view -> {
            var intent = new Intent();
            intent.putExtra("ret", "OK");
            setResult(RESULT_OK, intent);
            finish();
        });

        binding.button2.setOnClickListener(view -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}