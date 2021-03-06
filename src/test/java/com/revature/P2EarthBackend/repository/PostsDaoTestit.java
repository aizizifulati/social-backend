package com.revature.P2EarthBackend.repository;

import com.revature.P2EarthBackend.models.Posts;
import com.revature.P2EarthBackend.models.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PostsDaoTestIT {

    @Autowired
    PostsDao postsDao;

    @Autowired
    UsersDao usersDao;


    @Test
    void findAllByUserId() {
        //arrange
        Users user1 = new Users("archer01","123456","Sterling","Archer","","archer01@email.com");
        Users user2 =usersDao.save(user1);



        //act
        Posts expectedResult =new Posts("post_description","post_img",user2);
        Posts actualResult =postsDao.save(expectedResult);

        //assert
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void findAllByPostId() {
        //arrange
        Users user1 = new Users(1,"archer01","123456","Sterling","Archer","","archer01@email.com");
        usersDao.save(user1);
        Posts post1 =new Posts("post_description1","post_img1",user1);
        Posts post2 =new Posts("post_description2","post_img2",user1);
        Posts postFromDB1 =postsDao.save(post1);
        Posts postFromDB2 =postsDao.save(post2);


        //act

        Posts actualResult=postsDao.findAllByPostId(post1.getPost_id());
        Posts expectedResult=post1;


        //assert
        assertEquals(expectedResult,actualResult);


    }
}