package kz.enu.kztrdictionary.config;

import com.mysql.jdbc.jdbc2.optional.*;
import kz.enu.kztrdictionary.dao.dictionary.*;
import kz.enu.kztrdictionary.dao.kzword.*;
import kz.enu.kztrdictionary.dao.trword.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.*;
import org.springframework.web.servlet.view.*;

@Configuration
@PropertySources(value = {@PropertySource("classpath:db.properties"), @PropertySource("classpath:log4j.properties")})
@ComponentScan(basePackages = {"kz.enu.kztrdictionary.*"})
@EnableWebMvc
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private Environment env;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/").setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new GzipResourceResolver()).addResolver(new PathResourceResolver());
    }

    @Bean
    public ViewResolver getViewResolver() throws Exception {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Bean
    public MysqlDataSource getDataSource() {
        MysqlDataSource dataSource = null;
        try {
            dataSource = new MysqlDataSource();
            dataSource.setURL(env.getProperty("jdbc.url"));
            dataSource.setUser(env.getProperty("jdbc.username"));
            dataSource.setPassword(env.getProperty("jdbc.password"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataSource;
    }

    @Bean
    KzWordDAO kzWordDAO() {
        return new JdbcKzWordDAO(getDataSource());
    }

    @Bean
    TrWordDAO trWordDAO() {
        return new JdbcTrWordDAO(getDataSource());
    }

    @Bean
    DictionaryDAO dictionaryDAO() {
        return new JdbcDictionaryDAO(getDataSource());
    }
}
