package zls.app.library.sample.navbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.LinkedList;
import java.util.List;

import zls.app.library.sample.R;
import zls.app.library.view.NavBar;

public class NavBarDemo extends AppCompatActivity {

    private NavBar navBar;
    private float ration = 0;
    private int index = 0;
    private int prePosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar_demo);

        navBar = (NavBar) findViewById(R.id.navBar);
        navBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ration < 0.8 && index < 3){
                    ration += 0.2;
                }else if(index < 3){
                    ration = 0;
                    index += 1;
                }else{
                    ration = 0;
                    index = 0;
                }
                navBar.setCurrentIndex(index);
                navBar.onScroll(ration);
            }
        });
        navBar.setNavText(new String[]{"MESSI", "C.R", "ROBBEN", "KAKA"});

        /*FragmentTransaction ft = this.getFragmentManager().beginTransaction();
        List<BlankFragment> list = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            Bundle bundle = new Bundle();
            bundle.putString("text", "fragment " + i);
            BlankFragment fragment = new BlankFragment();
            fragment.setArguments(bundle);
            list.add(fragment);
            ft.add(R.id.container, fragment);
            ft.hide(fragment);
        }
        ft.show(list.get(0));
        ft.commit();*/

        final List<BlankFragment> list = new LinkedList<>();
        for (int i = 0; i < 4; i++) {
            Bundle bundle = new Bundle();
            bundle.putString("text", "fragment " + i);
            BlankFragment fragment = new BlankFragment();
            fragment.setArguments(bundle);
            list.add(fragment);
        }

        final ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        final FragmentPagerAdapter adapter = new FragmentPagerAdapter(this.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        };
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                navBar.setCurrentIndex(position);
                if(prePosition > position){
                    positionOffset = 0 - positionOffset;
                }
                navBar.onScroll(positionOffset);
                prePosition = position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        navBar.setOnNavBarItemClickListener(new NavBar.OnNavBarItemClickListener() {
            @Override
            public void onItemClick(int position) {
                viewPager.setCurrentItem(position, false);
            }
        });
    }
}
