package com.expidevapps.android.measurements.sync.service;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.expidevapps.android.measurements.db.Contract;
import com.expidevapps.android.measurements.db.GmaDao;
import com.expidevapps.android.measurements.model.Story;

import org.ccci.gto.android.common.db.Transaction;

import java.util.List;

public class StoriesManager {
    public static String[] PROJECTION_GET_STORY_DATA =
            {Contract.Story.COLUMN_TITLE, Contract.Story.COLUMN_CONTENT, Contract.Story.COLUMN_IMAGE,
                    Contract.Story.COLUMN_MINISTRY_ID, Contract.Story.COLUMN_MCC, Contract.Story.COLUMN_LONGITUDE,
                    Contract.Story.COLUMN_LATITUDE, Contract.Story.COLUMN_PRIVACY, Contract.Story.COLUMN_STATE,
                    Contract.Story.COLUMN_CREATED_BY, Contract.Story.COLUMN_CREATED};

    @NonNull
    private final GmaDao mDao;

    @Nullable
    private static StoriesManager INSTANCE;

    private StoriesManager(@NonNull final Context context) {
        mDao = GmaDao.getInstance(context);
    }

    public static StoriesManager getInstance(@NonNull final Context context) {
        synchronized (StoriesManager.class) {
            if (INSTANCE == null) {
                INSTANCE = new StoriesManager(context.getApplicationContext());
            }
        }

        return INSTANCE;
    }

    @WorkerThread
    public void updateStoriesFromApi(@NonNull final List<Story> stories) {
        // iterate over all the provided stories
        for (final Story story : stories) {
            final Transaction tx = mDao.newTransaction();
            try {
                tx.beginTransactionNonExclusive();

                // only update/insert stories that don't exist, aren't new and aren't dirty
                final Story existing = mDao.refresh(story);
                if (existing == null) {
                    mDao.insert(story);
                } else if (!existing.isNew() && !existing.isDirty()) {
                    mDao.update(story, PROJECTION_GET_STORY_DATA);
                }

                tx.setTransactionSuccessful();
            } finally {
                tx.endTransaction();
            }
        }
    }
}