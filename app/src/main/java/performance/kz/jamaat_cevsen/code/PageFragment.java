package performance.kz.jamaat_cevsen.code;

/**
 * Created by kairat.aitpayev on 2/11/2015.
 */
import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Array;

import performance.kz.jamaat_cevsen.R;

import static android.widget.RelativeLayout.LayoutParams;

public class PageFragment extends Fragment implements View.OnClickListener
{
    private static final String KEY_POSITION = "position";
    private int position = 0;

    private static final int[] pages = {R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4, R.drawable.p5};

    // First - marginTop, second - height of 1st bab, third - height of 2nd bab
    private static final int[][] dimensions = {{142,180,250},{60,250,250},{55,170,340},{60,250,250},{95,170,260}};


    static PageFragment newInstance(int position)
    {
        PageFragment frag = new PageFragment();
        Bundle args = new Bundle();

        args.putInt(KEY_POSITION, position);
        frag.setArguments(args);

        return(frag);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View result = inflater.inflate(R.layout.page, container, false);

        ImageView page = (ImageView)result.findViewById(R.id.page);
        TextView pageNumber = (TextView) result.findViewById(R.id.pageNumber);
        Button buttonBab1 = (Button) result.findViewById(R.id.buttonBab1);
        Button buttonBab2 = (Button) result.findViewById(R.id.buttonBab2);

        position = getArguments().getInt(KEY_POSITION, 0);

        page.setImageResource(pages[position]);
        pageNumber.setText((position + 1) + "");

        buttonBab1.setOnClickListener(this);
        LayoutParams params1 = new LayoutParams(LayoutParams.MATCH_PARENT, dptopx(dimensions[position][1]));
        params1.setMargins(0, dptopx(dimensions[position][0]), 0, 0);
        buttonBab1.setLayoutParams(params1);

        buttonBab2.setOnClickListener(this);
        LayoutParams params2 = (LayoutParams) buttonBab2.getLayoutParams();
        params2.height = dptopx(dimensions[position][2]);
        buttonBab2.setLayoutParams(params2);

        return(result);
    }

    public void onClick(View v)
    {
        Log.d("DEBUG", "button clicked");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.menu_cevsen, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    //convert dip to pixels
    private int dptopx(int dp)
    {
        Resources r = getActivity().getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }
}
