package com.mounacheikhna.easywear;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEventBuffer;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by cheikhnamouna on 11/5/15.
 */
//#FBN
public class EasyWear implements
    DataApi.DataListener {
  //TODO: get rid of these listeners and make them rx-y

  private Context mContext; //Not at all sure to have this here !!
  @Override public void onConnected(Bundle bundle) {

  }

  @Override public void onConnectionSuspended(int i) {

  }

  @Override public void onDataChanged(DataEventBuffer dataEventBuffer) {

  }

  @Override public void onConnectionFailed(ConnectionResult connectionResult) {

  }

  protected GoogleApiClient createApiClient(Subscriber<? super T> subscriber) {

    ApiClientConnectionCallbacks apiClientConnectionCallbacks = new ApiClientConnectionCallbacks(subscriber);

    GoogleApiClient.Builder apiClientBuilder = new GoogleApiClient.Builder(mContext);


    for (Api<? extends Api.ApiOptions.NotRequiredOptions> service : services) {
      apiClientBuilder.addApi(service);
    }

    apiClientBuilder.addConnectionCallbacks(apiClientConnectionCallbacks);
    apiClientBuilder.addOnConnectionFailedListener(apiClientConnectionCallbacks);

    GoogleApiClient apiClient = apiClientBuilder.build();

    apiClientConnectionCallbacks.setClient(apiClient);

    return apiClient;
  }

  private class ApiClientConnectionCallbacks implements
      GoogleApiClient.ConnectionCallbacks,
      GoogleApiClient.OnConnectionFailedListener {

    final private Observer<? super T> observer;

    private GoogleApiClient apiClient;

    private ApiClientConnectionCallbacks(Observer<? super T> observer) {
      this.observer = observer;
    }

    @Override
    public void onConnected(Bundle bundle) {
      try {
        onGoogleApiClientReady(apiClient, observer);
      } catch (Throwable ex) {
        observer.onError(ex);
      }
    }

    @Override
    public void onConnectionSuspended(int cause) {
      //observer.onError(new GoogleAPIConnectionSuspendedException(cause));
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
      //observer.onError(new GoogleAPIConnectionException("Error connecting to GoogleApiClient.", connectionResult));
    }

    public void setClient(GoogleApiClient client) {
      this.apiClient = client;
    }
  }

  protected void onGoogleApiClientReady(GoogleApiClient apiClient, Observer<? super T> observer){

  }


}
