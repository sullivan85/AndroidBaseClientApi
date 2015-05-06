package chris.sullivan.base.api;

import com.android.volley.Response;

/**
 * Created by chris on 5/5/15.
 *
 * This is a convenience interface to consolidate both error and success listeners.
 * Using an unkown type is helpful in cases where we want JsonObjects back instead of Strings
 */
public interface SpikeListener extends Response.Listener<String>, Response.ErrorListener {

}
