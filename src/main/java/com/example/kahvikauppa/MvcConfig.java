package com.example.kahvikauppa;

import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    String dirName = "images";
    String uploadPath = Paths.get(dirName).toFile().getAbsolutePath();

    if (dirName.startsWith("../"))
      dirName = dirName.replace("../", "");

    // Removing any leading slash in case it's already present in the dirName
    if (uploadPath.startsWith("/"))
      uploadPath = uploadPath.substring(1);

    // Expose the upload folder to show images in the browser
    registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + uploadPath + "/");
  }

  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("login");
  }
}
