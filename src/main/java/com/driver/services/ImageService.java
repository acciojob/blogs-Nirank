package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image(blogId,description,dimensions);
        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;

    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        String [] scr = screenDimensions.split("X");
        Image image = imageRepository2.findById(id).get();

        String imageDimensions = image.getDimensions();
        String [] img = imageDimensions.split("X");

        int sLen = Integer.parseInt(scr[0]);
        int sBrd = Integer.parseInt(scr[1]);

        int imgLen = Integer.parseInt(img[0]);
        int imgBrd = Integer.parseInt(img[1]);


        return (sLen/imgLen) * (sBrd/imgBrd);
    }
}
