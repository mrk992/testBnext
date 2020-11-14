package com.test.technicalMarcos.domain.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NewUserBody {

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String lastName;

    @NotBlank
    @NotNull
    private String telephone;
}
