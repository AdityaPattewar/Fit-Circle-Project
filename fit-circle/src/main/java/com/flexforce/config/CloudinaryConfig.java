package com.flexforce.config;

import java.util.HashMap;
import java.util.Map;

import com.cloudinary.Cloudinary;

public class CloudinaryConfig {
    public static Cloudinary cloudinary;
    public static Cloudinary getCloudinary(){
        
        if(cloudinary == null){

            Map<String, Object> config = new HashMap<>();

            config.put("cloud_name","d5lpzamm");
            config.put("api_key","195692332128887");
            config.put("api_secret","atTg1-ZNq2_tjaggdCYfSg4k_AI");
            config.put("secure",true);

            cloudinary = new Cloudinary(config);
        }
        return cloudinary;
    }
    
}