package com.jh22.airbnb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    /**
     * We can have multiple resource servers in place. This ties this resource server to this application
     */
    private static final String RESOURCE_ID = "resource_id";

    /**
     * Tries this application to the resource server
     *
     * @param resources the resource server
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources)
    {
        // stateless refers to only working with access tokens. Testing using a different schema
        // so stateless must be false.
        resources.resourceId(RESOURCE_ID)
                .stateless(false);
    }

    /**
     * This method configures which roles can access which endpoints
     *
     * @param http Our HttpSecurity object that is maintains by Spring
     * @throws Exception in case the configurations fails
     */
    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/",
                        "/h2-console/**",
                        "/swagger-resources/**",
                        "/swagger-resource/**",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                        "/webjars/**",
                        "/createnewuser")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/users/**",
                            "/properties/**",
                            "/cards/**",
                            "/roles/**")
                .hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/users/**",
                        "/properties/**",
                        "/cards/**",
                        "/roles/**")
                .hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/users/**",
                        "/cards/**",
                        "/roles/**")
                .hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/users/**",
                        "/properties/**",
                        "/cards/**",
                        "/roles/**")
                .hasAnyRole("ADMIN")
                .antMatchers("/users/**",
                        "/properties/**",
                        "/cards/**",
                        "/logout")
                .authenticated()
//                GET ALL PROPERTY LISTINGS 
                .antMatchers(HttpMethod.GET, "/properties/**")
                .hasAnyRole("RENTER", "OWNER", "USER", "ADMIN")
//                ADD A NEW PROPERTY TO WEBSITE AS A LISTING
                .antMatchers(HttpMethod.POST, "/properties/property")
                .hasAnyRole("ADMIN", "OWNER")
//                UPDATE POSTED PROPERTY INFORMATION
                .antMatchers(HttpMethod.PATCH, "/properties/property")
                .hasAnyRole("ADMIN", "OWNER")
//                GET THEIR PERSONAL USER ACCOUNT INFO
                .antMatchers(HttpMethod.GET, "/users/user")
                .hasAnyRole("RENTER", "OWNER", "USER", "ADMIN")
//                UPDATE THEIR OWN ACCOUNT INFO
                .antMatchers(HttpMethod.PATCH, "/users/user")
                .hasAnyRole("RENTER", "OWNER", "USER", "ADMIN")
//                DELETE THEIR OWN ACCOUNT
                .antMatchers(HttpMethod.DELETE, "/users/user")
                .hasAnyRole("RENTER", "OWNER", "USER", "ADMIN")
//              DO I NEED TO PUT IN ADMIN IF I GAVE PERMISSION PRIOR


    }
}
