package com.wisedevlife.whytalkmessage.repository;

import com.wisedevlife.whytalkmessage.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

}
