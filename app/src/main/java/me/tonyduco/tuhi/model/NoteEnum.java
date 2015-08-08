package me.tonyduco.tuhi.model;

public enum NoteEnum {
    PLAINTEXT(0),
    DELETED(-1),
    PERMANENTLY_DELETED(-2);

    private final int type;

    NoteEnum(int type){
        this.type = type;
    }

}
