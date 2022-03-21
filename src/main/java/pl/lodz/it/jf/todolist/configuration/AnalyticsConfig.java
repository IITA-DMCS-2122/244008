package pl.lodz.it.jf.todolist.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "analyticsManagerFactory",
        transactionManagerRef = "analyticsTransactionManager",
        basePackages = {"pl.lodz.it.jf.todolist.domain.analytics"}
)
public class AnalyticsConfig {

    @Autowired
    private Environment env;

    @Bean(name = "analyticsDataSource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .username(env.getProperty("analytics.datasource.username"))
                .password(env.getProperty("analytics.datasource.password"))
                .url(env.getProperty("analytics.datasource.url"))
                .build();
    }

    @Bean(name = "analyticsManagerFactory")
    public LocalContainerEntityManagerFactoryBean userEntityManager() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.POSTGRESQL);
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("pl.lodz.it.jf.todolist.domain.analytics");
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        em.setJpaProperties(properties);

        return em;
    }

    @Bean(name = "analyticsTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("analyticsManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

}
