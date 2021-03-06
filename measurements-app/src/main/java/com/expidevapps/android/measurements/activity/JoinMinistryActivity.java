package com.expidevapps.android.measurements.activity;

import static com.expidevapps.android.measurements.Constants.EXTRA_GUID;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.expidevapps.android.measurements.R;
import com.expidevapps.android.measurements.service.GoogleAnalyticsManager;
import com.expidevapps.android.measurements.support.v4.fragment.FindMinistryFragment;
import com.expidevapps.android.measurements.support.v4.fragment.JoinMinistryDialogFragment;

public class JoinMinistryActivity extends AppCompatActivity
        implements JoinMinistryDialogFragment.OnJoinMinistryListener {
    private static final String TAG_FIND_MINISTRY = "findMinistry";

    @NonNull
    private /* final */ GoogleAnalyticsManager mGoogleAnalytics;

    @NonNull
    private /* final */ String mGuid;

    public static void start(@NonNull final Context context, @NonNull final String guid) {
        final Intent intent = new Intent(context, JoinMinistryActivity.class);
        intent.putExtra(EXTRA_GUID, guid);
        context.startActivity(intent);
    }

    /* BEGIN lifecycle */

    @Override
    public void onCreate(@Nullable final Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.activity_join_ministry);

        mGoogleAnalytics = GoogleAnalyticsManager.getInstance(this);

        final Intent intent = this.getIntent();
        mGuid = intent.getStringExtra(EXTRA_GUID);

        loadFindMinistryFragment(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleAnalytics.sendJoinMinistryScreen(mGuid);
    }

    @Override
    public void onJoinedMinistry(@NonNull final String ministryId) {
        mGoogleAnalytics.sendJoinMinistryEvent(mGuid, ministryId);
        finish();
    }

    /* END lifecycle */

    private void loadFindMinistryFragment(final boolean force) {
        // only load the findMinistry fragment if it's not currently loaded
        final FragmentManager fm = getSupportFragmentManager();
        if (force || fm.findFragmentByTag(TAG_FIND_MINISTRY) == null) {
            fm.beginTransaction().replace(R.id.findMinistry, FindMinistryFragment.newInstance(mGuid), TAG_FIND_MINISTRY)
                    .commit();
        }
    }
}
