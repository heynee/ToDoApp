package tpilet.silver.com.swipeviews;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import static android.R.attr.onClick;

public class MainActivity extends FragmentActivity {
    ViewPager viewPager;
    FragmentPagerAdapter ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.pager);
        ft = new FragmentPageAdapter(getSupportFragmentManager());

        viewPager.setAdapter(ft);
    }
}
