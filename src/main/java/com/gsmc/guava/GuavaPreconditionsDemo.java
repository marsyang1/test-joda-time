package com.gsmc.guava;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by mars on 2016/5/31.
 */
@Slf4j
public class GuavaPreconditionsDemo {

    private final boolean initialized = false;

    /**
     * Demonstrate Guava's Preconditions.checkNotNull methods.
     *
     * @param parameter Parameter that is checked for null-ness.
     */
    public void testForNonNullArgument(final String parameter) {
        final String localParameter = Preconditions.checkNotNull(parameter, "Provided parameter is unacceptably null.");
    }

    public void testDivisorNotZero(final int divisor) {
        Preconditions.checkArgument(divisor != 0, "Zero divisor not allowed.");
    }

    public void testArrayElement(final String[] strArray, final int indexNumber) {
        final int index = Preconditions.checkElementIndex(indexNumber, strArray.length, "String array index number");
    }

    public void testArrayPosition(final String[] strArray, final int indexNumber) {
        final int index = Preconditions.checkPositionIndex(indexNumber, strArray.length, "String array index number");
    }

    public void testState() {
        Preconditions.checkState(this.initialized, "Cannot perform action because not initialized.");
    }

    public static void printHeader(final String newHeaderText) {
        log.error("\n==========================================================");
        log.error("== " + newHeaderText);
        log.error("==========================================================");
    }


}
