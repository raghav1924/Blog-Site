package com.blog.userApp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private String title,content,imageUrl,email;
    private List<String> commentList;
}
