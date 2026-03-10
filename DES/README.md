# 📌 Project Context: DES Encryption Suite (Legacy 2014)

This project is a Java-based implementation of the **Data Encryption Standard (DES)**, originally developed in 2014. It has been preserved and migrated to a modern Maven environment as part of a historical code recovery and documentation initiative.

## 🛠️ Reverse Engineering & Modernization
The source code was reconstructed from legacy `.class` files. During this process, the following improvements were made:
- **Event Handling Refactoring:** Replaced obsolete anonymous inner classes with **Java 8+ Method References and Lambdas** to clean up the GUI logic.
- **Architecture Migration:** Migrated from a flat project structure to a structured **Maven Multi-Module** architecture.
- **Full Restoration:** Beyond simple recovery, the core logic was successfully re-linked to the Swing GUI, restoring full encryption/decryption capabilities.
- **Compatibility:** Updated the GUI launcher to work with modern JDKs (17+).

## ⚠️ Current Status: "Fully Functional Archive"
> **Note for Reviewers:** Unlike standard de-compiled code, this module has been manually fixed to ensure a **fully functional GUI**. It serves as a historical documentation of the DES implementation (found in `helpFun.java`) while remaining executable for educational demonstrations.

## 🧬 Core Logic (Algorithm)
The cryptographic heart of the project resides in `helpFun.java`, which implements:
- **Initial & Final Permutations**
- **16-Round Feistel Network**
- **S-Box Substitutions** (S1 - S8) using a custom language mapping for ASCII/Hex conversion.
- **Key Scheduling:** Full generation of the 16 sub-keys from a 64-bit (16-hex char) master key.

## 🚀 How to Run
1. Navigate to the `crypto-des` directory.
2. Run the main class: `com.ahmad.crypto.des.DesEncTest_Ahmad_Dandeh`.
3. **Usage:** - Enter text (lowercase/numbers) in the **Text** field.
    - Click **Input Text** to process the blocks.
    - Enter a **16-character hex key** (e.g., `133457799BBCDFF1`).
    - Click **Encryption** to see the binary cipher output.