package com.example.e_commercefuture.Revision;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.e_commercefuture.MyFragmentAdapterWomanClothes;
import com.example.e_commercefuture.R;
import com.google.android.material.tabs.TabLayout;

public class FragmentActivityWomanClothes extends AppCompatActivity {

    TabLayout tabLayout2;
    ViewPager viewPager2;

    MyFragmentAdapterWomanClothes adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_woman_clothes);

       // getSupportFragmentManager().beginTransaction().replace(R.id.maincontainer,new Dresses()).commit();

        intiview2();
        viewPager2.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout2));
        tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    protected void intiview2(){
        tabLayout2=findViewById(R.id.tablelayout2);
        viewPager2=findViewById(R.id.view_pager2);
        adapter2 = new MyFragmentAdapterWomanClothes(getSupportFragmentManager(),1,tabLayout2.getTabCount());

        viewPager2.setAdapter(adapter2);
    }
}