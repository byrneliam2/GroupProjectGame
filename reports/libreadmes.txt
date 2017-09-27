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
Tests can be run through the FramesTests class.

gfx
All tests can be run as a suite by using the AllGFXTests class.

items
Contains the itemList folder which is simply a list of all the current actual items
in the game.
Run the tests from the items.tests package. Not many tests implemented yet.

map
//

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
A typical way of implementing a deep clone is to go through a class and write code to create new objects and copy over all of the values to these new objects. This can be a time-consuming process if the object being cloned is complicated. A simple way of performing a deep clone is for all of the classes that make up a class to implement the Serializable interface. If this is the case, we can serialize all of the values of the object and then deserialize all of these values to a new object. This in essence is a shortcut to performing a deep copy, since all of the values get copied over into new objects.

