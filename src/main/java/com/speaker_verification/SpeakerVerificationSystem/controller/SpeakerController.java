package com.speaker_verification.SpeakerVerificationSystem.controller;

import com.speaker_verification.SpeakerVerificationSystem.dto.request.SpeakerRequest;
import com.speaker_verification.SpeakerVerificationSystem.dto.response.ApiResponse;
import com.speaker_verification.SpeakerVerificationSystem.dto.response.SpeakerResponse;
import com.speaker_verification.SpeakerVerificationSystem.service.SpeakerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/speakers")
public class SpeakerController {
    SpeakerService speakerService;

    @PostMapping
    ApiResponse<SpeakerResponse> createSpeaker(@Validated @RequestBody SpeakerRequest request) {
        return ApiResponse.<SpeakerResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .data(speakerService.create(request))
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<SpeakerResponse> getSpeaker(@PathVariable("id") String id) {
        return ApiResponse.<SpeakerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .data(speakerService.get(id))
                .build();
    }

    @GetMapping
    ApiResponse<List<SpeakerResponse>> getAllSpeakers() {
        return ApiResponse.<List<SpeakerResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .data(speakerService.getAll())
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<SpeakerResponse> updateSpeaker(@PathVariable("id") String id,
                                               @RequestBody SpeakerRequest request) {
        return ApiResponse.<SpeakerResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .data(speakerService.update(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteSpeaker(@PathVariable("id") String id) {
        speakerService.delete(id);
        return ApiResponse.<Void>builder()
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
