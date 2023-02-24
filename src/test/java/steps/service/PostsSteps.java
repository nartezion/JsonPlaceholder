package steps.service;

import io.restassured.response.Response;
import rest.model.Post;
import rest.service.Posts;

import java.util.List;

public class PostsSteps {

    private final Posts POSTS_SERVICE_METHODS = new Posts();

    public List<Post> getPosts() {

        Response response = POSTS_SERVICE_METHODS.getPosts();

        List<Post> listOfPosts = response.then()
                .statusCode(200)
                .extract().body()
                .jsonPath().getList(".", Post.class);

        return listOfPosts;
    }

    public Post getPostById(String id) {

        Response response = POSTS_SERVICE_METHODS.getPostById(id);

        Post post = response.then()
                .statusCode(200)
                .extract().as(Post.class);

        return post;
    }

    public Post createPost(Post post) {

        Response response = POSTS_SERVICE_METHODS.createPost(post);

        Post createdPost = response.then()
                .statusCode(201)
                .extract().as(Post.class);

        return createdPost;
    }
}
