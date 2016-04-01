# Rose Build Library 

At present the project is seeded with sample code, test cases for the sample code, gradle build file, gitlab-ci config file, and JaCoco coverage tool setup. The build process also retrieves the code coverage metrics and shows it in the build window of GitLab.

The following resources were used to setup the project:
* [Gradle JaCoco Plugin](https://docs.gradle.org/current/userguide/jacoco_plugin.html)
* [Gradle User Guide](https://docs.gradle.org/current/userguide/userguide.html), especially the following chapters:
	* [Chapter 7 - Java Quick Start](https://docs.gradle.org/current/userguide/tutorial_java_projects.html)
	* [Chapter 8 - Dependency Management](https://docs.gradle.org/current/userguide/artifact_dependencies_tutorial.html)
	* [Chapter 14 - More about Tasks](https://docs.gradle.org/current/userguide/more_about_tasks.html)

Please see the **build.gradle** and **.gitlab-ci.yml** for more detials.

## Eclipse Setup Instruction

The project was setup using Eclipse (Mars 1 - Java Developer). It can be downloaded from the [On-Campus AFS Server](http://www.rose-hulman.edu/class/csse/binaries/Eclipse/mars/). You will also need to install Gradle plugin from [Eclipse Marketplace](https://marketplace.eclipse.org/content/gradle-integration-eclipse-0). After that, the project can be built within Eclipse IDE.

To build the project in Eclipse, right-click on the project -> Run As -> Gradle Build ... -> Under Gradle task box, enter **build** -> Apply -> Run. Your build should start. Note that you must **install JDK 8 (not JRE)** for all of these to work. 


--------------------------------------------------------------------------------------------------------
Milestone 2

Jim
	-Wrote Zone enum to handle basic zone operations
	-Made base for card class so that the GUI could interact with individual cards

Chris
	-Created GUI for the game that did not include any logic
	-Wrote Functionality tests for the GUI that explain the features the GUI should have
		-These features include:
			-Sectioned zones
			-Displayed cards in hand and battlefeild with their names
			-Displayed number of cards in the other zones
			-Cards in hand can be clicked on to move to the battlefield
			-Cards on the battlefield can be moved to the hand
			-GUI scales to the size of the window
	-Known Bug: Cards can have issues being clicked and numbers don't scale well with very thin windows

Scott


--------------------------------------------------------------------------------------------------------
Milestone 3

Jim
  -Added a bunch of getters and setters for cards
  -Added default action for ability 1 in card handling

Chris
 -Added GUI for clicking on cards and abilities
 -Added constants to make GUI code more readable
 -Fixed bug where cards can have issues being clicked
 -Possible later content: Scale viewed cards and make GUI more efficient (currently a little laggy)
 
Scott
 -Added metric: Method Lines of Code at max of 25. Refactored MTGDuelDecks.main to satisfy this
 -Added a good deal of input validation, mostly checking for indices being beyond the list's size and certain data strings not matching regex expressions.