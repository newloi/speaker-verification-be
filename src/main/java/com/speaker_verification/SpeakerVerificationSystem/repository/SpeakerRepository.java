package com.speaker_verification.SpeakerVerificationSystem.repository;

import com.speaker_verification.SpeakerVerificationSystem.entity.Speaker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, String> {
    Optional<Speaker> findById(String id);
    Page<Speaker> findAll(Pageable pageable);
}
