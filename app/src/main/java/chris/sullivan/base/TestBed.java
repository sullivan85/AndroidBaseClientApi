package chris.sullivan.base;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import chris.sullivan.base.api.SpikeListener;
import chris.sullivan.base.api.VolleyApi;
import chris.sullivan.base.util.CacheUtil;
import chris.sullivan.base.util.WebUtils;


public class TestBed extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_bed);

        final WebView wv = (WebView) findViewById(R.id.text);
        WebUtils.enableUrlLoading( wv );
        WebUtils.enableJavascript( wv );


        final String url = "https://www.google.com/";

        VolleyApi.getInstance()._getUrl(getBaseContext(), url, new SpikeListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

            @Override
            public void onResponse(String response) {
                String encoded = WebUtils.encodeForWebView(response);
                wv.loadData(encoded, "text/html; charset=UTF-8", "base64");
                Toast.makeText(getBaseContext(), response, Toast.LENGTH_LONG).show();
            }
        });

        boolean cacheHasFile = CacheUtil.getInstance(getBaseContext()).contains("cache");

        Toast.makeText( getBaseContext(), cacheHasFile + "", Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test_bed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
