package chris.sullivan.base.util;

import android.content.Context;
import android.util.Log;

import java.io.File;

/**
 * Created by chris on 5/6/15.
 */
public class CacheUtil {

    private static Context mContext;
    private static File cacheDir;

    // This doesn't need to be a singleton for most cases, however if two locations of the app
    // are reading and writing to the same location it may be better to be one as to not create
    // any errors with the saved data
    public static CacheUtil getInstance( Context context )
    {
        if( mContext == null || mContext != context )
        {
            mContext = context;
            cacheDir = mContext.getCacheDir();
        }

        if( cache == null ) cache = new CacheUtil( context );
        return cache;
    }

    private static CacheUtil cache;
    private CacheUtil( Context context )
    {
        // singleton
        cacheDir = mContext.getCacheDir();
    }

    public boolean clearCache()
    {
        return clearCache(cacheDir);
    }

    private boolean clearCache( File file )
    {
        if( file.isDirectory() )
        {
            File[] children = file.listFiles();

            // delete recursive children first
            for( File child : children )
            {
                clearCache(child);
            }

            // delete parent
            return file.delete();
        }
        else
        {
            // delete regular file
            return file.delete();
        }
    }

    public boolean contains( String name )
    {
        return contains( cacheDir, name );
    }

    private boolean contains( File file, String name )
    {
        if( file.isDirectory() )
        {
            File[] children = file.listFiles();
            for( File child : children )
            {
                if( contains( child, name ) )
                {
                    return true;
                }
            }
        }

        return file.getName().equalsIgnoreCase( name );
    }


    /** DEBUGGING USE **/
    public void dump()
    {
        dump( cacheDir );
    }

    private void dump( File file )
    {
        if( file.isDirectory() )
        {
            Log.d( "Directory", file.getAbsolutePath() );
            File[] children = file.listFiles();

            // dump date for children
            for( File child : children )
            {
                dump( child );
            }
        }
        else
        {
            Log.d( "File", file.getAbsolutePath() );
        }
    }

}
