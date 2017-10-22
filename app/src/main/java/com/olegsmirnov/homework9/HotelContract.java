package com.olegsmirnov.homework9;

import android.provider.BaseColumns;

public final class HotelContract {

    private HotelContract() {}

    public static abstract class GuestEntry implements BaseColumns {
        public static final String TABLE_NAME = "guests";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_FIRST_NAME = "first_name";
        public static final String COLUMN_LAST_NAME = "last_name";
        public static final String COLUMN_COMPANY = "company";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_CITY = "city";
        public static final String COLUMN_COUNTRY = "country";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_EMAIL = "email";
    }
}