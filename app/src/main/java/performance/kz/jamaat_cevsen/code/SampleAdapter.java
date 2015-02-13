package performance.kz.jamaat_cevsen.code;

/**
 * Created by kairat.aitpayev on 2/11/2015.
 */
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;

public class SampleAdapter extends FragmentPagerAdapter
{

    public SampleAdapter(Context context, FragmentManager mgr)
    {
        super(mgr);
    }

    @Override
    public int getCount()
    {
        return(5);
    }

    @Override
    public Fragment getItem(int position)
    {
        return(PageFragment.newInstance(position));
    }
}