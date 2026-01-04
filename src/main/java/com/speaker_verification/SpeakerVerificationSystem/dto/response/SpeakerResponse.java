package com.speaker_verification.SpeakerVerificationSystem.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpeakerResponse {
    String id;
    String studentId;
    String fullName;
    float[] voiceEmbedding;
    int version;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
