package com.example.android.restful;

import android.content.Context;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    //test
    TextView output;
    TextView output2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView) findViewById(R.id.output);
    }


    public void runClickHandler(View view) {
        getSupportLoaderManager().initLoader(0, null, this).forceLoad(); //restartLoader
    }

    public void restartClickHandler(View view) {
        getSupportLoaderManager().restartLoader(0, null, this); //restartLoader
    }

    public void clearClickHandler(View view) {
        output.setText("");
        getSupportLoaderManager().destroyLoader(0);
    }

    private static  class MyAsyncTaskLoader extends AsyncTaskLoader<String> {

        public MyAsyncTaskLoader(Context context) {
            super(context);
        }

        @Override
        public String loadInBackground() {
            String s = "string from loadInBackground goes to  onLoadFinished \n";
            s += "works single time on Loader creation \n";
            return s;
        }

        @Override
        public void deliverResult(String data) {
            data += data + "works everytime";
            super.deliverResult(data);
        }
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        output.append("onCreateLoader \n");
        return new MyAsyncTaskLoader(this);

    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        output.append("onLoadFinished \n data:  "+data);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
        output.append("onLoaderReset \n");
    }



    {

//    private class MyAsyncTask extends AsyncTask<String,String,Void> {
//        @Override
//        protected Void doInBackground(String... strings) {
//            for (String s: strings) {
//                publishProgress(s);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            output.append("onPostExecute"+"\n");
//        }
//
//        @Override
//        protected void onProgressUpdate(String... values) {
//            super.onProgressUpdate(values);
//
//            output.append(values[0]+"\n");
//        }
//    }
    }
}
