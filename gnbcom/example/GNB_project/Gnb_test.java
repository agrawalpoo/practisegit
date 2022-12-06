package gnbcom.example.GNB_project;


import Gnbproject.GNB_newcode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Gnb_test {

    public static void main(String args[]){
        SpringApplication.run(Gnb_test.class, args);

      GNB_newcode gnb_newcode = new GNB_newcode();
        gnb_newcode.getStart();

    }
}
