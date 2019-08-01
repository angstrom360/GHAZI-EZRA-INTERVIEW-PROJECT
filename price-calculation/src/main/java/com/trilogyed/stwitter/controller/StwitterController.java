package com.trilogyed.stwitter.controller;

import com.trilogyed.stwitter.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*

                                #############################################################
                                #  The REST CONTROLLER IS USED TO NETWORK THE SERVICE LAYER #
                                #   METHODS TO THE CORRECT ENDPOINTS, FOR THE CLIENT USAGE. #
                                #                                                           #
                                #    *CACHING*: The following REST Controller methods       #
                                #    will be cached are:                                    #
                                #                   - Creating Product                         #
                                #                   - Getting Product by ID                    #
                                #    Whenever these REQUEST METHODS ARE called (GET / POST) #
                                #    they will be immediately cached for future reference!  #                                                              #
                                #                                                           #
                                #############################################################

 */

@RestController
@CacheConfig(cacheNames = {"posts"})
public class StwitterController {

    @Autowired
    ServiceLayer service;

    public StwitterController(ServiceLayer service) {
        this.service = service;
    }

    @CachePut(key = "#result.getPostId()")
    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public PostViewModel createPost(@RequestBody PostViewModel postViewModel){
        System.out.println("CREATING POST");
        return service.createPost(postViewModel);
    }

    @Cacheable
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PostViewModel getPost(@PathVariable int id){
        System.out.println("GETTING POST ID = " + id);
        return service.getPost(id);
    }


    @RequestMapping(value = "/posts/user/{poster_name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<PostViewModel> getBookByPosterName(@PathVariable String poster_name){
        System.out.println("GETTING POST BY POSTER NAME = " + poster_name);
        return service.getPostByPosterName(poster_name);
    }




}
