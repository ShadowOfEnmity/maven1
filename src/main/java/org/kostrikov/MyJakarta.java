package org.kostrikov;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ToString
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(exclude = "technologies")
public class MyJakarta {
    private String version;
    private String description;
    @Setter(AccessLevel.NONE)
    @JsonSerialize
    @JsonDeserialize
    private List<Technology> technologies = new ArrayList<>();
    @Setter(AccessLevel.PRIVATE)
    @Getter(AccessLevel.NONE)
    private String path;

    public List<Technology> getTechnologies() {
        return Collections.unmodifiableList(technologies);
    }

    public static MyJakarta readFromJson(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        var newObject = mapper.readValue(new File(path), MyJakarta.class);
        newObject.setPath(path);
        return newObject;
    }

    void writeToJson(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File(path), this);
        this.setPath(path);
    }

    void updateTechnology(Technology technology) throws IOException {
        this.technologies.removeIf(t -> t.getName().equals(technology.getName()));
        this.technologies.add(technology);
        writeToJson(path);
    }
}
