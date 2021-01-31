package pl.edu.pjwstk.simulator.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum LastName {
    Anders,
    Parafiniuk,
    Zukiewicz,
    Ziolkowski,
    Pavlov,
    Ze_Szczecina,
    Khalifa,
    Grey;

    public static final List<LastName> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
}
