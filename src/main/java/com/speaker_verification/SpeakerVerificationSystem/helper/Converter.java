package com.speaker_verification.SpeakerVerificationSystem.helper;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class Converter {

    public static byte[] toBytes(float[] array) {
        ByteBuffer buffer = ByteBuffer.allocate(array.length * 4);
        for(float v : array) {
            buffer.putFloat(v);
        }

        return buffer.array();
    }

    public static float[] toFloats(byte[] array) {
        FloatBuffer buffer = ByteBuffer.wrap(array).asFloatBuffer();
        float[] floatArray = new float[buffer.remaining()];
        buffer.get(floatArray);

        return floatArray;
    }

}
