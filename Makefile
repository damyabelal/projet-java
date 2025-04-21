
clean:
	$ rm -rf classes/*

doc: 
	$ javadoc -d docs -sourcepath src src/game/listchooser/*.java
	$ javadoc -d docs -sourcepath src src/game/listchooser/util/*.java
	$ javadoc -d docs -sourcepath src src/game/tuile/*.java
	$ javadoc -d docs -sourcepath src src/game/util/*.java
	$ javadoc -d docs -sourcepath src src/game/*.java
	$ javadoc -d docs -sourcepath src src/game/tuile/building/*.java
	$ javadoc -d docs -sourcepath src src/game/action/*.java

cls:
	$ javac -sourcepath src src/game/tuile/*.java -d classes
	$ javac -sourcepath src src/game/util/*.java -d classes
	$ javac -sourcepath src src/game/*.java -d classes
	$ javac -sourcepath src src/game/tuile/building/*.java -d classes
	$ javac -sourcepath src src/game/action/*.java -d classes

ares.jar: 
	$ jar cvfe jar/livrable3ares.jar game.Livrable3ares -C classes .

demeter.jar: 
	$ jar cvfe jar/livrable3demeter.jar game.Livrable3demeter -C classes .

