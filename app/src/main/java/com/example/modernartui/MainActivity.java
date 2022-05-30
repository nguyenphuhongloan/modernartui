package com.example.modernartui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    static private final String URL = "http://www.mona.org";
    SeekBar sb = null;
    TextView c1_1, c1_2, c2_1, c3_1, c3_2, c4_1, c4_2, c5_1, c5_2;
    int[][] color = new int[5][3];
    int[][] colorChanged = new int[5][3];
    int[][] newColor = new int[5][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sb = (SeekBar) findViewById(R.id.slider);

        c1_1 = (TextView) findViewById(R.id.color1_1);
        c1_2 = (TextView) findViewById(R.id.color1_2);
        c2_1 = (TextView) findViewById(R.id.color2_1);
        c3_1 = (TextView) findViewById(R.id.color3_1);
        c3_2 = (TextView) findViewById(R.id.color3_2);
        c4_1 = (TextView) findViewById(R.id.color4_1);
        c4_2 = (TextView) findViewById(R.id.color4_2);
        c5_1 = (TextView) findViewById(R.id.color5_1);
        c5_2 = (TextView) findViewById(R.id.color5_2);

        color[0] = new int[]{198, 102, 104};
        color[1] = new int[]{226, 166, 156};
        color[2] = new int[]{200, 214, 208};
        color[3] = new int[]{70, 139, 151};
        color[4] = new int[]{31, 44, 80};

        colorChanged[0] = new int[]{223, 177, 133};
        colorChanged[1] = new int[]{177, 179, 193};
        colorChanged[2] = new int[]{167, 134, 155};
        colorChanged[3] = new int[]{104, 84, 100};
        colorChanged[4] = new int[]{191, 96, 90};

        //khi người dùng thay đổi giá trị của slider
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int process, boolean b) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 3; j++) {
                        int perChange = Math.abs(color[i][j]-colorChanged[i][j]);
                        int change = (perChange  * process) / 100;
                        newColor[i][j] = color[i][j] < colorChanged[i][j] ? color[i][j] + change : color[i][j] - change;

                    }
                }

                //set màu mới hiển thị lên
                c1_1.setBackgroundColor(Color.rgb(newColor[0][0], newColor[0][1], newColor[0][2]));
                c1_2.setBackgroundColor(Color.rgb(newColor[0][0], newColor[0][1], newColor[0][2]));
                c2_1.setBackgroundColor(Color.rgb(newColor[1][0], newColor[1][1], newColor[1][2]));
                c3_1.setBackgroundColor(Color.rgb(newColor[2][0], newColor[2][1], newColor[2][2]));
                c3_2.setBackgroundColor(Color.rgb(newColor[2][0], newColor[2][1], newColor[2][2]));
                c4_1.setBackgroundColor(Color.rgb(newColor[3][0], newColor[3][1], newColor[3][2]));
                c4_2.setBackgroundColor(Color.rgb(newColor[3][0], newColor[3][1], newColor[3][2]));
                c5_1.setBackgroundColor(Color.rgb(newColor[4][0], newColor[4][1], newColor[4][2]));
                c5_2.setBackgroundColor(Color.rgb(newColor[4][0], newColor[4][1], newColor[4][2]));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.showinfo:
                AlertDialog.Builder ad = new AlertDialog.Builder(this); // tạo dialog
                TextView title = new TextView(this);
                title.setSingleLine(false);
                title.setText("Insprired by the words of artists such as Piet Mondrian and Ben Nicholoson");
                //title.setGravity(Gravity.CENTER_HORIZONTAL);
                title.setPadding(100,30,100,30);
                title.setTextColor(Color.rgb(0,0,0));
                title.setTextSize(20);
                title.setPadding(60,20,60,0);
                ad.setCustomTitle(title);
                ad.setMessage("Click below to learn more");
                ad.setPositiveButton("Not Now", (dialog, id) -> {
                });
                ad.setNegativeButton("Visit Mona", (dialog, id) -> {
                    Intent momaIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL)); //action hiển thị web
                    startActivity(momaIntent);
                });
                ad.show();
        }
        return true;
    }
}