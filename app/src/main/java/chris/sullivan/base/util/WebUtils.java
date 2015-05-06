package chris.sullivan.base.util;

import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;

/**
 * Created by chris on 5/6/15.
 */
public class WebUtils {

    public static void enableUrlLoading( WebView wv )
    {
        wv.setWebViewClient( new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
    }

    public static void enableJavascript( WebView wv )
    {
        wv.getSettings().setJavaScriptEnabled( true );
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically( true );
    }

    public static String encodeForWebView( String response )
    {
        try
        {
            return android.util.Base64.encodeToString(response.getBytes("UTF-8"), android.util.Base64.DEFAULT);
        }
        catch( UnsupportedEncodingException e )
        {
            Log.d( "EncodeForWeb", response);
        }
        return null;
    }

}
