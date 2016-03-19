package me.tonyduco.tuhi.model;

public enum NoteType {
    PLAIN;

    public String getName() {
        switch (this) {
            case PLAIN: return "plain";
            default: throw new RuntimeException("Unreachable Code");
        }
    }

    public static NoteType forName(String name) {
        switch (name) {
            case "plain": return PLAIN;
            default: throw new IllegalArgumentException("Invalid note type" + name);
        }
    }

}
