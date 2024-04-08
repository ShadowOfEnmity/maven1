package org.kostrikov;


import lombok.*;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Technology {
    private String name;
    @Setter
    private String description;
}
