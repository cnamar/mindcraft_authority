package com.example.mindcraft_main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class tabular extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager pager;
    PagerAdapter adapter;
    Complaint complaint;
    detected detect;
    solved solved;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabular);
        pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setOffscreenPageLimit(3);
        setupViewPager(pager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition(),false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void setupViewPager(ViewPager viewPager)
    {
        adapter = new PagerAdapter(getSupportFragmentManager());
       complaint=new Complaint();
        solved=new solved();
        detect=new detected();
        adapter.addFragment(complaint,"CALLS");
        adapter.addFragment(solved,"CHAT");
        adapter.addFragment(detect,"CONTACTS");
        viewPager.setAdapter(adapter);
    }
}