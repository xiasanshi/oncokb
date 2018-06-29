package org.mskcc.cbio.oncokb.config;

import com.monitorjbl.json.JsonViewSupportFactoryBean;
import io.sentry.spring.SentryExceptionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@ComponentScan(basePackages = "org.mskcc.cbio.oncokb")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".html");
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/");
        registry.addResourceHandler("/app/**").addResourceLocations("/app/");
        registry.addResourceHandler("/components/**").addResourceLocations("/components/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/scripts/**").addResourceLocations("/scripts/");
        registry.addResourceHandler("/styles/**").addResourceLocations("/styles/");
        registry.addResourceHandler("/views/**").addResourceLocations("/views/");
        registry.addResourceHandler("/data/**").addResourceLocations("/data/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/swagger-ui.html").setViewName("redirect:/api/v1/swagger-ui.html");
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("utf-8");
        multipartResolver.setMaxUploadSize(50000000);
        return multipartResolver;
    }

    @Bean
    public JsonViewSupportFactoryBean views() {
        return new JsonViewSupportFactoryBean();
    }

    protected ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
            "OncoKB APIs",
            "OncoKB, a comprehensive and curated precision oncology knowledge base, offers oncologists detailed, evidence-based information about individual somatic mutations and structural alterations present in patient tumors with the goal of supporting optimal treatment decisions.",
            "v1.0.0",
            "http://oncokb.org/#/terms",
            new Contact("OncoKB", "http://www.oncokb.org", "team@oncokb.org"),
            "Usage Terms",
            "http://oncokb.org/#/terms");
        return apiInfo;
    }

    @Bean
    public HandlerExceptionResolver sentryExceptionResolver() {
        // Exclude specific events https://stackoverflow.com/questions/48914391/avoid-reporting-broken-pipe-errors-to-sentry-in-a-spring-boot-application
        return new SentryExceptionResolver() {
            @Override
            public ModelAndView resolveException(HttpServletRequest request,
                                                 HttpServletResponse response,
                                                 Object handler,
                                                 Exception ex) {
                Throwable rootCause = ex;

                while (rootCause.getCause() != null && rootCause.getCause() != rootCause) {
                    rootCause = ex.getCause();
                }

                if (!rootCause.getMessage().contains("Broken pipe")
                    && !rootCause.getMessage().contains("Required request body content is missing")
                    && !rootCause.getMessage().contains("Required request body is missing")
                    && !rootCause.getMessage().contains("Failed to convert value of type 'java.lang.String' to required type 'java.lang.Integer'")
                    && !rootCause.getMessage().contains("Required String parameter ")
                    && !rootCause.getMessage().contains("Could not read document")
                    ) {
                    super.resolveException(request, response, handler, ex);
                }
                return null;
            }

        };
    }
}
