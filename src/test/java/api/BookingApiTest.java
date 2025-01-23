package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

class BookingApiTest {

    private static String token;

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    @Order(1)
    void testHealthCheck() {
        given()
                .log().all()
                .when()
                .get("/ping")
                .then()
                .statusCode(201)
                .log().all();
    }

    @Test
    @Order(2)
    void testCreateToken() {
        String requestBody = """
                    {
                      "username": "admin",
                      "password": "password123"
                    }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .log().all()
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .log().all();
    }


    @Test
    @Order(3)
    public void testGetAllBookings() {
        given()
                .log().all()
                .when()
                .get("/booking")
                .then()
                .statusCode(200)
                .log().all()
                .body("bookingid", not(empty()));
    }

    @Test
    @Order(4)
    public void testCreateBooking() {
        String requestBody = """
                {
                  "firstname": "Alper",
                  "lastname": "Caktug",
                  "totalprice": 123,
                  "depositpaid": true,
                  "bookingdates": {
                    "checkin": "2024-07-01",
                    "checkout": "2024-07-10"
                  },
                  "additionalneeds": "Breakfast"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .log().all()
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .log().all()
                .body("booking.firstname", equalTo("Alper"))
                .body("booking.lastname", equalTo("Caktug"));
    }

    @Test
    @Order(5)
    public void testGetBookingById() {
        int bookingId = createBookingAndReturnId();

        given()
                .log().all()
                .when()
                .get("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .log().all()
                .body("firstname", equalTo("Alper"))
                .body("lastname", equalTo("Caktug"));
    }

    @Test
    @Order(6)
    public void testUpdateBooking() {
        token = generateToken();
        int bookingId = createBookingAndReturnId();

        String updatedBody = """
                {
                  "firstname": "Alper",
                  "lastname": "Caktug",
                  "totalprice": 150,
                  "depositpaid": false,
                  "bookingdates": {
                   "checkin": "2024-07-01",
                    "checkout": "2024-07-10"
                  },
                  "additionalneeds": "Dinner"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(updatedBody)
                .log().all()
                .when()
                .put("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .log().all()
                .body("firstname", equalTo("Alper"))
                .body("lastname", equalTo("Caktug"))
                .body("totalprice", equalTo(150));
    }

    @Test
    @Order(7)
    public void testDeleteBooking() {
        token = generateToken();
        int bookingId = createBookingAndReturnId();

        given()
                .header("Cookie", "token=" + token)
                .log().all()
                .when()
                .delete("/booking/" + bookingId)
                .then()
                .statusCode(201)
                .log().all();
    }


    @Test
    @Order(8)
    public void testUnauthorizedAccessUpdate() {
        int bookingId = createBookingAndReturnId();

        String updatedBody = """
            {
              "firstname": "Alper",
              "lastname": "Caktug",
              "totalprice": 150,
              "depositpaid": false,
              "bookingdates": {
               "checkin": "2024-07-01",
                "checkout": "2024-07-10"
              },
              "additionalneeds": "Dinner"
            }
            """;

        given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + "wrongToken")
                .body(updatedBody)
                .log().all()
                .when()
                .put("/booking/" + bookingId)
                .then()
                .statusCode(403)
                .log().all();
    }

    private static String generateToken() {
        String requestBody = """
                    {
                      "username": "admin",
                      "password": "password123"
                    }
                """;

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract()
                .response();

        return response.jsonPath().getString("token");
    }

    private int createBookingAndReturnId() {
        String requestBody = """
                {
                  "firstname": "Alper",
                  "lastname": "Caktug",
                  "totalprice": 123,
                  "depositpaid": true,
                  "bookingdates": {
                    "checkin": "2024-07-01",
                    "checkout": "2024-07-10"
                  },
                  "additionalneeds": "Breakfast"
                }
                """;

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract()
                .response();

        return response.jsonPath().getInt("bookingid");
    }
}
