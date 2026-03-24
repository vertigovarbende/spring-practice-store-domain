package com.deveyk.bookstore.common.controller.response;

import com.deveyk.bookstore.common.util.BsRandomUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    @Builder.Default
    private LocalDateTime time = LocalDateTime.now();
    @Builder.Default
    private String code = BsRandomUtil.generateUUID();
    @Builder.Default
    private final Boolean isSuccess = false;
    private String header;
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

    @Getter
    @RequiredArgsConstructor
    public enum Header {

        NOT_EXISTS_ERROR("NOT EXISTS ERROR"),
        VALIDATION_ERROR("VALIDATION ERROR"),
        CONFLICT_ERROR("CONFLICT ERROR");

        private final String name;

    }




}
