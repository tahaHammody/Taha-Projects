package com.example.finalproject;


    import android.app.Activity;
    import android.content.Intent;
    import android.os.Bundle;


    import android.view.View;
    import android.widget.Button;
    import android.widget.ImageView;
    import android.widget.TextView;

    public class WelcomeActivity extends Activity {

        private Button getting_started;
        private Button who_are_we_;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_welcome);

            getting_started = (Button) findViewById(R.id.button);
            who_are_we_ = (Button) findViewById(R.id.button2);

            getting_started.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(WelcomeActivity.this, login_activity.class);
                    startActivity(intent);
                }
            });

            who_are_we_.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(WelcomeActivity.this, info_activity.class);
                    startActivity(intent);
                }
            });

        }
    }
	
	