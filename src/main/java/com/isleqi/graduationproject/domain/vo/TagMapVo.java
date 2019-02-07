package com.isleqi.graduationproject.domain.vo;

import com.isleqi.graduationproject.domain.Question;
import com.isleqi.graduationproject.domain.Tag;
import lombok.Data;

import java.util.List;

@Data
public class TagMapVo {
    private Integer tagMapId;
    private  String quesId;
    private  String tagId;
    private Tag tag;
}
