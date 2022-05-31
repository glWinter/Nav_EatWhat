
package com.winter.nav_eatwhat.domain.message;

import androidx.lifecycle.ViewModel;

import com.kunminx.architecture.ui.callback.ProtectedUnPeekLiveData;
import com.kunminx.architecture.ui.callback.UnPeekLiveData;

public class SharedViewModel extends ViewModel {
    private final UnPeekLiveData<Boolean> toHideBottomView = new UnPeekLiveData<>();

    public ProtectedUnPeekLiveData<Boolean> isHideBottomView() {
        return toHideBottomView;
    }

    public void requestHideBottomView(boolean allow) {
        toHideBottomView.setValue(allow);
    }
}
