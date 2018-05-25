Creating the JAR file:

  With the project open in Eclipse,
  
  1) Click File > Export...
  2) Navigate to "JAR Runnable File" in the "Java" subtree. Click "Next".
  3) Select "Main - HeroesAndVillains" as the launch configuration.
  4) Choose the export destination as you like.
  5) Make sure "Extract required libraries into generated JAR" is selected.
  6) Click "Finish".
  7) Click "OK" for any warnings that appear.

Running the JAR:

  Navigate to the export destination directory from when you created the JAR and run:
  	
  	java -jar $filename

  Where $filename is the filename you chose when creating the jar.