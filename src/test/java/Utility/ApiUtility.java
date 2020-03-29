package Utility;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import io.restassured.RestAssured;

import java.util.ArrayList;
import java.util.List;

public class ApiUtility {

    private static ApiUtility singleInstance;
    private String preFilledAddress =  "\"address\":{\"id\":\"\",\"zipcode\":\"13010040\"" +
            ",\"state\":\"SP\",\"city\":\"Campinas\",\"neighborhood\":\"Centro\"" +
            ",\"address\":\"Rua Ferreira Penteado\",\"number\":\"178\",\"complement\":\"\"}";

    public static ApiUtility getInstance(){
        if(singleInstance == null){
            singleInstance = new ApiUtility();
        }
        return singleInstance;
    }

    public List<String> getPersonListIdFromResponse (Response jsonResponse) {
        List<String> personList = new ArrayList<>();
        String personID = jsonResponse.body().path("[0].id");
        int i = 1;
        while (personID != null) {
            personList.add(personID);
            personID = jsonResponse.body().path("[" + i + "].id");
            i++;
        }
        return personList;
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

    public String createJsonForRandomPersonToAdd() {
        String randomEmail = UserUtility.getInstance().generateRandomEmail();
        String randomDocument = UserUtility.getInstance().generateRandomCPF();
        String randomName = UserUtility.getInstance().generateRandomName();
        String randomMotherName = UserUtility.getInstance().generateRandomName();
        String randomFatherName = UserUtility.getInstance().generateRandomName();
        String jsonForPerson = "{\"name\":\"" + randomName + "\"" +
                ",\"email\":\"" + randomEmail + "\"" +
                ",\"document\":\"" + randomDocument + "\"" +
                ",\"mother\":\"" + randomMotherName + "\"" +
                ",\"father\":\"" + randomFatherName + "\"" +
                "," + preFilledAddress +
                "}";
        return jsonForPerson;
    }
}
