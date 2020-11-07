package cn.yoouu.learn.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataBindingViewModel extends ViewModel {
    private MutableLiveData<Integer> LikedNumber;

//    DataBindingViewModel(){
//        LikedNumber = new MutableLiveData<>();
//        LikedNumber.setValue(0);
//    }

    public void setLikedNumber(MutableLiveData<Integer> likedNumber) {
        LikedNumber = likedNumber;
    }

    public MutableLiveData<Integer> getLikedNumber() {
        if(LikedNumber == null){
            LikedNumber = new MutableLiveData<>();
            LikedNumber.setValue(0);
        }
        return LikedNumber;
    }

    public void addNum(int n){
        LikedNumber.setValue(LikedNumber.getValue() + n);
    }
}
