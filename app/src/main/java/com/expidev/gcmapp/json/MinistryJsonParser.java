package com.expidev.gcmapp.json;

import android.util.Log;

import com.expidev.gcmapp.model.Ministry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by William.Randall on 1/15/2015.
 */
public class MinistryJsonParser
{
    private static final String TAG = MinistryJsonParser.class.getSimpleName();

    public static List<Ministry> parseMinistriesJson(JSONArray jsonResults)
    {
        List<Ministry> ministryList = new ArrayList<Ministry>();
        try
        {
            for(int i = 0; i < jsonResults.length(); i++)
            {
                JSONObject jsonObject = jsonResults.getJSONObject(i);
                Ministry ministry = parseMinistry(jsonObject);

                if(jsonObject.has("sub_ministries"))
                {
                    JSONArray subMinistriesJson = jsonObject.getJSONArray("sub_ministries");
                    List<Ministry> subMinistries = new ArrayList<Ministry>();

                    for(int j = 0; j < subMinistriesJson.length(); i++)
                    {
                        subMinistries.add(parseMinistry(subMinistriesJson.getJSONObject(i)));
                    }
                    ministry.setSubMinistries(subMinistries);
                }

                ministryList.add(ministry);
            }
        }
        catch(JSONException e)
        {
            //Do stuff
            Log.e(TAG, "Error parsing JSON: " + e.getMessage());
        }

        return ministryList;
    }

    public static List<Ministry> parseMinistriesJsonRecursive(JSONArray jsonResults)
    {
        List<Ministry> ministryList = new ArrayList<Ministry>();
        try
        {
            for(int i = 0; i < jsonResults.length(); i++)
            {
                JSONObject jsonObject = jsonResults.getJSONObject(i);
                Ministry ministry = parseMinistry(jsonObject);

                if(jsonObject.has("sub_ministries"))
                {
                    List<Ministry> subMinistries = parseMinistriesJsonRecursive(jsonObject.getJSONArray("sub_ministries"));
                    ministry.setSubMinistries(subMinistries);
                }

                ministryList.add(ministry);
            }
        }
        catch(JSONException e)
        {
            //Do stuff
            Log.e(TAG, "Error parsing JSON: " + e.getMessage());
        }

        return ministryList;
    }

    public static Ministry parseMinistry(JSONObject jsonObject) throws JSONException
    {
        Ministry ministry = new Ministry();

        ministry.setMinistryId(jsonObject.getString("ministry_id"));
        ministry.setName(jsonObject.getString("name"));
        ministry.setMinistryCode(jsonObject.optString("min_code"));
        ministry.setHasSlm(jsonObject.optBoolean("has_slm"));
        ministry.setHasLlm(jsonObject.optBoolean("has_llm"));
        ministry.setHasDs(jsonObject.optBoolean("has_ds"));
        ministry.setHasGcm(jsonObject.optBoolean("has_gcm"));

        return ministry;
    }

    public static Map<String, String> parseMinistriesAsMap(JSONArray jsonResults)
    {
        Map<String, String> ministryMap = new HashMap<String, String>();

        try
        {
            for(int i = 0; i < jsonResults.length(); i++)
            {
                JSONObject jsonObject = jsonResults.getJSONObject(i);
                ministryMap.put(jsonObject.getString("name"), jsonObject.getString("ministry_id"));
            }
        }
        catch(JSONException e)
        {
            //Do stuff
            Log.e(TAG, "Error parsing JSON: " + e.getMessage());
        }

        return ministryMap;
    }
}