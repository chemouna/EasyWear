package com.mounacheikhna.easywear;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

public class RxMessageApi {

  public static Observable<MessageEvent> create(final GoogleApiClient googleApiClient) {
    return Observable.create(new OnSubscribe<MessageEvent>() {
      @Override public void call(final Subscriber<? super MessageEvent> subscriber) {
        Wearable.MessageApi.addListener(googleApiClient, new MessageApi.MessageListener() {
          @Override public void onMessageReceived(MessageEvent messageEvent) {
            subscriber.onNext(messageEvent);
          }
        });
      }
    });
  }

  //TODO: maybe also encapsulate behind an observable what we get
  // out of Wearable.MessageApi.addListener ?

  //TODO: also handle unsubcribing (through some binding to lifecycle ?)

}

