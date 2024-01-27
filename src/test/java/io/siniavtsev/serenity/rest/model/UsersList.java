package io.siniavtsev.serenity.rest.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.util.ArrayList;

public class UsersList<E> extends ArrayList<E> {

    @SneakyThrows
    public String toJson() {
        var mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
