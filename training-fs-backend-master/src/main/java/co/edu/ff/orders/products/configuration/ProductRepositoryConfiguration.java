package co.edu.ff.orders.products.configuration;

import co.edu.ff.orders.products.repositories.InMemoryProductRepository;
import co.edu.ff.orders.products.repositories.ProductRepository;
import co.edu.ff.orders.products.repositories.SqlProductRepository;
import co.edu.ff.orders.user.repositories.ImMemoryUserRepository;
import co.edu.ff.orders.user.repositories.SqlUserRepository;
import co.edu.ff.orders.user.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

@Configuration
public class ProductRepositoryConfiguration {

    @Bean
    @Profile({"dev", "prod"})
    public ProductRepository productRepositoryRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("PRODUCTS")
                .usingGeneratedKeyColumns("id");

        return new SqlProductRepository(jdbcTemplate, simpleJdbcInsert);
    }
/*
    @Bean
    @Profile({"dev-test"})
    public ProductRepository productRepositoryRepository() {
        return new InMemoryProductRepository();
    }*/
}
