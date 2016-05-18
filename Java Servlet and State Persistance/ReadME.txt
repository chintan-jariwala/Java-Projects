Instructions for running "Build.xml"

-- You have exceute the command "ant build" -> This will make the entire folder structure which you can copy in your tomcat/webapp directory.

-- To Generate a war file from ant , execute this command -> "ant war". 

-- The war file which i have included with this can be copied and put under tomcat/webapp directory. This will give you a running program.

-- In order to make the xml file thread safe from multiple JVM machines, I have used FileLocks. To properly check this functionality i have also included the xml file named "data.xml". This file is under resources directory of the source tree You can enter any data using multiple form and you will be bale to test the concurrancy.  