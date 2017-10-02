package items.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({BackPackTests.class, SpecificItemTests.class, ExternalItemsTests.class})
public class AllItemsTests {}
