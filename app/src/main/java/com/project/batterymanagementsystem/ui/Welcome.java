/*

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Welcome{

    private static final String TAG = makeLogTag(WelcomeActivity.class);

    @Override
    protected void Create(@Nullable Bundle savedInstanceState) {
        super.Create(savedInstanceState);

        ContentView(R.layout.activity_welcome);

        WelcomeActivityContent currentFragment = getCurrentFragment(this);

        
        if (currentFragment == null) { //no fragment just finish
            finish();
        }

        // Wire up the fragment
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.welcome_content, (Fragment) currentFragment);
        fragmentTransaction.commit();

        LogUtils.logD(TAG, "Inside Create View.");
    }

    // if it display welcome 
    public static boolean shouldDisplay(Context context) {
        WelcomeActivityContent fragment = getCurrentFragment(context);
        return fragment != null;
    }

    //true or false for welcome content
    public interface WelcomeActivityContent {
        
        boolean shouldDisplay(Context context);
    }
}
*/