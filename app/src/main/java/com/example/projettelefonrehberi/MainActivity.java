package com.example.projettelefonrehberi;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.projettelefonrehberi.databinding.ActivityMainBinding;
import com.example.projettelefonrehberi.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private com.example.projettelefonrehberi.databinding.ActivityMainBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.projettelefonrehberi.databinding.ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        sharedPreferences = getSharedPreferences("projerttelefonrehberı", Context.MODE_PRIVATE);
        binding.btnkaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isim = binding.editTextName.getText().toString().trim();
                String telefon = binding.editTextPhone.getText().toString().trim();

                if (!isim.isEmpty() && !telefon.isEmpty()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("isim", isim);
                    editor.putString("telefon", telefon);
                    editor.apply();


                    Toast.makeText(MainActivity.this, "Kişi Kaydedildi", Toast.LENGTH_SHORT).show();
                    binding.editTextName.setText("");
                    binding.editTextPhone.setText("");
                    showSavedContact();
                } else {
                    Toast.makeText(MainActivity.this, "Lütfen hem isim hem de telefon numarası girin", Toast.LENGTH_SHORT).show();
                }
            }
        });
        showSavedContact();
    }

    private void showSavedContact() {
        String name = sharedPreferences.getString("isim", "isim yok");
        String phone = sharedPreferences.getString("telefon", "telefon yok");

        binding.btnkaydet.setText("isim: " + name    + "telefon: " + phone);
    }
}
