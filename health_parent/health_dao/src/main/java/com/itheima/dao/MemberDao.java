package com.itheima.dao;

import com.itheima.pojo.Member;

public interface MemberDao {
    Member selectMember(Member member);

    void addMember(Member member1);
}
