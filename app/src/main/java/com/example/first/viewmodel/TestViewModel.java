package com.example.first.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TestViewModel extends ViewModel {

    private MutableLiveData<Integer> liveData = new MutableLiveData<>(5);

    public LiveData<Integer> getLiveData() {
        return liveData;
    }

    public void addValue() {
        int value = liveData.getValue();
        liveData.setValue(value + 1);
    }

    public void decreaseValue() {
        int value = liveData.getValue();
        if (value > 0) {
            value--;
            liveData.setValue(value);
        }
    }
}
