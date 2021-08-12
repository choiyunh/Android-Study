package org.study.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    BackgroundTask task;
    int value;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task = new BackgroundTask();
                task.execute();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task.cancel(true);
            }
        });
    }

    class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected void onPreExecute() {
            progressBar.setProgress(0);
        }

        @Override
        protected Integer doInBackground(Integer... values) {
            while (isCancelled() == false) {
                value++;
                if (value >= 100) {
                    break;
                } else {
                    publishProgress(value);
                }

                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                }
            }

            return value;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0].intValue());
        }

        @Override
        protected void onPostExecute(Integer result) {
            progressBar.setProgress(0);
        }

        @Override
        protected void onCancelled() {
            progressBar.setProgress(0);
        }
    }
}
