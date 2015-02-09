package com.hyundai.teli.smartsales.utils;

import com.squareup.otto.Bus;

/**
 * Created by nith on 1/27/15.
 */
public class BusProvider {
    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {
    }
}
