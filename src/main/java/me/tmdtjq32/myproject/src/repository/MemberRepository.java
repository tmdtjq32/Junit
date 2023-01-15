package me.tmdtjq32.myproject.src.repository;

import me.tmdtjq32.myproject.src.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    @Override
    Optional<Member> findById(Long memberId) throws IllegalArgumentException;
}
