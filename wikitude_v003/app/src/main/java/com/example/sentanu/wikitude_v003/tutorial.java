package com.example.sentanu.wikitude_v003;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class tutorial extends AppCompatActivity {
    ImageView btnHome, imgtutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        btnHome =(ImageView)findViewById(R.id.btn_home);
        imgtutor =(ImageView)findViewById(R.id.img_tutor);

        Bundle extras = getIntent().getExtras();
        int statTutor = extras.getInt("key_tutor");

        switch(statTutor) {
            case 0:
                imgtutor.setImageDrawable(getResources().getDrawable(R.mipmap.a1));
                break;
            case 1:
                imgtutor.setImageDrawable(getResources().getDrawable(R.mipmap.a2));
                break;
            case 2:
                imgtutor.setImageDrawable(getResources().getDrawable(R.mipmap.a3));
                break;
            case 3:
                imgtutor.setImageDrawable(getResources().getDrawable(R.mipmap.a4));
                break;
            case 4:
                imgtutor.setImageDrawable(getResources().getDrawable(R.mipmap.b1));
                break;
            case 5:
                imgtutor.setImageDrawable(getResources().getDrawable(R.mipmap.b2));
                break;
            case 6:
                imgtutor.setImageDrawable(getResources().getDrawable(R.mipmap.b3));
                break;
            default:
                imgtutor.setImageDrawable(getResources().getDrawable(R.mipmap.a1));
        }
        btnnext(statTutor);
    }


    public void btnnext(final int x){
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                i = new Intent(tutorial.this, tutorial.class);
                switch(x) {
                    case 0:
                        i.putExtra("key_tutor", 1);
                        break;
                    case 1:
                        i.putExtra("key_tutor", 2);
                        break;
                    case 2:
                        i.putExtra("key_tutor", 3);
                        break;
                    case 3:
                        i.putExtra("key_tutor", 4);
                        break;
                    case 4:
                        i.putExtra("key_tutor", 5);
                        break;
                    case 5:
                        i.putExtra("key_tutor", 6);
                        break;
                    case 6:
                        i = new Intent(tutorial.this, MapsActivity.class);
                        break;
                    default:
                        i.putExtra("key_tutor", 0);
                }
                startActivity(i);
            }
        });
    }
}
