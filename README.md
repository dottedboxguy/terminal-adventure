to run the project :
	open a terminal where you installed the archive
	unzip it
	run "cd TERMINAL-ADVENTURE/game/target"
	run "java -jar game-0.0.1-SNAPSHOT.jar"

IN THE CASE WHERE THE JAR DOES NOT EXIT :
	run "cd TERMINAL-ADVENTURE/game"
	run "mvn clean"
	the previous command may have output errors, it does not matter
	just in case, empty target/ of any files remaining
	run "mvn package"
	run "cd target"
	run "java -jar game-0.0.1-SNAPSHOT.jar"

to play the game :
	check out manual.txt