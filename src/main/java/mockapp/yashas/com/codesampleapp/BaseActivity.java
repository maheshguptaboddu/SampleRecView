package mockapp.yashas.com.codesampleapp;


import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    protected void showProgressDialog(String message) {
        if (this.mProgressDialog == null) {
            this.mProgressDialog = new ProgressDialog(this);
            this.mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            this.mProgressDialog.setCancelable(false);
        }
        this.mProgressDialog.setTitle(message);
        this.mProgressDialog.show();
    }

    protected void dismissProgressDialog() {
        if (this.mProgressDialog != null && this.mProgressDialog.isShowing()) {
            this.mProgressDialog.dismiss();
        }
    }

}
