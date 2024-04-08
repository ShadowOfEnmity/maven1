package org.kostrikov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class AppTest {

    private MyJakarta myJakarta;
    private List<Technology> technologies = new ArrayList<>();
    private String dataLocation;

    private URL resource;

    @BeforeEach
    public void setUp() throws IOException {
        var properties = new Properties();
        try (InputStream resourceStream = ClassLoader.getSystemResourceAsStream("configuration.properties")) {
            properties.load(resourceStream);
            dataLocation = properties.getProperty("dataLocation");

            myJakarta = new MyJakarta();
            myJakarta.setVersion("10");
            myJakarta.setDescription("Jakarta 10 is a set of specifications");

            var technology1 = new Technology("Validation", "Validation provides a facility for validating objects");
            var technology2 = new Technology("Persistance", "Jakarta Persistence defines a standard for management of persistence and object/relational mapping");
            technologies.add(technology1);
            technologies.add(technology2);

//            properties.getProperty("dataLocation")
//            ClassLoader classLoader = getClass().getClassLoader();
//            resource = classLoader.getResource(dataLocation);
        }

    }


    @Test
    void writeToJsonTest() throws IOException {

//        assertThat(resource).isNotNull();
//
//        var path = resource.getFile().trim();

        assertThatThrownBy(() -> myJakarta.writeToJson("")).isInstanceOf(IOException.class);
        myJakarta.writeToJson(dataLocation);

    }

    @Test
    void updateTechnology() throws IOException {

//        assertThat(resource).isNotNull();

//        var path = resource.getFile().trim();
        myJakarta.writeToJson(dataLocation);

        for (Technology t : technologies) {
            myJakarta.updateTechnology(t);
        }

        var myJakarta2 = MyJakarta.readFromJson(dataLocation);
        assertThat(myJakarta).isEqualTo(myJakarta2);
        assertThat(myJakarta.getTechnologies()).containsExactlyElementsOf(myJakarta2.getTechnologies());
    }
}