package org.piotrek.spaceinvaders.model;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Arie on 16.08.2016.
 */
public class BoardFactoryTest {

    @Test
    public void FactoryTestLevel1() {
        Board level1 = BoardFactory.create(1);
        assertTrue(level1 instanceof Level1Board);
    }

    public void FactoryTestLevel2() {
        Board level2 = BoardFactory.create(1);
        assertTrue(level2 instanceof Level2Board);
    }

    @Test(expected=IllegalArgumentException.class)
    public void FacoryTestException() throws Exception {
        Board level10 = BoardFactory.create(10);
    }

}
