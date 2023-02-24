package tests;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import rest.model.Post;
import steps.service.PostsSteps;

public class Posts {

    private final PostsSteps POSTS_STEPS = new PostsSteps();

    @Test
    public void getPosts() {

        val listOfPosts = POSTS_STEPS.getPosts();

        Assertions.assertThat(listOfPosts.size()).isPositive();
    }

    @Test
    public void getPostById() {

        Post expectedPost = new Post();

        expectedPost.setUserId(1);
        expectedPost.setId(1);
        expectedPost.setTitle("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
        expectedPost.setBody("quia et suscipit\n" +
                "suscipit recusandae consequuntur expedita et cum\n" +
                "reprehenderit molestiae ut ut quas totam\n" +
                "nostrum rerum est autem sunt rem eveniet architecto");

        val actualPost = POSTS_STEPS.getPostById("1");

        Assertions.assertThat(actualPost.getId()).isEqualTo(expectedPost.getId());
        Assertions.assertThat(actualPost.getBody()).isEqualTo(expectedPost.getBody());
        Assertions.assertThat(actualPost.getTitle()).isEqualTo(expectedPost.getTitle());
        Assertions.assertThat(actualPost.getUserId()).isEqualTo(expectedPost.getUserId());
    }

    @Test
    public void createPost() {

        Post expectedPost = new Post();

        expectedPost.setUserId(15175);
        expectedPost.setTitle("My title");
        expectedPost.setBody("My body");

        val createdPost = POSTS_STEPS.createPost(expectedPost);

        val createPostId = String.valueOf(createdPost.getId());

        val actualPost = POSTS_STEPS.getPostById(createPostId);

        Assertions.assertThat(actualPost).isEqualTo(expectedPost);
    }
}
