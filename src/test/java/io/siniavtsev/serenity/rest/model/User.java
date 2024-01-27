package io.siniavtsev.serenity.rest.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    Integer id;
    String userName;

    @SneakyThrows
    public String toJson() {
        var mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
