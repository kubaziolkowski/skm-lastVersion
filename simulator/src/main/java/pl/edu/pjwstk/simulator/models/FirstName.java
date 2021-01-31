package pl.edu.pjwstk.simulator.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum FirstName {
    Abdullah,
    Adolf,
    Maria,
    Gandalf,
    Gimli,
    Theoden,
    Shagrat,
    Ugluk;

    public static final List<FirstName> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
}
