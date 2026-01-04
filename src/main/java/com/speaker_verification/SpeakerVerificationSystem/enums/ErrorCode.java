package com.speaker_verification.SpeakerVerificationSystem.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    SPEAKER_EXISTED(
            HttpStatus.CONFLICT.value(),
            "Mẫu giọng nói của người này đã tồn tại.",
            HttpStatus.CONFLICT
    ),
    SPEAKER_NOT_EXISTED(
            HttpStatus.NOT_FOUND.value(),
            "Không tồn tại mẫu giọng nói của người này.",
            HttpStatus.NOT_FOUND
    ),
    STUDENT_ID_USED(
            HttpStatus.CONFLICT.value(),
            "Mã số sinh viên đã được sử dụng.",
            HttpStatus.CONFLICT
    ),
    NOT_BLANK(
            HttpStatus.BAD_REQUEST.value(),
            "Vui lòng điền hết các trường thông tin.",
            HttpStatus.BAD_REQUEST
    )
    ;

    int statusCode;
    String message;
    HttpStatus status;
}
