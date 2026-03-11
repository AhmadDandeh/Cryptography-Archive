# 📌 Project Context: GSM Security Suite (A3, A8, & A5/1 Simulation)

This project is a Java-based simulation of the core security protocols used in GSM (2G) mobile networks. It provides a comprehensive implementation of the authentication and encryption lifecycle, from SIM card validation to real-time voice data encryption.

Originally developed as a legacy simulation, this project has been reconstructed and migrated to a modern environment to serve as a functional demonstration of mobile cryptographic standards.

## 🛠️ Reverse Engineering & Modernization
The source code was audited and refactored to resolve structural inconsistencies and ensure compatibility with modern development standards:
* **Lambda Refactoring:** Legacy anonymous action listeners were replaced with **Java 8+ Lambda Expressions**, streamlining the interaction between the GUI and the cryptographic engines (`A3`, `A8`, and `A5/1`).
* **GUI Stabilization:** The interface was completely rebuilt using a precise `GroupLayout` manager to match the original aesthetic (Cyan-on-Blue theme) while fixing component alignment issues that caused runtime exceptions in modern JDKs.
* **Bridge Logic:** Implemented specialized data-conversion methods in `A5Fun.java` to bridge the gap between raw audio byte streams and the bit-level requirements of the LFSR registers.

## ⚠️ Current Status: "Fully Functional Simulation"
> **Note for Reviewers:** This is a high-fidelity simulation. Unlike static implementations, this suite features a **Live Audio Processor**. It can record real-time audio from the system microphone, convert it into a binary stream, and apply the A5/1 cipher in real-time. The GUI provides three distinct data areas (Plain, Cipher, and Decrypted) for immediate verification of the encryption's integrity.

## 🧬 Core Logic (GSM Algorithms)
The project faithfully implements the three pillars of GSM security:
* **Comp128 (A3/A8):** Handles the challenge-response mechanism. It processes a 128-bit **Ki** (Individual Subscriber Key) to generate the **SRES** (Signed Response) and the 64-bit **Kc** (Session Key).
* **A5/1 Stream Cipher:** A bit-level implementation using three Linear Feedback Shift Registers (LFSRs) of sizes 19, 22, and 23 bits. It includes the **Majority Function** clocking logic to ensure cryptographic non-linearity.
* **XOR Stream Processing:** Executes a bitwise XOR between the generated key stream and the digitized audio frames, mimicking the actual over-the-air encryption used in cellular towers.

## 🚀 How to Run
1. Navigate to the main class: `com.ahmad.crypto.gsm.A51Algorithim`.
2. **Phase 1 (Authentication):** Enter a 128-bit Binary **Ki** and press **A3**, then **A8** to generate the Session Key.
3. **Phase 2 (Setup):** Enter the **Frame Counter** (22-bit) and the recording duration in seconds.
4. **Phase 3 (Execution):** Press **RECORD** to capture audio, then **A5/1 ENCRYPTION** to view the ciphered output.
5. **Phase 4 (Verification):** Press **DESCRIPTION** to decrypt and verify that the output matches the original plain text.