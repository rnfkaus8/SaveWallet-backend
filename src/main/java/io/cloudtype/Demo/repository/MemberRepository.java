package io.cloudtype.Demo.repository;

import io.cloudtype.Demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);

    Optional<Member> findByDeviceId(String deviceId);
}
