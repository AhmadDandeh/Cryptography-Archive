# DES Encryption Suite (Legacy 2014)

## 📌 Project Context
This project is a Java-based implementation of the **Data Encryption Standard (DES)**, originally developed in 2014. It has been preserved and migrated to a modern Maven environment as part of a historical code recovery and documentation initiative.

## 🛠️ Reverse Engineering & Modernization
The source code was reconstructed from legacy `.class` files. During this process, the following improvements were made:
- **Refactoring:** Replaced obsolete anonymous inner classes with **Java 8+ Lambda Expressions** to clean up the event-handling logic.
- **Environment:** Migrated from a flat project structure to a **Maven Multi-Module** architecture.
- **Compatibility:** Updated the GUI launcher to work with modern JDKs (17+).

## ⚠️ Current Status: "Legacy Preservation"
> **Note for Reviewers:** > This module is kept primarily for **algorithmic reference** and historical documentation of the DES implementation (found in `helpFun.java`). While the GUI is functional, some execution exceptions may occur due to the nature of the bytecode-to-source reconstruction. Priority has been given to preserving the cryptographic logic over fixing legacy UI-bound exceptions.

## 🧬 Core Logic (Algorithm)
The cryptographic heart of the project resides in `helpFun.java`, which implements:
- **Initial & Final Permutations**
- **16-Round Feistel Network**
- **S-Box Substitutions** (S1 - S8)
- **Key Scheduling** (Sub-key generation)
