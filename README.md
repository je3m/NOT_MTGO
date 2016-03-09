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