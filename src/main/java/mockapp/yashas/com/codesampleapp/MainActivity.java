package mockapp.yashas.com.codesampleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends BaseActivity implements ServiceResponseHandler.CallBack {


    private RecyclerView currentRecyclerView;

    private ServerResponse serverResponse;

    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        startView();
    }

    private void startView() {
        currentRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        currentRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        currentRecyclerView.setLayoutManager(mLayoutManager);
        currentRecyclerView.addItemDecoration(
                new SimpleDividerItemDecoration(MainActivity.this));
        if (this.serverResponse == null) {
            new ServiceResponseHandler("https://itunes.apple.com/rss/customerreviews/id=529479190/json", this).makeGetRequest();
        } else {
            updateUI();
        }

    }


    private void updateUI() {
        RecyclerAd ad = new RecyclerAd(this.serverResponse.entries);
        currentRecyclerView.setAdapter(ad);
    }


    @Override
    public void onSuccessResponse(JSONObject jsonObject) {
        serverResponse = new ServerResponse(jsonObject);
        serverResponse.parse(jsonObject);
        updateUI();
    }

    @Override
    public void onFailure(String error) {

    }

    @Override
    public void onServiceStart() {
        showProgressDialog("Please Wait...");
    }

    @Override
    public void onServicCompleted() {
        dismissProgressDialog();
    }

}
