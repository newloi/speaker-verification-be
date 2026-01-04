package com.speaker_verification.SpeakerVerificationSystem.service;

import com.speaker_verification.SpeakerVerificationSystem.dto.request.SpeakerRequest;
import com.speaker_verification.SpeakerVerificationSystem.dto.response.SpeakerResponse;
import com.speaker_verification.SpeakerVerificationSystem.enums.ErrorCode;
import com.speaker_verification.SpeakerVerificationSystem.exception.AppException;
import com.speaker_verification.SpeakerVerificationSystem.mapper.SpeakerMapper;
import com.speaker_verification.SpeakerVerificationSystem.repository.SpeakerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SpeakerService {
    SpeakerRepository speakerRepository;
    SpeakerMapper speakerMapper;
    MqttService mqttService;

    public SpeakerResponse create(SpeakerRequest speakerRequest) {
        SpeakerResponse response;
        try {
            response = speakerMapper
                    .toSpeakerResponse(
                            speakerRepository.save(
                                    speakerMapper.toSpeaker(speakerRequest)
                            )
                    );
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.SPEAKER_EXISTED);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        mqttService.publishAsync("speaker/" + response.getId(), objectMapper.writeValueAsString(response));

        return response;
    }

    public SpeakerResponse get(String id) {
        var speaker = speakerRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.SPEAKER_NOT_EXISTED));

        return speakerMapper.toSpeakerResponse(speaker);
    }

    public List<SpeakerResponse> getAll() {
        return speakerRepository.findAllByOrderByFullNameAsc().stream().map(speakerMapper::toSpeakerResponse).toList();
    }

    public SpeakerResponse update(String id, SpeakerRequest speakerRequest) {
        var speaker = speakerRepository.findById(id)
                        .orElseThrow(() -> new AppException(ErrorCode.SPEAKER_NOT_EXISTED));
        speakerMapper.updateSpeaker(speaker, speakerRequest);

        try {
            var response = speakerMapper.toSpeakerResponse(speakerRepository.save(speaker));
            ObjectMapper objectMapper = new ObjectMapper();
            mqttService.publishAsync("speaker/" + response.getId(), objectMapper.writeValueAsString(response));

            return response;
        } catch (DataIntegrityViolationException e) {
            throw new AppException(ErrorCode.STUDENT_ID_USED);
        }
    }

    public void delete(String id) {
        var speaker = speakerRepository.findById(id)
                        .orElseThrow(() -> new AppException(ErrorCode.SPEAKER_NOT_EXISTED));
        speakerRepository.delete(speaker);

        mqttService.publishAsync("speaker/" + speaker.getId(), new String(new byte[0]));
    }

}
