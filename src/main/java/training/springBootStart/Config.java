package training.springBootStart;

import org.flywaydb.core.Flyway;
import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@ComponentScan(basePackageClasses = Config.class)
@PropertySource("classpath:/application.properties")
@EnableTransactionManagement
public class Config {

    @Autowired
    private Environment environment;

    @Bean
    public DataSource ds() {
        try {
            MariaDbDataSource ds = new MariaDbDataSource();
            ds.setUrl(environment.getProperty("spring.datasource.url"));
            ds.setUser(environment.getProperty("spring.datasource.username"));
            ds.setPassword(environment.getProperty("spring.datasource.password"));
            return ds;
        } catch (SQLException se) {
            throw new IllegalStateException("Can not create datasource", se);
        }
    }

    @Bean
    public Flyway flyway(){
        Flyway flyway = Flyway.configure()
                .locations("/db/migration/employee")
                .dataSource(ds()).load();
        flyway.clean();
        flyway.migrate();

        return flyway;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource){
        LocalContainerEntityManagerFactoryBean emfBean =
                new LocalContainerEntityManagerFactoryBean();
        emfBean.setJpaVendorAdapter(jpaVendorAdapter());
        emfBean.setDataSource(dataSource);
        emfBean.setPackagesToScan("training.springBootStart");
        return emfBean;
    }

}
