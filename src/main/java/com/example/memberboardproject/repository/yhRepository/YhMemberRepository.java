package com.example.memberboardproject.repository.yhRepository;

import com.example.memberboardproject.dto.yhdDto.YhMemberDTO;
import com.example.memberboardproject.entity.yhEntity.YhBoardEntity;
import com.example.memberboardproject.entity.yhEntity.YhMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface YhMemberRepository extends JpaRepository<YhMemberEntity, Long> {
    //  select * from member_table where member_email=? and member_password=?
    Optional<YhMemberEntity> findByMemberEmailAndMemberPassword(String memberEmail, String memePassword);

    YhMemberEntity findByMemberEmail(String memberEmail);


}
