package com.expidev.gcmapp.db;

import android.text.TextUtils;

public class Contract {
    private Contract() {
    }

    public static abstract class Base {
        static final String COLUMN_LAST_SYNCED = "lastSynced";

        static final String SQL_COLUMN_LAST_SYNCED = COLUMN_LAST_SYNCED + " INTEGER";
    }

    public static final class Training extends Base {
        public static final String TABLE_NAME = "training";

        static final String COLUMN_ID = "id";
        static final String COLUMN_MINISTRY_ID = "ministry_id";
        static final String COLUMN_NAME = "name";
        static final String COLUMN_DATE = "date";
        static final String COLUMN_TYPE = "type";
        static final String COLUMN_MCC = "mcc";
        static final String COLUMN_LATITUDE = "latitude";
        static final String COLUMN_LONGITUDE = "longitude";

        static final String[] PROJECTION_ALL =
                {COLUMN_ID, COLUMN_MINISTRY_ID, COLUMN_NAME, COLUMN_DATE, COLUMN_TYPE, COLUMN_MCC, COLUMN_LATITUDE,
                        COLUMN_LONGITUDE, COLUMN_LAST_SYNCED};

        private static final String SQL_COLUMN_ID = COLUMN_ID + " INT";
        private static final String SQL_COLUMN_MINISTRY_ID = COLUMN_MINISTRY_ID + " TEXT";
        private static final String SQL_COLUMN_NAME = COLUMN_NAME + " TEXT";
        private static final String SQL_COLUMN_DATE = COLUMN_DATE + " TEXT";
        private static final String SQL_COLUMN_TYPE = COLUMN_TYPE + " TEXT";
        private static final String SQL_COLUMN_MCC = COLUMN_MCC + " TEXT";
        private static final String SQL_COLUMN_LATITUDE = COLUMN_LATITUDE + " DECIMAL";
        private static final String SQL_COLUMN_LONGITUDE = COLUMN_LONGITUDE + " DECIMAL";
        private static final String SQL_PRIMARY_KEY = "PRIMARY KEY(" + COLUMN_ID + ")";

        static final String SQL_WHERE_PRIMARY_KEY = COLUMN_ID + " = ?";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + TextUtils
                .join(",", new Object[] {SQL_COLUMN_ID, SQL_COLUMN_MINISTRY_ID, SQL_COLUMN_NAME, SQL_COLUMN_DATE,
                        SQL_COLUMN_TYPE, SQL_COLUMN_MCC, SQL_COLUMN_LATITUDE, SQL_COLUMN_LONGITUDE,
                        SQL_COLUMN_LAST_SYNCED, SQL_PRIMARY_KEY}) + ")";
    }

    public static abstract class MinistryBase extends Base
    {
        static final String COLUMN_MINISTRY_ID = "ministry_id";
        static final String COLUMN_NAME = "name";

        static final String SQL_COLUMN_MINISTRY_ID = COLUMN_MINISTRY_ID + " TEXT";
        static final String SQL_COLUMN_NAME = COLUMN_NAME + " TEXT";
        static final String SQL_PRIMARY_KEY = "PRIMARY KEY(" + COLUMN_MINISTRY_ID + ")";

        static final String SQL_WHERE_PRIMARY_KEY = COLUMN_MINISTRY_ID + " = ?";
    }

    public static final class Ministry extends MinistryBase
    {
        public static final String TABLE_NAME = "all_ministries";

        static final String[] PROJECTION_ALL = { COLUMN_MINISTRY_ID, COLUMN_NAME, COLUMN_LAST_SYNCED };

        public static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + TextUtils
            .join(",", new Object[] { SQL_COLUMN_MINISTRY_ID, SQL_COLUMN_NAME,
                SQL_COLUMN_LAST_SYNCED, SQL_PRIMARY_KEY }) + ")";
    }

    public static final class AssociatedMinistry extends MinistryBase
    {
        public static final String TABLE_NAME = "associated_ministries";

        static final String COLUMN_MIN_CODE = "min_code";
        static final String COLUMN_HAS_SLM = "has_slm";
        static final String COLUMN_HAS_LLM = "has_llm";
        static final String COLUMN_HAS_DS = "has_ds";
        static final String COLUMN_HAS_GCM = "has_gcm";
        static final String COLUMN_PARENT_MINISTRY_ID = "parent_ministry_id";

        static final String[] PROJECTION_ALL = {
            COLUMN_MINISTRY_ID, COLUMN_NAME, COLUMN_MIN_CODE, COLUMN_HAS_SLM, COLUMN_HAS_LLM, COLUMN_HAS_DS,
            COLUMN_HAS_GCM, COLUMN_PARENT_MINISTRY_ID, COLUMN_LAST_SYNCED
        };

        private static final String SQL_COLUMN_MIN_CODE = COLUMN_MIN_CODE + " TEXT";
        private static final String SQL_COLUMN_HAS_SLM = COLUMN_HAS_SLM + " INTEGER";
        private static final String SQL_COLUMN_HAS_LLM = COLUMN_HAS_LLM + " INTEGER";
        private static final String SQL_COLUMN_HAS_DS = COLUMN_HAS_DS + " INTEGER";
        private static final String SQL_COLUMN_HAS_GCM = COLUMN_HAS_GCM + " INTEGER";
        private static final String SQL_COLUMN_PARENT_MINISTRY_ID = COLUMN_PARENT_MINISTRY_ID + " TEXT";

        static final String SQL_WHERE_PARENT = COLUMN_PARENT_MINISTRY_ID + " = ?";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + TextUtils
            .join(",", new Object[] { SQL_COLUMN_MINISTRY_ID, SQL_COLUMN_NAME, SQL_COLUMN_MIN_CODE, SQL_COLUMN_HAS_SLM,
                SQL_COLUMN_HAS_LLM, SQL_COLUMN_HAS_DS, SQL_COLUMN_HAS_GCM, SQL_COLUMN_PARENT_MINISTRY_ID,
                SQL_COLUMN_LAST_SYNCED, SQL_PRIMARY_KEY }) + ")";
    }
}