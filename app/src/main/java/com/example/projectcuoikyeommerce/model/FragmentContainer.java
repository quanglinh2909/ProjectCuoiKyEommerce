package com.example.projectcuoikyeommerce.model;

import androidx.fragment.app.Fragment;

public class FragmentContainer {
    private int current;
    private Fragment fragment;

    public FragmentContainer(int current, Fragment fragment) {
        this.current = current;
        this.fragment = fragment;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
