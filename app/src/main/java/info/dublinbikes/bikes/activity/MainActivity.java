package info.dublinbikes.bikes.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import info.dublinbikes.bikes.R;


/*************************************************************
**
**************************************************************
**Student ID: 10381791
**Assignment : Android
**Student Name : Ananth Bharadwaj(Msc.Information systems with computing
**
*************************************************************/
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSimpleTabs:
                startActivity(new Intent(MainActivity.this, SimpleTabsActivity.class));
                break;

        }
    }
}
