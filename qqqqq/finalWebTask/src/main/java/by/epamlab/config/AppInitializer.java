package by.epamlab.config;

import by.epamlab.model.beans.CategoryType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletContext;
import java.io.File;
import java.net.URL;

public class AppInitializer implements WebApplicationInitializer {

    private final String TMP_FOLDER = "C:" + File.separator + "Users"
            + File.separator + "Windows" + File.separator + "tmp";
    private final int MAX_UPLOAD_SIZE = 10 * 1024*1024;


    @Override
    public void onStartup(ServletContext container) throws ServletException {
        var context = new AnnotationConfigWebApplicationContext();
        context.scan("by.epamlab");
        container.setAttribute("categories", CategoryType.values());

        container.addListener(new ContextLoaderListener(context));

        ServletRegistration.Dynamic dispatcher =
                container.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");


        //tmp dir initialising костыль, не знаю, как правильно...
        File uploadDir = new File(TMP_FOLDER);
        if (!uploadDir.exists()){
            uploadDir.mkdir();
        }

        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(TMP_FOLDER,
                MAX_UPLOAD_SIZE, MAX_UPLOAD_SIZE * 2, MAX_UPLOAD_SIZE / 2 );
        dispatcher.setMultipartConfig(multipartConfigElement);



    }
}
