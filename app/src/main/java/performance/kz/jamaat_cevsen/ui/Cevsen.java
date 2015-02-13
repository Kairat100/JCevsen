package performance.kz.jamaat_cevsen.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import performance.kz.jamaat_cevsen.R;
import performance.kz.jamaat_cevsen.code.SampleAdapter;

public class Cevsen extends Activity {

    private static final String SAVED_TEXT = "current_position";
    private SharedPreferences sPref;

    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cevsen);

        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(buildAdapter());
    }

    private PagerAdapter buildAdapter() {
        return(new SampleAdapter(this, getFragmentManager()));
    }

    @Override
    protected void onPause() {
        super.onPause();

        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putInt(SAVED_TEXT, pager.getCurrentItem());
        ed.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        sPref = getPreferences(MODE_PRIVATE);
        int current_position = sPref.getInt(SAVED_TEXT, 0);
        pager.setCurrentItem(current_position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cevsen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
