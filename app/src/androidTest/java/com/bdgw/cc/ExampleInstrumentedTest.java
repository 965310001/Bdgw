package com.bdgw.cc;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

//        assertEquals("com.bdgw.cc", appContext.getPackageName());



        //第一种写法
//        RetrofitClient.getInstance().getApiService().login("戒酒的李白s", "123456")
//                .compose(RxUtils.exceptionTransformer())
//                .compose(RxUtils.schedulersTransformer())
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                    }
//                }).subscribe(new Consumer<BaseResponse<Result>>() {
//            @Override
//            public void accept(BaseResponse<Result> response) throws Exception {
////                KLog.i(response.getData().getUsername());
//                assertEquals("com.bdgw.cc", response.getData().getUsername());
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//                KLog.i(throwable.getMessage());
//            }
//        }, new Action() {
//            @Override
//            public void run() throws Exception {
//
//            }
//        });

//        RetrofitClient.getInstance().getApiService().login("戒酒的李白s", "123456")
//                .compose(RxSchedulers.<BaseResponse<Result>>io_main())
//                .subscribe(new Subscriber<BaseResponse<Result>>() {
//                    @Override
//                    public void onSubscribe(Subscription s) {
//                        KLog.i("onComplete" + s.toString());
//                    }
//
//                    @Override
//                    public void onNext(BaseResponse<Result> result) {
//                        KLog.i(result.getData().getUsername());
//                        KLog.i("----------------------");
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        KLog.i(t.toString());
//                        KLog.i("--------Throwable--------------");
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        KLog.i("onComplete");
//                    }
//                });
    }
}
