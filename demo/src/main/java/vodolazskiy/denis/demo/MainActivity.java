package vodolazskiy.denis.demo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import vodolazskiy.denis.customviews.FunnyProgressDialog;
import vodolazskiy.denis.demo.interfaces.IProgress;

public class MainActivity extends AppCompatActivity implements IProgress {
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);

        findViewById(R.id.btnDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progressDialog != null) {
                    progressDialog.show();
                    progressDialog.setContentView(R.layout.progress_dialog);
                }
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void showAnimatedProgress(String message) {
        if (progressDialog != null) {
            progressDialog.show();
            progressDialog.setContentView(R.layout.progress_dialog);
            ((FunnyProgressDialog) progressDialog.findViewById(R.id.progressDialog)).setText(message);
        }
    }

    @Override
    public void showAnimatedLoadingProgress() {//default text "loading"
        if (progressDialog != null) {
            progressDialog.show();
            progressDialog.setContentView(R.layout.progress_dialog);
        }
    }


    @Override
    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.hide();
        }
    }

}
