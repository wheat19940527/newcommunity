package com.mqm.community.mapper;

import com.mqm.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}