package com.sparta.memoproject.repository;

import com.sparta.memoproject.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
   Optional<Member> findByNickname(String nickname);
   boolean existsByNickname(String nickname);
   boolean existsByPassword(String password);
}