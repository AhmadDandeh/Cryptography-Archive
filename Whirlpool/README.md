# 📌 Project Context: Whirlpool Hash Suite (Legacy 2017)
This project is a Java-based implementation of the **Whirlpool Hash Function**, originally developed in 2017. It has been preserved and migrated to a modern Maven environment as part of a historical code recovery and documentation initiative.

Whirlpool is a cryptographic hash function that produces a 512-bit digest, based on a substantially modified version of the Advanced Encryption Standard (AES).

## 🛠️ Reverse Engineering & Modernization
The source code was reconstructed from legacy `.class` and `.jar` files. During this recovery process, the following modernizations were implemented:
* **Lambda Refactoring:** Replaced obsolete anonymous inner classes (`WhirlpoolJFrame$1`, `$2`) with **Java 8+ Lambda Expressions** to streamline event handling and GUI thread management.
* **Architecture Migration:** Successfully moved from a standalone flat structure to a structured **Maven Multi-Module** environment.
* **JDK Compatibility:** Updated the core logic and GUI components to ensure seamless execution on modern JDKs (17+).

## ⚠️ Current Status: "Fully Functional Archive"
> **Note for Reviewers:** Unlike standard de-compiled code, this module has been manually fixed to ensure a **fully functional GUI**. It serves as a historical documentation of the Whirlpool hashing implementation (found in `helpFun.java`) while remaining executable for educational demonstrations. Priority was given to refactoring the event-handling logic to ensure seamless interaction between the UI and the cryptographic engine.

## 🧬 Core Logic (Algorithm)
The cryptographic implementation in `helpFun.java` faithfully executes the Whirlpool hashing rounds:
* **W-Block Cipher:** The underlying dedicated 512-bit block cipher.
* **State Matrix Operations:** Full implementation of `SubBytes`, `ShiftColumns`, and `MixRows` equivalents.
* **Padding Logic:** Handles message padding to ensure the input is a multiple of 512 bits, including the length-strengthening bit.
* **Format Utilities:** Includes custom logic to format 512-bit binary output and hexadecimal strings for better readability.

## 🚀 How to Run
1. Navigate to the `crypto-whirlpool` directory.
2. Run the main class: `com.ahmad.crypto.whirlpool.WhirlpoolJFrame`.
3. Enter any text to see the 512-bit hash generated in real-time in both Hexadecimal and Binary formats.