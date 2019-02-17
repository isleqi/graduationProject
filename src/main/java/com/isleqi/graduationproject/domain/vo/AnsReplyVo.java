package com.isleqi.graduationproject.domain.vo;

import com.isleqi.graduationproject.domain.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class AnsReplyVo {

    private Integer id;

    private Integer ansCommentId;

    private Integer replyId;

    private Integer replyUserId;

    private Integer replyedUserId;

    private String replyComtent;

    private Integer likeNum;

    private Date creatTime;

    private User replyUser;

    private User replyedUser;

    private List<AnsReplyVo> replyVoList;

}
