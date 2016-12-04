package com.edu.gvn.vietlottery.utils;

public class SequenceUtils {
    private static SequenceUtils sequenceUtils;

    public static SequenceUtils getInstance() {
        if (sequenceUtils == null)
            sequenceUtils = new SequenceUtils();
        return sequenceUtils;
    }

    private SequenceUtils() {

    }

    public String[] sliptSequence(String sequenceNumber, String charSlipt) {
        return sequenceNumber.split(charSlipt);
    }
}
