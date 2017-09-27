SWEN 222
Team SnicketySnacks (James Watt, Thomas Edwards, Liam Byrne, Mohsen Javaher, Andrew McManaway)

Each package has it's own test package which contains different class to test different aspects 
of the package.

audio
//

controller
//

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
Shooting tests mostly implemented and working well.... (Mohesn->...

save_load
//