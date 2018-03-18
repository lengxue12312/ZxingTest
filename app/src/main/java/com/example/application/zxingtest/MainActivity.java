package com.example.application.zxingtest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imageView;
    private Button button;
    private Button button2;
    private EditText et_text;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    private void initView() {
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        et_text = findViewById(R.id.et_text);
        button2 = findViewById(R.id.button2);
        textView = findViewById(R.id.textView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                String content = "";
                if (et_text.getText().toString().equals("")) {
                    Toast.makeText(this, "请输入要生成的二维码信息", Toast.LENGTH_SHORT).show();
                }
                content = et_text.getText().toString();
                Bitmap qrCode = EncodingUtils.createQRCode(content, 300, 300, null);
                if (qrCode != null) {
                    imageView.setImageBitmap(qrCode);
                } else {
                    Toast.makeText(this, "二维码生成失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button2:
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent,0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String result = data.getExtras().getString("result");
        textView.setText(result);
    }
}
