package com.yzl.pojo;

import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
@Repository
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
