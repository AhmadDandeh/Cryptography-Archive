# 🔐 Cryptography & Security Simulation Lab

Welcome to the **Cryptography & Security Simulation Lab**. This repository is a comprehensive collection of cryptographic algorithms, ranging from legacy symmetric ciphers to modern hashing and mobile security protocols.

Each module has been recovered from legacy source code and modernized to run on **JDK 21+** with a structured **Maven** architecture.


---

## 📂 Project Modules

### 1. 🌀 Whirlpool Hash Suite
**Location:** `/Whirlpool`
* **Description:** A high-performance implementation of the 512-bit Whirlpool hashing algorithm.
* **Modernization:** Refactored anonymous classes into Java 8 Lambdas and migrated to a clean Maven structure.
* **Key Use Case:** Secure data integrity and digital signatures.

### 2. 📱 GSM Security Suite
**Location:** `/GSM-Security`
* **Description:** A simulation of the 2G GSM security lifecycle, including A3/A8 authentication and A5/1 stream encryption.
* **Key Use Case:** Real-time voice encryption simulation using live audio recording.
* **Modernization:** Completely rebuilt GUI with stabilized Layout Managers for cross-platform support.

### 3. 🔐 DES Encryption Suite
**Location:** `/DES`
* **Description:** An implementation of the Data Encryption Standard (DES), highlighting the Feistel network architecture.
* **Key Use Case:** Educational demonstration of bit-level permutations and S-Box substitutions.
* **Modernization:** Integrated thread-safe event handling and updated byte-encoding logic.

---

## 🛠️ Technology Stack & Standards
* **Language:** Java 21 (LTS)
* **Build Tool:** Maven 3.x
* **UI Framework:** Java Swing (Modernized Layouts)
* **Security Standards:** NIST (DES), ISO/IEC (Whirlpool), ETSI (GSM/A5.1)

## 🚀 How to Explore
1. Clone the entire repository.
2. Open the root folder in **IntelliJ IDEA** or any Maven-compliant IDE.
3. Navigate to the specific module's `/src/main/java` directory to run the respective `Main` classes.
4. Consult the local `README.md` within each module for specific algorithm details.