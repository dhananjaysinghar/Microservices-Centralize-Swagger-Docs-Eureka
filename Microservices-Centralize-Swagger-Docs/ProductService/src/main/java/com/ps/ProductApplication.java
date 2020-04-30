package com.ps;

import java.util.stream.Stream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import com.ps.model.Product;
import com.ps.service.ProductService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableAutoConfiguration
public class ProductApplication {
  public static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(ProductApplication.class);

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(ProductApplication.class);
    Environment environment = app.run(args).getEnvironment();
    String port = environment.getProperty("server.port");
    logger.info("Application Started with port {} ", port);
    logger.info("API Document :  http://localhost:{}/swagger-ui.html", port);
    logger.info(
        "Database Details: http://localhost:{}/h2-console  : access with URl : jdbc:h2:mem:emp_db , UserName:sa, password :  ",
        port);

  }


  /**
   * Add Static test data at beginning of application
   * 
   * @param service
   * @return
   */
  @Bean
  public CommandLineRunner employees(ProductService service) {
    return args -> getStreamOfStaticProductData().forEach(service::addProduct);
  }


  private static Stream<Product> getStreamOfStaticProductData() {
    return Stream.of(new Product(null, "p_One", 1000), new Product(null, "p_Two", 2000),
        new Product(null, "p_Three", 3000));
  }


}
