package com.bdgw.cc;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.socks.library.KLog;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.goldze.common.base.mvvm.base.test.BaseActivity;
import me.goldze.common.constants.ARouterConfig;
import me.goldze.common.utils.ActivityToActivity;

/**
 * 过渡界面
 */
@Route(path = ARouterConfig.SPLASHACTIVITY)
public class SplashActivity extends BaseActivity {

    @BindView(R.id.tv_time)
    TextView tvTime;

    private Disposable timer;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        showSuccess();

        final long count = 3;
        timer = Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (aLong <= count && !timer.isDisposed()) {
                            KLog.i("" + aLong);
                            tvTime.setText((count - aLong) + "秒");
                        } else {
                            ActivityToActivity.toActivity(ARouterConfig.MAINACTIVITY);
                            finish();
                        }
                    }

                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != timer && !timer.isDisposed()) {
            timer.dispose();
            timer = null;
        }
    }

    @Override
    protected boolean isActionBar() {
        return false;
    }
}
