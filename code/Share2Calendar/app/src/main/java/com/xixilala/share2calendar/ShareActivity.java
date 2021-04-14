package com.xixilala.share2calendar;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;


import java.util.Calendar;
import java.util.TimeZone;


public class ShareActivity extends Activity {

    private static final String CALANDER_URL = "content://com.android.calendar/calendars";
    private static final String CALANDER_EVENT_URL = "content://com.android.calendar/events";
    private static final String CALANDER_REMIDER_URL = "content://com.android.calendar/reminders";
    private static final String CALENDARS_NAME = "test";
    private static final String CALENDARS_ACCOUNT_NAME = "test@gmail.com";
    private static final String CALENDARS_ACCOUNT_TYPE = "com.android.exchange";
    private static final String CALENDARS_DISPLAY_NAME = "测试账户";

    protected void onCreate(  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.write2Calendar(this.getIntent());
    }

    protected void onNewIntent(  Intent intent) {
        super.onNewIntent(intent);
        this.write2Calendar(intent);
    }

    private void write2Calendar(Intent intent) {
        CharSequence result = intent != null ? intent.getCharSequenceExtra("android.intent.extra.PROCESS_TEXT") : null;

        try {
            if (result != null) {

                Context var10001 = this.getApplicationContext();

                this.addCalendarEvent(var10001, result.toString(), "", System.currentTimeMillis());
                Toast.makeText(this.getApplicationContext(), (CharSequence)"分享成功", Toast.LENGTH_SHORT).show();
            }
        } catch (Throwable var8) {
            var8.printStackTrace();
            Toast.makeText(this.getApplicationContext(), (CharSequence)"分享失败", Toast.LENGTH_SHORT).show();
        }

        this.finish();
    }

    public final void addCalendarEvent(  Context context,   String title,   String description, long beginTime) throws Throwable {

        int calId = this.checkAndAddCalendarAccount(context);
        if (calId >= 0) {
            ContentValues event = new ContentValues();
            event.put("title", title);
            event.put("description", description);
            event.put("calendar_id", calId);
            Calendar mCalendar = Calendar.getInstance();
            mCalendar.setTimeInMillis(beginTime);
            long start = mCalendar.getTime().getTime();
            mCalendar.setTimeInMillis(start + 1L);
            long end = mCalendar.getTime().getTime();
            event.put("dtstart", start);
            event.put("dtend", end);
            event.put("hasAlarm", 1);
            event.put("eventTimezone", "Asia/Shanghai");
            Uri var16 = context.getContentResolver().insert(Uri.parse(CALANDER_EVENT_URL), event);
            if (var16 != null) {
                Uri newEvent = var16;
                ContentValues values = new ContentValues();
                values.put("event_id", ContentUris.parseId(newEvent));
                values.put("minutes", 10);
                values.put("method", 1);
                var16 = context.getContentResolver().insert(Uri.parse(CALANDER_REMIDER_URL), values);
                if (var16 == null) {
                    throw (Throwable)(new RuntimeException("添加失败"));
                }
            }
        }
    }

    private int checkAndAddCalendarAccount(Context context) {
        int oldId = this.checkCalendarAccount(context);
        int var10000;
        if (oldId >= 0) {
            var10000 = oldId;
        } else {
            long addId = this.addCalendarAccount(context);
            var10000 = addId >= 0L ? this.checkCalendarAccount(context) : -1;
        }

        return var10000;
    }

    private int checkCalendarAccount(Context context) {
        Cursor userCursor = context.getContentResolver().query(Uri.parse(CALANDER_URL), (String[])null, (String)null, (String[])null, (String)null);
        boolean var6 = false;

        int count;
        try {
            var6 = true;
            if (userCursor == null) {
                return (byte) -1;
            }

            count = userCursor.getCount();
            int var10000;
            if (count > 0) {
                userCursor.moveToFirst();
                var10000 = userCursor.getInt(userCursor.getColumnIndex("_id"));
            } else {
                var10000 = -1;
            }

            count = var10000;
            var6 = false;
        } finally {
            if (var6) {
                if (userCursor != null) {
                    userCursor.close();
                }

            }
        }

        userCursor.close();
        return count;
    }

    private long addCalendarAccount(Context context) {
        TimeZone timeZone = TimeZone.getDefault();
        ContentValues value = new ContentValues();
        value.put("name", CALENDARS_NAME);
        value.put("account_name", CALENDARS_ACCOUNT_NAME);
        value.put("account_type", CALENDARS_ACCOUNT_TYPE);
        value.put("calendar_displayName", CALENDARS_DISPLAY_NAME);
        value.put("visible", 1);
        value.put("calendar_color", -16776961);
        value.put("calendar_access_level", 700);
        value.put("sync_events", 1);

        value.put("calendar_timezone", timeZone.getID());
        value.put("ownerAccount", CALENDARS_ACCOUNT_NAME);
        value.put("canOrganizerRespond", 0);
        Uri calendarUri = Uri.parse(CALANDER_URL);
        calendarUri = calendarUri.buildUpon().appendQueryParameter("caller_is_syncadapter", "true").appendQueryParameter("account_name", CALENDARS_ACCOUNT_NAME).appendQueryParameter("account_type", CALENDARS_ACCOUNT_TYPE).build();
        Uri result = context.getContentResolver().insert(calendarUri, value);
        return result == null ? -1L : ContentUris.parseId(result);
    }
}
