package org.project.task.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class UserDto {

    private Long Id;
    private String name;
    private String email;
    private String password;
}
