package mockapp.yashas.com.codesampleapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ServerResponse {
    public class Entry {
        String author;
        String content;
    }

    public ArrayList<Entry> entries;

    public ServerResponse() {
        entries = new ArrayList<>();
    }

    public ServerResponse(JSONObject pJSON) {
        this();
        parse(pJSON);
    }


    public void parse(JSONObject pJSON) {
        try {

            JSONArray jsonArray = pJSON.getJSONObject("feed").getJSONArray("entry");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonEntry = jsonArray.getJSONObject(i);
                Entry entry = new Entry();
                entry.author = getAuthor(jsonEntry);
                entry.content = getContent(jsonEntry);
                entries.add(entry);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getAuthor(JSONObject json) {
        try {
            return json.getJSONObject("author").getJSONObject("name").getString("label");
        } catch (JSONException e) {
        }
        return "";
    }

    private String getContent(JSONObject json) {

        try {
            return json.getJSONObject("content").getString("label");
        } catch (JSONException e) {
        }
        return "";
    }


}
