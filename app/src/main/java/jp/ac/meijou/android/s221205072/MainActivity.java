package jp.ac.meijou.android.s221205072;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import jp.ac.meijou.android.s221205072.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prefDataStore = prefDataStore.getInstance(this);

        prefDataStore.getString("name").ifPresent(name -> binding.text.setText(name));

        binding.button.setOnClickListener(view -> {
            var text = binding.editTextText.getText().toString();
            binding.text.setText(text);
        });
        binding.saveButton.setOnClickListener(view -> {
            var text = binding.editTextText.getText().toString();
            prefDataStore.setString("name", text);
        });
        //EditTextが変更されたらTextViewを更新する
//        binding.editTextText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                // テキストが更新される直前に呼ばれる
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                // 文字を1つ入力された時に呼ばれる
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                // テキストが更新されたあとに呼ばれる
//                binding.text.setText(editable.toString());
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        prefDataStore.getString("name").ifPresent(name -> binding.text.setText(name));
    }
}