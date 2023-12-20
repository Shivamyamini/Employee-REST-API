package com.employee.employee.SpringBootConfig;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;


@OpenAPIDefinition
@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI baseOpenAPI() {
        ApiResponse successfulSignIn = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("{\"code\" : 200, \"Status\" : \"Successful Sign In\", \"Message\" : \"Successful Sign In\"")))
        ).description("""
                The request has succeeded. The information returned with the response is dependent on the method used in the request, for example:
                                
                GET an entity corresponding to the requested resource is sent in the response;
                HEAD the entity-header fields corresponding to the requested resource are sent in the response without any message-body;
                POST an entity describing or containing the result of the action;
                TRACE an entity containing the request message as received by the end server.""");


        ApiResponse badRequest = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("{\"code\" : 400, \"Status\" : \"Bad Request\", \"Message\" : \"Bad Request\"")))
        ).description("""
                The request could not be understood by the server due to malformed syntax. The client SHOULD NOT repeat the request without modifications.""");

        ApiResponse notFound = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("{\"code\" : 404, \"Status\" : \"Not Found\", \"Message\" : \"Not Found\"")))
        ).description("""
                The server has not found anything matching the Request-URI. 
                No indication is given of whether the condition is temporary or permanent. 
                The 410 (Gone) status code SHOULD be used if the server knows, through some 
                internally configurable mechanism, that an old resource is permanently 
                unavailable and has no forwarding address. This status code is commonly used 
                when the server does not wish to reveal exactly why the request has been refused, 
                or when no other response is applicable.""");

        ApiResponse internalServerErrorAPI = new ApiResponse().content(
                new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE,
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("{\"code\" : 500, \"Status\" : \"Internal Server Error\", \"Message\" : \"Internal Server Error\"")))
        ).description("""
                The server encountered an unexpected condition which prevented it from fulfilling the request.""");


        Components components = new Components();
        components.addResponses("badRequest",badRequest);
        components.addResponses("internalServerErrorAPI",internalServerErrorAPI);
        components.addResponses("notFound",notFound);
        components.addResponses("successfulSignIn",successfulSignIn);

        return new OpenAPI()
                .components(components)
                .info(new Info().title("Human Resource Management System")
                        .version("1.0.0").description("This is the swagger documentation of the Employee Api. This has been made using OpenApi 3.0."));
    }

}
