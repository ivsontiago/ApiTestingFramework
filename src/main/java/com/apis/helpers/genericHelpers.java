package com.apis.helpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class genericHelpers {

    public static boolean validateJson(String jsonArray)
    {
        try {
            new JSONObject(jsonArray);
        } catch (JSONException ex) {
            try {
                new JSONArray(jsonArray);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    public static int getJsonArrayLength(String jsonArray)
    {
        if (validateJson(jsonArray))
        {
            JSONArray mArray = new JSONArray(jsonArray);
            return mArray.length();
        }
        return -1;
    }

    public static <T> T convertStringToJsonObjectOrArray(String inputJson)
    {
        String firstChar = String.valueOf(inputJson.charAt(0));

        if (firstChar.equalsIgnoreCase("["))
        {
            JSONArray jArr = new JSONArray(inputJson);
            return (T) jArr;
        }
        else
        {
            JSONObject jObj = new JSONObject(inputJson);
            return (T) jObj;
        }
    }

    public static String percentEncode(String inputString) throws UnsupportedEncodingException
    {
        String encoded = "";
        try {
            encoded = URLEncoder.encode(inputString, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sb = "";
        char focus;
        for (int i = 0; i < encoded.length(); i++) {
            focus = encoded.charAt(i);
            if (focus == '*') {
                sb += "%2A";
            } else if (focus == '+') {
                sb += "%20";
            } else if (focus == '%' && i + 1 < encoded.length()
                    && encoded.charAt(i + 1) == '7' && encoded.charAt(i + 2) == 'E') {
                sb += '~';
                i += 2;
            } else {
                sb += focus;
            }
        }
        return sb.toString();
    }
}