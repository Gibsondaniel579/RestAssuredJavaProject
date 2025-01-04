package api.endpoints;

public class Routes {
    //https://petstore.swagger.io/v2/user/
    public static String baseURL = "https://petstore.swagger.io/v2";
    //User module
    public static String postURL = baseURL + "/user";
    public static String getURL = baseURL + "/user/{username}";
    public static String updateURL = baseURL + "/user/{username}";
    public static String deleteURL = baseURL + "/user/{username}";

    //Store module

    public static String baseURLGOREST = "https://gorest.co.in/public/v2";
    //User module
    public static String postURLGOREST = baseURLGOREST + "/users";
    public static String getURLGOREST = baseURLGOREST + "/users/{id}";
    public static String updateURLGOREST = baseURLGOREST + "/users/{id}";
    public static String deleteURLGOREST = baseURLGOREST + "/users/{id}";

}
