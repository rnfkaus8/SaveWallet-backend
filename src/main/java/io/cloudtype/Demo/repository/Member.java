package io.cloudtype.Demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Member extends JpaRepository<Member, Long> {
}
