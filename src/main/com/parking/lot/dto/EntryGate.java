package com.parking.lot.dto;

import java.util.TreeSet;

public class EntryGate {

    private final int gateNo;

    public EntryGate(int gateNo, int length, int width) {
        this.gateNo = gateNo;
    }

    @Override
    public String toString() {
        return "EntryGate{" +
                "gateNo=" + gateNo +
                '}';
    }
}
