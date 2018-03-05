# PokerHandSort
Poker Hand Sort Coding Exercise in Kotlin

### Directory Structure
- `.idea`: Inellij IDEA Setting folder
- `src`: Source Code in Kotlin
- `out`: Folder structure that contains the project artifacts 'assembly files - JAR file'
- `.iml`: Inellij IDEA module file


### How to build and use the code?
Simply, import the project root directory into Intellij IDEA and from the main menue:

 - `Build` -> `Build Artifacts..` 

![Alt text](/../master/screenshots/Build-BuildArtifacts.png?raw=true "Code Results")

 - -> `Build`

![Alt text](/../master/screenshots/BuildArtifact.png?raw=true "Code Results")


 - The JAR file will be generated and stored in `out/artifacts/PokerHandSorter` directory
 
 ![Alt text](/../master/screenshots/JAR-Directory-Structure.png?raw=true "Code Results")

- Now, you can use the terminal command line to run the file as instructured in the PDF file. To use that from the root directory:
  - `cd out/artifacts/PokerHandSorter`
  - `cat poker-hands.txt | java -jar PokerHandSorter.jar`

![Alt text](/../master/screenshots/Screenshot-shell-git-java-jar.png?raw=true "Code Results")

### The easiest way to run and check the results!
Navigate to the `bin` directory and run the following command:

 `cat poker-hands.txt | java -jar PokerHandSorter.jar`
