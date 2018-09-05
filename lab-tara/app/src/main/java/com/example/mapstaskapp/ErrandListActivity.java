package com.example.mapstaskapp;

import android.support.v4.app.Fragment;

public class ErrandListActivity extends SingleFragmentActivity {
    @Override
    public Fragment getFragment() {
        return new ErrandListFragment();
    }
}
