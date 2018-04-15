package mg.studio.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tvName;

    private SessionManager session;
    private static final String[] strs = new String[] {
            "first", "second", "third", "fourth", "fifth"};
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.user_name);


        /**
         * Only logged in users should access this activity
         */
        session = new SessionManager(getApplicationContext());
        if (!session.isLoggedIn()) {
            logout();
        }

        /**
         * If the user just registered an account from Register.class,
         * the parcelable should be retrieved
         */
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            // Retrieve the parcelable
            Feedback feedback = bundle.getParcelable("feedback");
            // Get the from the object
            String userName = feedback.getName();
            tvName.setText(userName);
        }

        lv = findViewById(R.id.lv);
        lv.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, strs));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent=new Intent(MainActivity.this, LifeCycle.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Logging out the user:
     * - Will set isLoggedIn flag to false in SharedPreferences
     * - Clears the user data from SqLite users table
     */

    public void btnLogout(View view) {
        logout();
    }

    public void logout() {
        // Updating the session
        session.setLogin(false);
        // Redirect the user to the login activity
        startActivity(new Intent(this, Login.class));
        finish();
    }
}