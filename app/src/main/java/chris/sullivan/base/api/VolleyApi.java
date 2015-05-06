package chris.sullivan.base.api;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by chris on 5/5/15.
 */
public class VolleyApi {

    public static class RequestType
    {
        public static final int String = 0;
        public static final int Json = 1;
    }

    public static VolleyApi getInstance()
    {
        if(api == null) api = new VolleyApi();
        return api;
    }

    private RequestQueue queue;

    private static VolleyApi api;
    private VolleyApi()
    {
        // singleton instance
    }

    private void _buildQueue( Context context )
    {
        if( queue == null )
        {
            // create our cache
            Cache cache = new DiskBasedCache( context.getCacheDir(), 1024 * 1024 ); // 1 MB

            // instantiate network
            Network network = new BasicNetwork( new HurlStack() );

            queue = new RequestQueue( cache, network );
        }

        // start the queue by default, checking for valid instance
        if( queue != null ) queue.start();
    }

    public void _deleteUrl( Context context, String url, SpikeListener handler )
    {
        _buildQueue( context );

        StringRequest request = new StringRequest(Request.Method.DELETE, url, handler, handler);
        queue.add( request );
    }

    public void _getUrl( Context context, String url, SpikeListener handler  )
    {
        _buildQueue( context );

        StringRequest request = new StringRequest(Request.Method.GET, url, handler, handler);
        queue.add( request );
    }

    public void _postUrl( Context context, String url, SpikeListener handler )
    {
        _buildQueue( context );

        StringRequest request = new StringRequest(Request.Method.POST, url, handler, handler);
        queue.add( request );
    }

    public void _putUrl( Context context, String url, SpikeListener handler )
    {
        _buildQueue( context );

        StringRequest request = new StringRequest(Request.Method.PUT, url, handler, handler);
        queue.add( request );
    }

}
