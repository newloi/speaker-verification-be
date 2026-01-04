package com.speaker_verification.SpeakerVerificationSystem.mapper;

import com.speaker_verification.SpeakerVerificationSystem.dto.request.SpeakerRequest;
import com.speaker_verification.SpeakerVerificationSystem.dto.response.SpeakerResponse;
import com.speaker_verification.SpeakerVerificationSystem.entity.Speaker;
import com.speaker_verification.SpeakerVerificationSystem.helper.Converter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", imports = {Converter.class})
public interface SpeakerMapper {
    @Mapping(target = "voiceEmbedding", expression = "java(Converter.toBytes(request.getVoiceEmbedding()))")
    Speaker toSpeaker(SpeakerRequest request);

    @Mapping(target = "voiceEmbedding", expression = "java(Converter.toFloats(speaker.getVoiceEmbedding()))")
    SpeakerResponse toSpeakerResponse(Speaker speaker);

    @Mapping(target = "voiceEmbedding", expression = "java(Converter.toBytes(request.getVoiceEmbedding()))")
    void updateSpeaker(@MappingTarget Speaker speaker, SpeakerRequest request);
}
