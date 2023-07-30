package io.cloudtype.Demo.repository;

import io.cloudtype.Demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
