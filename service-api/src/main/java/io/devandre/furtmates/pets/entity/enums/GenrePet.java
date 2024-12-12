package io.devandre.furtmates.pets.entity.enums;

public enum GenrePet {
    MALE,
    FEMALE;

    public static boolean isValidGenre(String genreString) {
        if (genreString == null) {
            return false;
        }

        try {
            valueOf(genreString.trim().toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
