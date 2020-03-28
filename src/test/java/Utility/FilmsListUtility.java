package Utility;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FilmsListUtility {

    private static FilmsListUtility singleInstance;

    public static FilmsListUtility getInstance(){
        if(singleInstance == null){
            singleInstance = new FilmsListUtility();
        }
        return singleInstance;
    }

    public ArrayList<String> getFilmsListFromResponseJson(Response response) {
        JSONObject responseJson = new JSONObject(response.asString());
        JSONArray array = responseJson.getJSONArray("results");

        ArrayList<String> filmsList = new ArrayList<String>();

        for (int i = 0; i < array.length(); i++) {
            String filmName = array.getJSONObject(i).getString("title");
            filmsList.add(filmName);
        }
        return filmsList;
    }
}
