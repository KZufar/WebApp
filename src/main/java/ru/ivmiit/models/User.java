package ru.ivmiit.models;

import lombok.*;

/**
 * 05.10.2017
 * User
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class User {
    private Long id;
    private String name;
    private int age;

}
