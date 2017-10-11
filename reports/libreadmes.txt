SWEN 222
Team SnicketySnacks (James Watt, Thomas Edwards, Liam Byrne, Mohsen Javaher, Andrew McManaway)

Each package has it's own test package which contains different class to test different aspects 
of the package.

audio
All tests for this Library can be located in 'audio.tests.AudioTests' using JUnit4.
Please note:
    Some tests requires the user to listen to different 'clicks' for each method.
    When you encounter these tests, the 'correct output' occurs when a test plays
    a 'Click' sound twice (which could be at different volumes).

controller
The movement controls for the Controller library can be tested by running the 'Launcher', and starting a game.
Each test can be run from the 'controller.tests.ControllerTests' using JUnit4.

frames
Run Launcher to launch game and see frames library in use.
Tests can be run through the AllFramesTests class.

gfx
All tests can be run as a suite by using the AllGFXTests class.

items
Run the AllItemsTests class to run all tests as a suite.
Please note that not all the tests may pass, since items is undergoing a major redo currently.

map

James Watt Library Info:
I am in charge/the primary author of the Map package.
I have completed the implementation of my Map Package.
The main classes in my map package are the map and world classes which represent the model of the game world and game maps located in the world. Each world and map,
is represented by a text file of which my MapParser and WorldParser are in charge of reading and creating the given worlds and map. I have chosen to implement a "waterfall" parser 
and as such have various classes called "healthPotion" and "MassiveG" which are simply helper classes for my parser. The world and map text files which my parser reads is
located in my assets/entities package(for maps) and assets/world for world.

Testing:
My "tests" package is located inside my map package and contains tests for the Map,World,MapParser and WorldParser. They are all Junit Test which can be run as per the usual methods.
However due to time restraints i have yet to fully completed my testing package. As of reading this i have only implemented a few tests for each testing class.


npc
Package uses a Strategy design pattern.
Run the test suite called allTests, havent tested them on other computers yet.
(which might cause issues because of the simultaneous threads)

player
Contains a BackPack of items can make moves if it is possible and also can shoot every 1 second there is 
shoot method that I implemented I have used the TimerTask and Schedual library to implement that.
In the Player class I have implemented a pick up item which checks if an item is a bounding box of the player 
then the player can pick it up I used a Rectangle library to implement this boundingbox

save_load
A typical way of implementing a deep clone is to go through a class and write code to create new objects and copy over all of the values to these new objects. 
This can be a time-consuming process if the object being cloned is complicated. A simple way of performing a deep clone is for all of the classes that make up 
a class to implement the Serializable interface. If this is the case, we can serialize all of the values of the object and then deserialize all of these values 
to a new object. This in essence is a shortcut to performing a deep copy, since all of the values get copied over into new objects.

