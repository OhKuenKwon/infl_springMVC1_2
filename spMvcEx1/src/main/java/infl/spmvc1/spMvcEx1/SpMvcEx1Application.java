package infl.spmvc1.spMvcEx1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpMvcEx1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpMvcEx1Application.class, args);
	}
}
