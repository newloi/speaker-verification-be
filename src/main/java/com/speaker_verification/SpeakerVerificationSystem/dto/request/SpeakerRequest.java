package com.speaker_verification.SpeakerVerificationSystem.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpeakerRequest {
    @NotBlank(message = "NOT_BLANK")
    String studentId;
    @NotBlank(message = "NOT_BLANK")
    String fullName;
    @NotNull(message = "NOT_BLANK")
    float[] voiceEmbedding;
}
