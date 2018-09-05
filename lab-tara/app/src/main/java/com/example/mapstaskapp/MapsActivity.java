package com.example.mapstaskapp;

import android.support.v4.app.Fragment;

public class MapsActivity extends SingleFragmentActivity {
    @Override
    public Fragment getFragment() {
        return new MapsFragment();
    }
}
