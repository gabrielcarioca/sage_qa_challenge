package Utility;

import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class WebServiceUtility {

    private static WebServiceUtility singleInstance;
    private String preFilledAddress =  "\"address\":{\"id\":\"\",\"zipcode\":\"13010040\"" +
            ",\"state\":\"SP\",\"city\":\"Campinas\",\"neighborhood\":\"Centro\"" +
            ",\"address\":\"Rua Ferreira Penteado\",\"number\":\"178\",\"complement\":\"\"}";
    private String emptyAddress = "\"address\":{\"id\":\"\",\"zipcode\":\"\"" +
            ",\"state\":\"\",\"city\":\"\",\"neighborhood\":\"\",\"address\":\"\"" +
            ",\"number\":\"\",\"complement\":\"\"}";

    public static WebServiceUtility getInstance(){
        if(singleInstance == null){
            singleInstance = new WebServiceUtility();
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

    public String createJsonForRandomUserWithMissingField(String missingField) {
        String completeJson = createJsonForRandomPersonToAdd();
        if (missingField == "address") {
            String jsonWIthoutField = completeJson.replaceAll(
                    "\"" + missingField + "\":{.*}}",
                    "\"" + missingField + "\":" + emptyAddress + "}"
            );
            return jsonWIthoutField;
        }
        else {
            String jsonWIthoutField = completeJson.replaceAll(
                    "\"" + missingField + "\":\"([^\\\"])*\"",
                    "\"" + missingField + "\":\"\""
            );
            return jsonWIthoutField;
        }
    }

    public String createJsonForRandomUserWithInvalidEmail() {
        String completeJson = createJsonForRandomPersonToAdd();
        String jsonWIthoutField = completeJson.replaceAll(
                "\"email\":\"([^\\\"])*\"",
                "\"email\":\"invalidEmail\""
        );
        return jsonWIthoutField;
    }

    public String createJsonForRandomUserWithInvaliDocument() {
        String completeJson = createJsonForRandomPersonToAdd();
        String jsonWIthoutField = completeJson.replaceAll(
                "\"document\":\"([^\\\"])*\"",
                "\"document\":\"invalidDocument\""
        );
        return jsonWIthoutField;
    }
}
