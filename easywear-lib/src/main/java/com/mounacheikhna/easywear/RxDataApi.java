package com.mounacheikhna.easywear;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.Wearable;
import rx.Observable;
import rx.Subscriber;

public class RxDataApi {

  //TODO: maybe use an abstraction on top of DataEventBuffer ?
  public static Observable<DataEventBuffer> create(final GoogleApiClient googleApiClient) {

    return Observable.create(new Observable.OnSubscribe<DataEventBuffer>() {
      @Override public void call(final Subscriber<? super DataEventBuffer> subscriber) {
        Wearable.DataApi.addListener(googleApiClient, new DataApi.DataListener() {
          @Override public void onDataChanged(DataEventBuffer dataEventBuffer) {
            subscriber.onNext(dataEventBuffer);
          }
        });
      }
    });
  }


}
