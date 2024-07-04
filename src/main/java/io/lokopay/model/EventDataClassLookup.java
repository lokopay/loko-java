package io.lokopay.model;

import java.util.HashMap;
import java.util.Map;

public class EventDataClassLookup {
    public static final Map<String, Class<? extends LokoObject>> classLookup = new HashMap<>();

    static {
        classLookup.put("payment", Payment.class);
        classLookup.put("payout", Payout.class);
    }
}
