package com.deveyk.bookstore.common.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String title;
    private String path;
    @Builder.Default
    private Map<String, String> properties = new HashMap<>();

    public void addProperty(String key, String value) {
        if (properties == null) {
            properties = Map.of(key, value);
        } else {
            properties.put(key, value);
        }
    }


}
