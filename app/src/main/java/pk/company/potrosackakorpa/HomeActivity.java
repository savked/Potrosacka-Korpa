package pk.company.potrosackakorpa;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initToolBar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_toolbar_menu, menu);
        return true;
    }

    public void initToolBar(){
        toolbar = (Toolbar) findViewById(R.id.homeToolBar);
        toolbar.setTitle(R.string.homeToolBarTitle);

        toolbar.inflateMenu(R.menu.home_toolbar_menu);

        setSupportActionBar(toolbar);
    }

    private boolean pressedTwice = false;

    @Override
    public void onBackPressed() {
        if(pressedTwice){
            super.onBackPressed();
            this.finishAffinity();
        }

        this.pressedTwice = true;
        Toast.makeText(this, "Pritisnite nazad dugme jos jednom da bi ste izasli", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pressedTwice = false;
            }
        }, 2000);
    }
}
