package Gnbproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class ConfigUtility {
    public String getProperty(String pPropertyKey) {
        try {
            Properties obj = new Properties();
            FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\src/main/resources/application.properties");
            obj.load(objfile);
            return obj.getProperty(pPropertyKey);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}