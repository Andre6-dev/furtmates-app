package io.devandre.furtmates.pets.entity.enums;

import java.util.Arrays;

public enum AgePet {
    PUPPY,
    YOUNG,
    ADULT,
    SENIOR;

    public static boolean isValidAge(String ageString) {
        if (ageString == null) {
            return false;
        }

        try {
            valueOf(ageString.trim().toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
