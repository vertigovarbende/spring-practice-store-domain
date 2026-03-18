package com.deveyk.bookstore.common.controller.request;

import com.deveyk.bookstore.common.model.Pagination;
import com.deveyk.bookstore.common.model.Sorting;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@Validated
public abstract class BsPaginationRequest {

    @NotNull
    @Valid
    private Pagination pagination;

    private Sorting sorting;

    public abstract boolean isOrderPropertyAccepted();

    public boolean isPropertyAccepted(final Set<String> acceptedProperties) {

        if (this.sorting == null)
            return true;
        if (StringUtils.isBlank(sorting.getProperty()) || sorting.getDirection() == null)
            return true;
        return acceptedProperties.contains(sorting.getProperty());

    }

}
