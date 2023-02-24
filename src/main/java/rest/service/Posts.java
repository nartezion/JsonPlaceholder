package rest.service;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import rest.model.Post;

import static rest.endpoint.Posts.POSTS;
import static rest.endpoint.Posts.POST_BY_ID;

public class Posts {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private RequestSpecification getDefaultRequestSpecification() {
        // Creating request specification using given()
        RequestSpecification requestSpecification = RestAssured.given();
        // Setting Base URI
        requestSpecification.baseUri(BASE_URL);
        // Specify to log all data from response in console
        requestSpecification.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        return requestSpecification;
    }

    public Response getPosts() {

        return getDefaultRequestSpecification()
                .when()
                .get(POSTS)
                .then()
                .extract().response();
    }

    public Response getPostById(String id) {

        return getDefaultRequestSpecification()
                .when()
                .pathParam("id", id)
                .get(POST_BY_ID)
                .then()
                .extract().response();
    }

    public Response createPost(Post post) {

        return getDefaultRequestSpecification()
                .when()
                .header("Content-type", "application/json; charset=UTF-8")
                .body(post)
                .post(POSTS)
                .then()
                .extract().response();
    }
}
