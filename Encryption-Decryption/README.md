  # Project: Encryption-Decryption
  In this project we have encoder which use Caesar Cipher with to emplementations, shift only letters or shift all characters.
  Program parses arguments from the command line and chooses different modes
  
**-key** is the key to the cipher, by default key = 0  
**-mode** choose the mode, **"enc"** to encode, **"dec"** to decode  
If there is no -mode, the program work in enc mode.  
**-data** send text to encode or decode it  
**-in**, it is name of file, if we want to read data from the file  
**-out**, it is name of file, if we want to write result to file  
If there is no **-out** argument, the program must print data to the standard output.  
If there are both **-data** and **-in** arguments, program prefer **-data** over **-in**.  
  
  
The first algorithm would be shifting algorithm (it shifts each letter by the specified number according to its order in the alphabet in circle).  
The second one would be based on Unicode table.
  
When starting the program, the necessary algorithm should be specified by an argument **(-alg)**.   
The first algorithm should be named **-shift**, the second one should be named **-unicode**.  
If there is no -alg you should default it to shift.
  

  In this project I use pattern Strategy and also FactoryMethod.  
  https://hyperskill.org/projects/46?track=1
