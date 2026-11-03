package com.example.practicalfive;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Municipality extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        TextView tvTitle = findViewById(R.id.tv_city_title);
        ImageView ivCityImg = findViewById(R.id.img_city);
        TextView tvDesc = findViewById(R.id.tv_description);
        Button btnVisit = findViewById(R.id.btn_visit_site);

        TextProcessor processor = new TextProcessor();
        Intent incomingIntent = getIntent();
        String cityRaw = incomingIntent.getStringExtra(MainActivity.EXTRA_CITY_NAME);
        String cityName = processor.processMunicipality(cityRaw);

        if(cityName == null){
            Toast.makeText(this,"Error, invalid city data",Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        String[] cityInfoArray = getResources().getStringArray(R.array.municipalities_array);
        String url = "";
        int colorBgId, imgId;

        switch (cityName){
            case "Beijing":
                tvTitle.setText("Beijing");
                colorBgId = R.color.municipality_beijing;
                imgId = R.drawable.beijing;
                tvDesc.setText(cityInfoArray[0]);
                url = "https://english.beijing.gov.cn/";
                break;
            case "Shanghai":
                tvTitle.setText("Shanghai");
                colorBgId = R.color.municipality_shanghai;
                imgId = R.drawable.shanghai;
                tvDesc.setText(cityInfoArray[1]);
                url = "https://english.shanghai.gov.cn/";
                break;
            case "Chongqing":
                tvTitle.setText("Chongqing");
                colorBgId = R.color.municipality_chongqing;
                imgId = R.drawable.chongqing;
                tvDesc.setText(cityInfoArray[2]);
                url = "https://english.cq.gov.cn/";
                break;
            case "Tianjin":
                tvTitle.setText("Tianjin");
                colorBgId = R.color.municipality_tianjin;
                imgId = R.drawable.tianjin;
                tvDesc.setText(cityInfoArray[3]);
                url = "https://en.tj.gov.cn/";
                break;
            default:
                Toast.makeText(this,"Unknown city",Toast.LENGTH_SHORT).show();
                finish();
                return;
        }

        tvTitle.setBackgroundColor(getResources().getColor(colorBgId));
        tvTitle.setTextColor(getResources().getColor(R.color.color_text_light));
        btnVisit.setBackgroundTintList(getResources().getColorStateList(colorBgId));
        ivCityImg.setImageResource(imgId);

        final String finalUrl = url;
        btnVisit.setOnClickListener(v -> {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(finalUrl));
            try {
                startActivity(webIntent);
            }catch (ActivityNotFoundException e){
                Toast.makeText(Municipality.this,"No application can open web page",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
