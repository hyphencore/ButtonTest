package com.example.buttontest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.textview);
        Button bt = findViewById(R.id.button);
        bt.setOnClickListener(new buttonOnClickListener());

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = findViewById(R.id.textview);
                tv.setText(Integer.toString(++count));
                if (count > 10) {
                    Toast.makeText(getApplicationContext(), "そんなクリックすんなて・・・///", Toast.LENGTH_LONG).show();
                }
            }
        });

        bt.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                ScaleAnimation scale = new ScaleAnimation(
                        0.5f,
                        10.0f,
                        0.5f,
                        10.0f,
                        view.getWidth()/2,
                        view.getHeight()/2
                );
                scale.setDuration(1000);
                scale.setInterpolator(new CycleInterpolator(1.0f));
                view.startAnimation(scale);
                return true;
            }
        });

        Toast.makeText(getApplicationContext(), "起動しました", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "Pause...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "Stop...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Destory...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "Resume...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "Restart...", Toast.LENGTH_SHORT).show();
    }

    class buttonOnClickListener implements View.OnClickListener {
        public void onClick(View view) {
            TextView tv = findViewById(R.id.textview);
            tv.setText(Integer.toString(++count));
        }
    }
}