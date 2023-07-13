package com.example.e_commercefuture;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.e_commercefuture.Revision.Dresses;
import com.example.e_commercefuture.Revision.JacketWoman;
import com.example.e_commercefuture.Revision.ShoesWoman;
import com.example.e_commercefuture.Revision.sweatshirtswomen;

public class MyFragmentAdapterWomanClothes extends FragmentPagerAdapter {
    private int tabCount2;
    public MyFragmentAdapterWomanClothes(@NonNull FragmentManager fm, int behavior , int tabCount2) {
        super(fm, behavior);
        this.tabCount2=tabCount2;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Dresses();
            case 1:
                return new JacketWoman();
            case 2:
                return new sweatshirtswomen();
            case 3:
                return new ShoesWoman();
        }

        return null;
    }


    @Override
    public int getCount() {
        return tabCount2;
    }
}
