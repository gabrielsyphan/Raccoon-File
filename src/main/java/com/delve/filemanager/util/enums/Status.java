package com.delve.filemanager.util.enums;

public enum Status {

    PUBLIC(1, "Public file"),
    PRIVATE(2, "Private file");

    private final int value;
    private final String name;

    Status(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Status getStatusByValue(int value) throws IllegalArgumentException {
        for(Status sts : values()) {
            if(sts.value == value) {
                 return sts;
            }
        }

        throw new IllegalArgumentException("There is no status with an informed value.");
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
