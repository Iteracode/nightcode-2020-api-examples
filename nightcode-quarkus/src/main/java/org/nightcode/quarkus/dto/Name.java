package org.nightcode.quarkus.dto;

import javax.validation.constraints.NotEmpty;

public class Name {
    @NotEmpty
    private String name;

    public Name() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
