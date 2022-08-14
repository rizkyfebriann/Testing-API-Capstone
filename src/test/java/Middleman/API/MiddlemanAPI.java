package Middleman.API;

import java.io.File;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class MiddlemanAPI {
    public static final String URL = "https://postme.site";
    public static final String DIR = System.getProperty("user.dir");
    public static final String JSON_FILE_LOGIN = DIR+"/src/test/resources/JSONFile/Login";
    public static final String JSON_FILE_REGISTER = DIR+"/src/test/resources/JSONFile/Register";
    public static final String JSON_FILE_ADMINS = DIR+"/src/test/resources/JSONFile/Admins";
    public static String POST_LOGIN_USER = URL+"/login";
    public static String POST_LOGININVALID_USER = URL+"/logins";

    public static String POST_REGISTER_USER = URL+"/register";
    public static String POST_REGISTERINVALID_USER = URL+"/registers";

    public static String TOKEN_ADMINS = "";
    public static String TOKEN_ADMINS_INVALID ="";
    public static String GET_ALL_PRODUCT_ADMINS = URL+"/admins";
    public static String GET_INVALIDPATH_ADMINS = URL+"/adminssss";
    public static String POST_CREATE_ADMINS = URL+"/admins";
    public static String PUT_UPDATE_ADMINS = URL+"/admins/6";
    public static String DELETE_PRODUCT_ADMINS = URL+"/admins/{id}";
    public static String DELETE_INVALIDPATH_ADMINS = URL+"/adminss/{id}";


//    public String bearer_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJJRCI6MiwiUm9sZSI6InVzZXIiLCJleHAiOjE2NjAzMjQxMjR9.kOYiRZO8OQXfNERZGtfwcC_g0Jyzlfdg9MBQ27V-Zn8";
//
//    public void apply(RequestTemplate requestTemplate) {
//
//        requestTemplate.header(AUTHORIZATION_HEADER, getBearerTokenHeader());
//
//    }

    @Step("Post login user or admin")
    public void postLoginUserOrAdmin(File json){
        SerenityRest.given()
                .contentType(ContentType.JSON)
                .body(json);
    }

    @Step("Post register user")
    public void postRegisterUser(File json){
        SerenityRest.given()
                .contentType(ContentType.JSON)
                .body(json);
    }
    @Step("Get all product admins")
    public static void getAllProductAdmins(File json) {
        SerenityRest.given()
                .contentType(ContentType.JSON)
                .body(json);

    }
    @Step("Post create product admins")
    public static void postCreateProductAdmins(File json) {
        JsonPath jsonPath = new JsonPath(json);
        SerenityRest.given()
                .header("Authorization", TOKEN_ADMINS)
                .multiPart("product_image", new File("/Users/nabilahpermata/AUTOMATION_QA5/Testing-API-Capstone/src/test/resources/image/Beras Jago.jpeg"))
                .formParam("product_name",jsonPath.get("product_name").toString())
                .formParam("stock",jsonPath.get("stock").toString())
                .formParam("unit",jsonPath.get("unit").toString())
                .formParam("price",jsonPath.get("price").toString());
    }
    @Step("Put update product admins")
    public static void putUpdateProductAdmins(File json) {
        JsonPath jsonPath = new JsonPath(json);
        SerenityRest.given()
                .header("Authorization", TOKEN_ADMINS)
                .multiPart("product_image", new File("/Users/nabilahpermata/AUTOMATION_QA5/Testing-API-Capstone/src/test/resources/image/beras-koi.jpeg"))
                .formParam("product_name",jsonPath.get("product_name").toString())
                .formParam("stock",jsonPath.get("stock").toString())
                .formParam("unit",jsonPath.get("unit").toString())
                .formParam("price",jsonPath.get("price").toString());
    }

    @Step("Put update product admins without product name and stock")
    public static void putUpdateProductAdminsWithoutNameAndStock(File json) {
        JsonPath jsonPath = new JsonPath(json);
        SerenityRest.given()
                .header("Authorization", TOKEN_ADMINS)
                .multiPart("product_image", new File("/Users/nabilahpermata/AUTOMATION_QA5/Testing-API-Capstone/src/test/resources/image/beras-koi.jpeg"))
                .formParam("unit", jsonPath.get("unit").toString())
                .formParam("price", jsonPath.get("price").toString());
    }
    @Step("Put update product admins without unit and price")
    public static void putUpdateProductAdminsWithoutUnitAndPrice(File json) {
        JsonPath jsonPath = new JsonPath(json);
        SerenityRest.given()
                .header("Authorization", TOKEN_ADMINS)
                .multiPart("product_image", new File("/Users/nabilahpermata/AUTOMATION_QA5/Testing-API-Capstone/src/test/resources/image/beras-koi.jpeg"))
                .formParam("product_name",jsonPath.get("product_name").toString())
                .formParam("stock",jsonPath.get("stock").toString());
    }
   @Step("Delete product admins")
   public void deleteProductAdmins (int id){
            SerenityRest.given()
                    .header("Authorization",TOKEN_ADMINS)
                    .pathParam ("id",id);
    }


}
