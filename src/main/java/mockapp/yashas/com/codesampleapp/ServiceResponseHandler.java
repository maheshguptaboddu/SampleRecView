package mockapp.yashas.com.codesampleapp;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ServiceResponseHandler extends AsyncHttpResponseHandler {

    private AsyncHttpClient mHttpClient;

    private CallBack callBack;

    private String mUrl;


    public ServiceResponseHandler(String pUrl, CallBack pCallBack) {
        setCallBack(pCallBack);
        setmUrl(pUrl);
        setmHttpClient(new AsyncHttpClient());
    }

    public AsyncHttpClient getmHttpClient() {
        return mHttpClient;
    }

    public void setmHttpClient(AsyncHttpClient mHttpClient) {
        this.mHttpClient = mHttpClient;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public enum REQUEST_TYPE {
        GET,
        POST
    }

    public CallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    public interface CallBack {
        /**
         * @param jsonObject
         */
        void onSuccessResponse(JSONObject jsonObject);

        void onFailure(String error);

        void onServiceStart();

        void onServicCompleted();
    }


    public void makeRequest(REQUEST_TYPE requestType) {
        if (requestType == REQUEST_TYPE.GET) {
            makeGetRequest();
        } else {
            makePostRequest();
        }
    }


    public void makeGetRequest() {
        getmHttpClient().get(getmUrl(), this);
    }


    public void makePostRequest() {

    }

    @Override
    public void onStart() {
        if (getCallBack() != null) {
            getCallBack().onServiceStart();
        }
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        if (getCallBack() != null) {
            try {
                JSONObject jsonObject = new JSONObject(new String(responseBody));
                getCallBack().onSuccessResponse(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
                onFailure(statusCode, headers, responseBody, e);
                return;
            }
            getCallBack().onServicCompleted();
        }
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        if (getCallBack() != null) {

            getCallBack().onServicCompleted();
        }
    }

}
