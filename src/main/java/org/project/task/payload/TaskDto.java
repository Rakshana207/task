package org.project.task.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class TaskDto {

    private Long id;
    private String taskname;

}
