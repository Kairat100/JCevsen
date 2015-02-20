package performance.kz.jamaat_cevsen.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

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
        if (id == R.id.action_goToBegging)
        {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(R.string.title_goToBeggining)
                    .setMessage(R.string.message_goToBeggining)
                    .setPositiveButton(R.string.button_yes, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            pager.setCurrentItem(0);
                        }
                    })
                    .setNegativeButton(R.string.button_no, null)
                    .show();
        }

        if (id == R.id.action_goToBab)
        {
            final EditText input = new EditText(this);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setTitle(R.string.title_goToBab)
                    .setMessage(R.string.message_goToBab)
                    .setView(input)
                    .setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                int count = Integer.parseInt(input.getText().toString());
                                if(0 > count || count > 100)
                                {
                                    WrongNumberofBabDialog();
                                }
                                pager.setCurrentItem((count-1)/2);
                            } catch (NumberFormatException nfe) {
                                System.out.println("Could not parse " + nfe);
                            }
                        }
                    })
                    .setNegativeButton(R.string.button_cancel, null)
                    .show();
        }

        if (id == R.id.action_goToDua)
        {
            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(R.string.title_goToDua)
                    .setMessage(R.string.message_goToDua)
                    .setPositiveButton(R.string.button_yes, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            pager.setCurrentItem(50);
                        }
                    })
                    .setNegativeButton(R.string.button_no, null)
                    .show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void WrongNumberofBabDialog()
    {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.title_wrongNBab)
                .setMessage(R.string.message_wrongNBab)
                .setPositiveButton(R.string.button_ok, null)
                .show();
    }
}
