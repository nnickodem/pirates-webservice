package lootweb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebMvc
@EnableMBeanExport(registration = RegistrationPolicy.REPLACE_EXISTING)
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean(name = "freeMarkerViewResolver")
	public ViewResolver getFreeMakerViewResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setSuffix(".ftl");
		resolver.setPrefix("");
		resolver.setCache(true);
		return resolver;
	}

	@Bean(name = "viewResolver")
	public ViewResolver contentNegotiatingViewResolver( ContentNegotiationManager manager) {
		List<ViewResolver> resolvers = Arrays.asList(getFreeMakerViewResolver());
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setViewResolvers(resolvers);
		resolver.setContentNegotiationManager(manager);
		return resolver;
	}
}