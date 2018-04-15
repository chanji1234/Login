package mg.studio.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LifeCycle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
    }
}
