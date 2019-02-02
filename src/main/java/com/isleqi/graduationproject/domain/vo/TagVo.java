package com.isleqi.graduationproject.domain.vo;

import com.isleqi.graduationproject.domain.Question;
import lombok.Data;

import java.util.List;

@Data
public class TagVo {
    Integer tagId;

    String tagName;

    List<Question> questionList;
}
