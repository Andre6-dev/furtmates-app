package io.devandre.furtmates.pets.entity.enums;

public enum PetAvailabilityStatus {
    AVAILABLE,
    ADOPTED,
    FOSTERED,
    PENDING;

    public static boolean isValidStatus(String statusString) {
        if (statusString == null) {
            return false;
        }

        try {
            valueOf(statusString.trim().toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
