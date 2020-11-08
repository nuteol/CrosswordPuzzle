package com.example.crossword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.LogRecord;

public class QueryActivity extends AppCompatActivity implements RequestOperator.RequestOperatorListener {

    Button sendRequestButton;
    TextView title;
    TextView bodyText;
    TextView jsonText;
    private IndicatingView indicator;
    private ModelPost publication;
    LoadingDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        sendRequestButton = (Button) findViewById(R.id.send_request);
        sendRequestButton.setOnClickListener(requestButtonClicked);

        title = (TextView) findViewById(R.id.title);
        bodyText = (TextView) findViewById(R.id.body_text);
        jsonText = (TextView) findViewById(R.id.json_elements);
        loading = new LoadingDialog(this);
        indicator = (IndicatingView)findViewById(R.id.generated_graphic);
    }

    public void setIndicatorStatus(final int status)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                indicator.setState(status);
                indicator.invalidate();
            }
        });
    }

    View.OnClickListener requestButtonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            sendRequest();
        }
    };
    private void sendRequest(){
        RequestOperator ro = new RequestOperator();
        ro.setListener(this);
        setIndicatorStatus(IndicatingView.LOADING);


        loading.startLoadingAnimation();
        ro.start();

    }

    public void updatePublication(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(publication!=null){
                    title.setText(publication.getTitle());
                    bodyText.setText(publication.getBodyText());
                    jsonText.setText("Number of items in JSON: ");
                    jsonText.append(String.valueOf(publication.getJSONObject().length()));
                } else {
                    title.setText("");
                    bodyText.setText("");
                }
            }
        });
    }

    @Override
    public void success(ModelPost publication) {
        this.publication = publication;
        updatePublication();

        loading.dismissLoading();
        setIndicatorStatus(IndicatingView.SUCCESS);
    }

    @Override
    public void failed(int responseCode) {
        this.publication = null;
        updatePublication();
        loading.dismissLoading();
        setIndicatorStatus(IndicatingView.FAILED);
    }
}