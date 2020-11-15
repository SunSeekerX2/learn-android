package cn.yoouu.learn.demo.score;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class ScoreViewModel extends ViewModel {
  //    private MutableLiveData<Integer> aTeamScore;
//    private MutableLiveData<Integer> bTeamScore;
  private SavedStateHandle handle;
  final static String ATEAMSCOREKEY = "ATEAMSCOREKEY";
  final static String BTEAMSCOREKEY = "BTEAMSCOREKEY";
  int aBack, bBack;

  /**
   * 文档说 handle 不会为空，只需要判断是否包含我们需要的数据
   *
   * @param handle
   */
  public ScoreViewModel(SavedStateHandle handle) {
    this.handle = handle;
  }

  public MutableLiveData<Integer> getATeamScore() {
    if (!handle.contains(ATEAMSCOREKEY)) {
      handle.set(ATEAMSCOREKEY, 0);
    }
    return handle.getLiveData(ATEAMSCOREKEY);
//        if(aTeamScore == null){
//            aTeamScore = new MutableLiveData<>();
//            aTeamScore.setValue(0);
//        }
//        return aTeamScore;
  }

  public MutableLiveData<Integer> getBTeamScore() {
    if (!handle.contains(BTEAMSCOREKEY)) {
      handle.set(BTEAMSCOREKEY, 0);
    }
    return handle.getLiveData(BTEAMSCOREKEY);
//        if(bTeamScore == null){
//            bTeamScore = new MutableLiveData<>();
//            bTeamScore.setValue(0);
//        }
//        return bTeamScore;
  }

  public void aTeamAdd(int p) {
    aBack = getATeamScore().getValue();
    bBack = getBTeamScore().getValue();
    getATeamScore().setValue(getATeamScore().getValue() + p);
//        aTeamScore.setValue(aTeamScore.getValue() + p);

  }

  public void bTeamAdd(int p) {
    aBack = getATeamScore().getValue();
    bBack = getBTeamScore().getValue();
    getBTeamScore().setValue(getBTeamScore().getValue() + p);
//        bTeamScore.setValue(bTeamScore.getValue() + p);
  }

  public void reset() {
    aBack = getATeamScore().getValue();
    bBack = getBTeamScore().getValue();
    getATeamScore().setValue(0);
    getBTeamScore().setValue(0);
  }

  public void undo() {
    getATeamScore().setValue(aBack);
    getBTeamScore().setValue(bBack);
  }
}
