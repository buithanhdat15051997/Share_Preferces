package com.example.share_preferces;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button btn;
EditText edtname, edtpass;
CheckBox check;

SharedPreferences sharedPreferences;// khai báo toàn cục
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();
        sharedPreferences = getSharedPreferences("datalogin",MODE_PRIVATE); // khởi tạo tên file là "datalogin", và kiểu
        // lẤY giá trị từ  file datalogin, khi đăng nhập lại lần 2 có ghi nhớ mật khẩu
        edtname.setText(sharedPreferences.getString("name","")); //  lấy từ file datalogin đổ cho edtname
        edtpass.setText(sharedPreferences.getString("pass",""));
        check.setChecked(sharedPreferences.getBoolean("check",false));// mặc định k check

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtname.getText().toString().trim();
                String pass =  edtpass.getText().toString().trim();
                // Gọi editor Share preferences để chỉnh sửa file
                if(name.equals("btd")&& pass.equals("123")){

                    Toast.makeText(MainActivity.this,"Thành công",Toast.LENGTH_LONG).show();
                    // nếu checkbox  được check
                    if(check.isChecked()){
                        // gọi editor để  mở file share preferences và ghi nội dung vào để LƯU
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name",name);// gồm tên và giá trị
                        editor.putString("pass",pass);
                        editor.putBoolean("check",true);
                        editor.apply(); // xác nhận
                    }else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("name");
                        editor.remove("pass");
                        editor.remove("check");
                        editor.apply();

                    }

                }else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("name");
                    editor.remove("pass");
                    editor.remove("check");
                    editor.apply();

                    Toast.makeText(MainActivity.this,"Thất bại",Toast.LENGTH_LONG).show();

                }
            }
        });




    }
    public void anhxa(){
        btn = (Button) findViewById(R.id.button);
        edtname = (EditText) findViewById(R.id.editText2);
        edtpass = (EditText) findViewById(R.id.editText3);
        check = (CheckBox) findViewById(R.id.checkBox);

    }
}
