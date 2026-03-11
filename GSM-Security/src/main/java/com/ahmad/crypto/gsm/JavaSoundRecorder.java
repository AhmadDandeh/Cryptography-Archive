package com.ahmad.crypto.gsm;

import javax.sound.sampled.*;
import java.io.*;

/**
 * Utility class for recording and playing audio samples.
 * Modernized with Lambda threads.
 */
public class JavaSoundRecorder {
    private static long recordTime = 6000L; // Default 6 seconds
    private static String fileName = "RecordAudio.wav";
    private final AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
    private TargetDataLine line;

    /**
     * Standard GSM/Telephony Audio Format:
     * 8000Hz, 16-bit, Mono is more standard,
     * but we'll keep it close to your original 44.1kHz for quality.
     */
    public static AudioFormat getAudioFormat() {
        float sampleRate = 44100.0F;
        int sampleSizeInBits = 16; // 16-bit provides better depth for crypto samples
        int channels = 1;          // Mono is better for encryption logic
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }

    public static String getF() { return fileName; }
    public static void setF(String f) { fileName = f; }

    // --- Recording Logic ---

    void startRecording() {
        try {
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                System.err.println("Line not supported");
                return;
            }

            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            System.out.println("Recording started...");
            AudioInputStream ais = new AudioInputStream(line);

            File wavFile = new File(fileName);
            AudioSystem.write(ais, fileType, wavFile);

        } catch (LineUnavailableException | IOException ex) {
            System.err.println("Recording Error: " + ex.getMessage());
        }
    }

    void stopRecording() {
        if (line != null) {
            line.stop();
            line.close();
            System.out.println("Recording finished.");
        }
    }

    /**
     * Main entry point to start recording for X milliseconds
     */
    public static void ma(int durationMs) {
        JavaSoundRecorder recorder = new JavaSoundRecorder();
        recordTime = durationMs;

        // Thread to stop recording after the specified time
        Thread stopper = new Thread(() -> {
            try {
                Thread.sleep(recordTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            recorder.stopRecording();
        });

        stopper.start();
        // Start recording in the current thread (it blocks until line is closed)
        new Thread(recorder::startRecording).start();
    }

    // --- Playback Logic ---

    public static void maa(String filePath) {
        // Simplified play call
        play(filePath);
    }

    public static void play(String filePath) {
        try {
            File audioFile = new File(filePath);
            if (!audioFile.exists()) {
                System.err.println("File not found: " + filePath);
                return;
            }

            AudioInputStream in = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(in);

            // Add listener to close the clip after playback finishes
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });

            clip.start();
            System.out.println("Playing: " + filePath);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Playback Error: " + e.getMessage());
        }
    }
}