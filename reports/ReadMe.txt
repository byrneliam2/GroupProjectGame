This is group 10's read me for the external testing section of this group assignment
James:
I am in charge of externally testing gfx,audio and save/load packages.
-At this stage of the project the save/load functionality is still under development so externally testing it is
impossible at this point.
-I have added a few tests to the audio package and have stated in more detail in the tests itself what i did
and what i thought of the current tests.

-For graphics i have added a few extra tests that are very similar to the current tests but have stronger assertions
and thus tests the graphics package to a higher level. I have also noted that there needs to be more refactoring
done so that the front end does not directly communicate to the back end but instead uses interfaces.
-For graphics

Liam:
I have written some external tests for the items package, these can be found in the test class aptly named so.
All tests from items can be run with the AllItemsTests class.
Please note that not all the tests may pass, since items is undergoing a major redo currently.

Drew:
I been placed in charge of being the External Tester for the Map and NPC Classes, both of which are packages
which a majority of the game relies on to run.
    Map:
        - I have created an 'AllMapTests' test class to automatically run every Test for the Map Package.
        - I have attempted to get a larger coverage, this involved running coverage for the entire Map package,
          and then creating tests for all of the remaining minor if / while branches that may not normally occur.
    NPC:
        - I have created a new 'NPCTests' test class which holds the new External Tests for the NPC Package,
          this was created due to the limited amount of tests added previously. I made it my goal to make the
          NPC.class have 100% coverage, which has been achieved.
