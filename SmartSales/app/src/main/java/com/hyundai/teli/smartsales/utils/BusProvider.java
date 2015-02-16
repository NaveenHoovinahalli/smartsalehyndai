package com.hyundai.teli.smartsales.utils;

import com.squareup.otto.Bus;

/**
 * Created by Nitish Kulkarni on 2/8/15.
 */
public class BusProvider {
    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {
    }
}
