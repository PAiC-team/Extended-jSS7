
package org.restcomm.ss7.management.console;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.restcomm.ss7.management.console.Tree;
import org.restcomm.ss7.management.console.Tree.Node;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author abhayani
 *
 */
public class TreeTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testMessage() throws IOException {
        Tree tree = new Tree("history");
        tree.getTopNode().addChild("enable");
        tree.getTopNode().addChild("disable");

        assertEquals("history", tree.getTopNode().getCommand());

        Node enable = tree.getTopNode().getChildren().get(0);
        assertEquals("history enable", enable.getCommand());

        Node disable = tree.getTopNode().getChildren().get(1);
        assertEquals(disable.getCommand(), "history disable");
    }
}
