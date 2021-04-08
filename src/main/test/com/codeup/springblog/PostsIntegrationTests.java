package com.codeup.springblog;


import com.codeup.codeup_demo.CodeupDemoApplication;
import com.codeup.codeup_demo.models.Post;
import com.codeup.codeup_demo.models.User;
import com.codeup.codeup_demo.repo.PostRepository;
import com.codeup.codeup_demo.repo.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.servlet.http.HttpSession;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CodeupDemoApplication.class)
@AutoConfigureMockMvc
public class PostsIntegrationTests {

    private User testUser;
    private HttpSession httpSession;

    @Autowired
    private MockMvc mvc;

    @Autowired
    UserRepo userDao;

    @Autowired
    PostRepository postDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setup() throws Exception {

        testUser = userDao.findByUsername("testUser");

        // Creates the test user if not exists
        if(testUser == null){
            User newUser = new User();
            newUser.setUsername("testUser");
            newUser.setPassword(passwordEncoder.encode("pass"));
            newUser.setEmail("testUser@codeup.com");
            testUser = userDao.save(newUser);
        }

        // Throws a Post request to /login and expect a redirection to the Posts index page after being logged in
        httpSession = this.mvc.perform(post("/login").with(csrf())
                .param("username", "testUser")
                .param("password", "pass"))
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/posts"))
                .andReturn()
                .getRequest()
                .getSession();
    }
    @Test
    public void contextLoPosts() {
        // Sanity Test, just to make sure the MVC bean is working
        assertNotNull(mvc);
    }

    @Test
    public void testIfUserSessionIsActive() throws Exception {
        // It makes sure the returned session is not null
        assertNotNull(httpSession);
    }
    @Test
    public void testCreatePost() throws Exception {
        // Makes a Post request to /Posts/create and expect a redirection to the Post
        this.mvc.perform(
                post("/posts/create").with(csrf())
                        .session((MockHttpSession) httpSession)
                        // Postd all the required parameters to your request like this
                        .param("title", "test")
                        .param("body", "for sale")
    .param("img_src0", "https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcSpcceRUG3NVjbEJf8b-9aFXsaUmQwEC0u_ULFssCvy_Xf1LPQU8I2WOWqkNcM3Kn12ELDwUUIBvA&usqp=CAc")
                .param("img_src1", "https://images-na.ssl-images-amazon.com/images/I/71Ug9Bzu-YL._AC_SL1024_.jpg")
        )

                .andExpect(status().is3xxRedirection());

    }
    @Test
    public void testShowPost() throws Exception {

        // get the first Postd
        Post existingPost = postDao.findAll().get(0);

        // Makes a Get request to /Posts/{id} and expect a redirection to the Post show page
        this.mvc.perform(get("/posts/" + existingPost.getId()))
                .andExpect(status().isOk())
                // Test the dynamic content of the page
                .andExpect(content().string(containsString(existingPost.getBody())));
    }

    @Test
    public void testPostsIndex() throws Exception {
        Post existingPost = postDao.findAll().get(0);

        // Makes a Get request to /Posts and verifies that we get some of the static text of the Posts/index.html template and at least the title from the first Post is present in the template.
        this.mvc.perform(get("/posts"))
                .andExpect(status().isOk())
                // Test the static content of the page d
                .andExpect(content().string(containsString("Latest Posts")))
                // Test the dynamic content of the page
                .andExpect(content().string(containsString(existingPost.getTitle())));
    }
    @Test
    public void testEditPost() throws Exception {
        // Gets the first Post for tests purposes
        Post existingPost = postDao.findAll().get(0);

        // Makes a Post request to /Posts/{id}/edit and expect a redirection to the Post show page
        this.mvc.perform(
                post("/posts/" + existingPost.getId() + "/edit").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("title", "edited title")
                        .param("body", "edited body"))
                .andExpect(status().is3xxRedirection());

        // Makes a GET request to /Posts/{id} and expect a redirection to the Post show page
        this.mvc.perform(get("/posts/" + existingPost.getId()))
                .andExpect(status().isOk())
                // Test the dynamic content of the page
                .andExpect(content().string(containsString("edited title")))
                .andExpect(content().string(containsString("edited body")));
    }
    @Test
    public void testDeletePost() throws Exception {
        // Creates a test Post to be deleted
        this.mvc.perform(
                post("/posts/create").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("title", "Post to be deleted")
                        .param("body", "won't last long"))

                .andExpect(status().is3xxRedirection());

        // Get the recent Post that matches the title
        Post existingPost = postDao.findByTitle("Post to be deleted");

        // Makes a Post request to /Posts/{id}/delete and expect a redirection to the Posts index
        this.mvc.perform(
                post("/posts/" + existingPost.getId() + "/delete").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("id", String.valueOf(existingPost.getId())))
                .andExpect(status().is3xxRedirection());
    }
}