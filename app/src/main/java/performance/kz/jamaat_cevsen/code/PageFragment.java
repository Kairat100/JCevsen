package performance.kz.jamaat_cevsen.code;

/**
 * Created by kairat.aitpayev on 2/11/2015.
 */
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;

import performance.kz.jamaat_cevsen.R;

public class PageFragment extends Fragment
{
    private static final String KEY_POSITION="position";
    private int position=-1;

    private static final int[] pages = {R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5};

    static PageFragment newInstance(int position)
    {
        PageFragment frag=new PageFragment();
        Bundle args=new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

    static String getTitle(Context ctxt, int position)
    {
        return(String.format(ctxt.getString(R.string.hint), position + 1));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View result = inflater.inflate(R.layout.page, container, false);
        ImageView page = (ImageView)result.findViewById(R.id.page);
        TextView pageNumber = (TextView) result.findViewById(R.id.pageNumber);

        position = getArguments().getInt(KEY_POSITION, -1);

        page.setImageResource(pages[position]);
        pageNumber.setText((position + 1) + "");

        return(result);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.menu_cevsen, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
