# 5.-Secure-Cloud-File-Storage-Emulator

Difficulty: Hard
Goal: Create a simple program to encrypt a file before uploading it to a simulated cloud directory (use local storage as the "cloud"). Include options to decrypt and download the file.
Skills Covered:
- File I/O
- Encryption and decryption (e.g., AES)
- Basic command-line interface

# Summary
This project involves implementing a basic symmetric encryption algorithm (e.g., AES) in Java. The program will allow users to encrypt and decrypt a message using a shared key. 
This will teach you how to work with cryptographic libraries, handle byte arrays and strings, and manage exceptions.

# Deadline 
4 weeks from today: February 6, 2025.

# Use Case Scenario
You are a user who wants to securely store a confidential document ("passwords.txt") in the cloud and retrieve it later.

# Stage 1: File Upload (Encryption and Storage)
1. Initial Input:
You have a file called passwords.txt containing sensitive data like:
yaml
Gmail: myemail@gmail.com | Password: 123456  
Bank: bankuser | PIN: 7890  

2. Action:
Run the program from the command line with an "upload" option:
bash
java SecureCloud encrypt-upload passwords.txt  

3.Processing:
The program reads the contents of passwords.txt.
It generates an encryption key (a secure string) to encode the file using AES encryption.
The encrypted data might look like this:
7GdhU3k9mNpOq2e1Rf...  
The encrypted file is saved in a simulated "cloud" folder, e.g., /cloud-storage/passwords.enc.

4. Output:
The program informs you:
arduino
File "passwords.txt" encrypted and uploaded successfully to the cloud.  

# Stage 2: Retrieving the File (Decryption and Download)
1. Need:
You now want to retrieve and decrypt the passwords.txt file.

2. Action:
Run the program with a "download" option:
bash
java SecureCloud decrypt-download passwords.txt  

3.Processing:
The program locates the encrypted file in the /cloud-storage directory (passwords.enc).
It uses the encryption key to decrypt the file, recovering the original data:
yaml
Copy code
Gmail: myemail@gmail.com | Password: 123456  
Bank: bankuser | PIN: 7890  
The decrypted file is saved to your local directory as passwords-decrypted.txt.

4. Output:
The program informs you:
arduino
File "passwords.txt" decrypted and saved as "passwords-decrypted.txt".  

# Stage 3: Program Workflow Example
Encrypt and Upload:
  Input: passwords.txt
  Encrypted File Stored: /cloud-storage/passwords.enc
Decrypt and Download:

Input: /cloud-storage/passwords.enc
Output: passwords-decrypted.txt (restores original content)

# Step-by-Step Project Plan
# Week 1: Research and Setup
Understand the Key Concepts:
Research AES encryption: How to encrypt and decrypt files securely.
Learn about file handling in Java: Reading, writing, and updating files.
Set Up Your Workspace:
Install Java (or ensure it’s updated).
Set up your IDE (e.g., IntelliJ IDEA, Eclipse).
Organize folders for the project, e.g., /input, /cloud-storage.

# Week 2: Build the Encryption and Decryption Mechanisms
File Encryption:

Plan how to take an input file and encrypt its contents using AES.
Use a secret key for encryption and ensure it is securely stored.
Example:
Input: "Hello, World!"
Encrypted: "3a45b6d..."
File Decryption:

Plan a process to decrypt an encrypted file using the same key.
Verify the decrypted content matches the original.
Example:
Input: "3a45b6d..."
Decrypted: "Hello, World!"

# Week 3: Simulate Cloud Storage with File I/O
Directory Structure:
Create a folder on your local system (e.g., /cloud-storage) to simulate the cloud.
Upload Files:
Write the encrypted file to the /cloud-storage directory.
Store metadata (e.g., filename, encryption key) if needed.
Download Files:
Retrieve files from /cloud-storage and decrypt them to their original form.

# Week 4: Create a Command-Line Interface (CLI)
Interface Design:
Use the command line to interact with the program.
Provide options such as:
Encrypt and upload a file (encrypt-upload)
Decrypt and download a file (decrypt-download)
