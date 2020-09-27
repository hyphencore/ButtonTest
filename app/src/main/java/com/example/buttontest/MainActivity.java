package com.example.buttontest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.RadialGradient;
import android.graphics.SweepGradient;
import android.os.Bundle;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int count = 0;
    // 画面リソースの宣言はClass内でよい
    TextView tv, tv2, tv3, tv4;
    Button bt;
    CheckBox cb1, cb2, cb3;
    RadioGroup rg1;
    ToggleButton tb1;
    SeekBar sb1, sb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        onCheckChangedCheckbox cbListner = new onCheckChangedCheckbox();
        onSeekBarChange sbListner = new onSeekBarChange();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // onCreateでリソースを紐づけすればよい。他のハンドラでは不要
        tv = findViewById(R.id.textview);
        tv2 = findViewById(R.id.textview2);
        tv3 = findViewById(R.id.textview3);
        tv4 = findViewById(R.id.textview4);
        bt = findViewById(R.id.button);
        cb1 = findViewById(R.id.checkBox);
        cb2 = findViewById(R.id.checkBox2);
        cb3 = findViewById(R.id.checkBox3);
        rg1 = findViewById(R.id.radioGroup);
        tb1 = findViewById(R.id.toggleButton);
        sb1 = findViewById(R.id.seekBar);
        sb2 = findViewById(R.id.seekBar2);

        bt.setOnClickListener(new buttonOnClickListener());

        // 無名リスナー。クリック時の処理
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setText(Integer.toString(++count));
                if (count > 10) {
                    Toast.makeText(getApplicationContext(), "そんなクリックすんなて・・・///", Toast.LENGTH_LONG).show();
                }
            }
        });

        // 無名リスナー。長押し時の処理
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

        // check box のリスナーセット
        cb1.setOnCheckedChangeListener(cbListner);
        cb2.setOnCheckedChangeListener(cbListner);
        cb3.setOnCheckedChangeListener(cbListner);

        // radio groupのリスナーセット
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup rg, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton:
                        tv2.setText("rb1 checked");
                        break;
                    case R.id.radioButton2:
                        tv2.setText("rb2 checked");
                        break;
                    case R.id.radioButton3:
                        tv2.setText("rb3 checked");
                }
            }
        });

        // toggle button のリスナー
        tb1.setOnClickListener(new CompoundButton.OnClickListener() {
            public void onClick(View view) {
                if (tb1.isChecked()) {
                    Toast.makeText(getApplicationContext(), "ON!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "OFF!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // seek bar listener
        sb1.setOnSeekBarChangeListener(sbListner);
        sb2.setOnSeekBarChangeListener(sbListner);

        // finish onCreate()
        Toast.makeText(getApplicationContext(), "起動しました", Toast.LENGTH_LONG).show();
    }

    // seek bar の共通リスナー
    class onSeekBarChange implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            TextView tv = null;

            if (seekBar == sb1) {
                tv = tv3;
            } else {
                tv = tv4;
            }

            tv.setText(Integer.toString(i) + " %");
        }

        // onTouch
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            TextView tv = null;

            if (seekBar == sb1) {
                tv = tv3;
            } else {
                tv = tv4;
            }

            tv.setText("ON!");
        }

        // releaseTouch
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            TextView tv = null;

            if (seekBar == sb1) {
                tv = tv3;
            } else {
                tv = tv4;
            }

            tv.setText("Release!");
        }
    }

    // check box の共通リスナ(interface)
    class onCheckChangedCheckbox implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton cb, boolean isChecked) {
            if (cb == cb1) {
                Toast.makeText(getApplicationContext(),
                        "checkBox1 changed: " + Boolean.toString(isChecked),
                        Toast.LENGTH_SHORT).show();
            }
            if (cb == cb2) {
                Toast.makeText(getApplicationContext(),
                        "checkBox2 changed: " + Boolean.toString(isChecked),
                        Toast.LENGTH_SHORT).show();
            }
            if (cb == cb3) {
                Toast.makeText(getApplicationContext(),
                        "checkBox3 changed: " + Boolean.toString(isChecked),
                        Toast.LENGTH_SHORT).show();
            }
        }
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

    // ボタン長押し時のイベントリスナ
    class buttonOnClickListener implements View.OnClickListener {
        public void onClick(View view) {
            tv.setText(Integer.toString(++count));
        }
    }
}