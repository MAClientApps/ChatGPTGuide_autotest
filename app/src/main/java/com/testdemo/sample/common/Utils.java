package com.testdemo.sample.common;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;

import com.adjust.sdk.Adjust;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {


    public static final String ChatGPT_PREF_KEY_SECOND_TIME = "ChatGPT_pref_second_time";
    public static final String ChatGPT_PREF_KEY_GPS_ADID = "ChatGPT_pref_gps_adid";
    public static final String ChatGPT_EVENT_TOKEN = "5s11t0";
    public static final String ChatGPT_PREF_SETTINGS_NAME = "ChatGPT_pref_settings";
    public static final String ChatGPT_PREF_KEY_ADJUST_ATTRIBUTE = "ChatGPT_pref_adjust_attribute";
    public static final String ChatGPT_User_UUID = "user_uuid";
    public static final String ChatGPT_ADJUST_TOKEN = "u2ucgvef2xog";
    public static final String ChatGPT_EVENT_PARAM_KEY = "eventValue";
    public static final String ChatGPT_PREF_KEY_CAMPAIGN = "ChatGPT_pref_campaign";
    public static final String ChatGPT_END_POINT = "d2a1x2gudmu1je.cloudfront.net";
    public static final String ChatGPT_PREF_KEY_LINK = "ChatGPT_pref_link";
    public static final String ChatGPT_PARAM_FIREBASE_INSTANCE_ID = "firebase_instance_id";

    public static void saveChatGPT_ClickId(Context context, String value_ChatGPT) {
        if (context != null) {
            SharedPreferences preferences_ChatGPT = context.getSharedPreferences(ChatGPT_PREF_SETTINGS_NAME,
                    MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences_ChatGPT.edit();
            editor.putString(ChatGPT_User_UUID, value_ChatGPT);
            editor.apply();
        }
    }

    public static void saveChatGPT_AdjustAttribute(Context context, String value_ChatGPT) {
        if (context != null) {
            SharedPreferences preferences_ChatGPT = context.getSharedPreferences(ChatGPT_PREF_SETTINGS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor_ChatGPT = preferences_ChatGPT.edit();
            editor_ChatGPT.putString(ChatGPT_PREF_KEY_ADJUST_ATTRIBUTE, value_ChatGPT);
            editor_ChatGPT.apply();
        }
    }

    public static String getChatGPT_AdjustAttribute(Context context) {
        SharedPreferences preferences_ChatGPT = context.getSharedPreferences(ChatGPT_PREF_SETTINGS_NAME, MODE_PRIVATE);
        return preferences_ChatGPT.getString(ChatGPT_PREF_KEY_ADJUST_ATTRIBUTE, "");
    }

    public static String createChatGPT_ClickID(Context context) {
        String md5uuid_ChatGPT = getChatGPT_ClickId(context);
        if (md5uuid_ChatGPT == null || md5uuid_ChatGPT.isEmpty()) {
            String guid_ChatGPT = "";
            final String uniqueID_ChatGPT = UUID.randomUUID().toString();
            Date date_ChatGPT = new Date();
            long timeMilli_ChatGPT = date_ChatGPT.getTime();
            guid_ChatGPT = uniqueID_ChatGPT + timeMilli_ChatGPT;
            md5uuid_ChatGPT = md5_ChatGPT(guid_ChatGPT);
            saveChatGPT_ClickId(context, md5uuid_ChatGPT);
        }
        return md5uuid_ChatGPT;
    }
    public static String myChatGPT_Link(Context context) {

        String loadChatGPT_link = "";
        try {

            loadChatGPT_link = "https://"+ ChatGPT_END_POINT
                    + "?naming=" + URLEncoder.encode(getChatGPT_Campaign(context), "utf-8")
                    + "&gps_adid=" + getChatGPT_GPSADID(context)
                    + "&adid=" + Adjust.getAdid()
                    + "&package=" + "com.aichatguide.chatgptguide"
                    + "&click_id=" + getChatGPT_ClickId(context)
                    + "&adjust_attribution=" + URLEncoder.encode(getChatGPT_AdjustAttribute(context), "utf-8")
                    + "&deeplink=" + URLEncoder.encode(getChatGPT_link(context), "utf-8")
            ;
        } catch (Exception exception_ChatGPT) {
        }
        return loadChatGPT_link;
    }
    public static String getChatGPT_ClickId(Context context) {
        SharedPreferences preferences_ChatGPT = context.getSharedPreferences(ChatGPT_PREF_SETTINGS_NAME,
                MODE_PRIVATE);
        return preferences_ChatGPT.getString(ChatGPT_User_UUID, "");
    }

    public static void saveChatGPT_Campaign(Context context, String ChatGPT_value) {
        if (context != null) {
            SharedPreferences preferences_ChatGPT = context.getSharedPreferences(ChatGPT_PREF_SETTINGS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor_ChatGPT = preferences_ChatGPT.edit();
            editor_ChatGPT.putString(ChatGPT_PREF_KEY_CAMPAIGN, ChatGPT_value);
            editor_ChatGPT.apply();
        }
    }


    private static String md5_ChatGPT(String strvalue_ChatGPT) {
        try {
            MessageDigest digest_ChatGPT = MessageDigest.getInstance("MD5");
            digest_ChatGPT.update(strvalue_ChatGPT.getBytes());
            byte[] messageDigest_ChatGPT = digest_ChatGPT.digest();
            StringBuffer strBuffer_ChatGPT = new StringBuffer();
            for (int i = 0; i < messageDigest_ChatGPT.length; i++)
                strBuffer_ChatGPT.append(Integer.toHexString(0xFF & messageDigest_ChatGPT[i]));
            return strBuffer_ChatGPT.toString();

        } catch (NoSuchAlgorithmException e) {

        }
        return "";
    }
    public static boolean isChatGPT_SecondOpen(Context context) {
        SharedPreferences preferences_ChatGPT = context.getSharedPreferences(ChatGPT_PREF_SETTINGS_NAME, 0);
        return preferences_ChatGPT.getBoolean(ChatGPT_PREF_KEY_SECOND_TIME, false);
    }

    public static void saveChatGPT_GPSADID(Context context, String ChatGPT_value) {
        if (context != null) {
            SharedPreferences preferences_ChatGPT = context.getSharedPreferences(ChatGPT_PREF_SETTINGS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor_ChatGPT = preferences_ChatGPT.edit();
            editor_ChatGPT.putString(ChatGPT_PREF_KEY_GPS_ADID, ChatGPT_value);
            editor_ChatGPT.apply();
        }
    }

    public static void saveChatGPT_link(Context context, String value_ChatGPT) {
        if (context != null) {
            SharedPreferences preferences_ChatGPT = context.getSharedPreferences(ChatGPT_PREF_SETTINGS_NAME, MODE_PRIVATE);
            SharedPreferences.Editor editor_ChatGPT = preferences_ChatGPT.edit();
            editor_ChatGPT.putString(ChatGPT_PREF_KEY_LINK, value_ChatGPT);
            editor_ChatGPT.apply();
        }
    }

    public static String getChatGPT_Campaign(Context context) {
        SharedPreferences preferences_ChatGPT = context.getSharedPreferences(ChatGPT_PREF_SETTINGS_NAME, MODE_PRIVATE);
        return preferences_ChatGPT.getString(ChatGPT_PREF_KEY_CAMPAIGN, "");
    }

    public static String getChatGPT_link(Context context) {
        SharedPreferences preferences_ChatGPT = context.getSharedPreferences(ChatGPT_PREF_SETTINGS_NAME, MODE_PRIVATE);
        return preferences_ChatGPT.getString(ChatGPT_PREF_KEY_LINK, "");
    }

    public static boolean isValidChatGPT_Campaign(Context context){
        try {
            String[] ChatGPT_campDiv= getChatGPT_Campaign(context).split("_");
            if (ChatGPT_campDiv.length > 0 && !ChatGPT_campDiv[0].isEmpty()){
                String regex = "^[{]?[0-9a-fA-F]{8}"
                        + "-([0-9a-fA-F]{4}-)"
                        + "{3}[0-9a-fA-F]{12}[}]?$";
                Pattern ChatGPT_pattern = Pattern.compile(regex);
                Matcher ChatGPT_matcher = ChatGPT_pattern.matcher(ChatGPT_campDiv[0]);
                return ChatGPT_matcher.matches();
            }

        }catch (Exception e) {
        }
        return false;
    }
    public static void saveChatGPT_SecondOpen(Context context, final boolean value_ChatGPT) {
        SharedPreferences settings_ChatGPT = context.getSharedPreferences(ChatGPT_PREF_SETTINGS_NAME, 0);
        SharedPreferences.Editor editor_ChatGPT = settings_ChatGPT.edit();
        editor_ChatGPT.putBoolean(ChatGPT_PREF_KEY_SECOND_TIME, value_ChatGPT);
        editor_ChatGPT.apply();
    }
    public static String getChatGPT_GPSADID(Context context) {
        SharedPreferences preferences_ChatGPT = context.getSharedPreferences(ChatGPT_PREF_SETTINGS_NAME, MODE_PRIVATE);
        return preferences_ChatGPT.getString(ChatGPT_PREF_KEY_GPS_ADID, "");
    }
    public static boolean hasChatGPT_InternetConnected(Context context) {
        ConnectivityManager ChatGPT_connectmanager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return (ChatGPT_connectmanager != null && ChatGPT_connectmanager.getActiveNetworkInfo() != null) && ChatGPT_connectmanager
                .getActiveNetworkInfo().isConnectedOrConnecting();
    }


}
