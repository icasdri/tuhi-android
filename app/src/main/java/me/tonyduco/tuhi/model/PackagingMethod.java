package me.tonyduco.tuhi.model;

import java.util.Properties;

public enum PackagingMethod {
    NONE(new Packager() {
        @Override public String unpack(String packagedData, Properties props) {
            return packagedData;
        }
        @Override public String pack(String unpakcagedData, Properties props) {
            return unpakcagedData;
        }
    });

    private Packager packager;
    PackagingMethod(Packager packager) {
        this.packager = packager;
    }

    /**
     * Unpackages the given packaged data for this packaging method
     * @param packagedData packaged data
     * @param props method-specific options/parameters (e.g. encryption keys)
     * @return unpackaged data
     */
    public String unpack(String packagedData, Properties props) {
        return this.packager.unpack(packagedData, props);
    }

    /**
     * Packages the given unpackaged data for this packaging method
     * @param unpackagedData unpackaged data
     * @param props method-specific options/parameters (e.g. encryption keys)
     * @return packaged data
     */
    public String pack(String unpackagedData, Properties props) {
        return this.packager.pack(unpackagedData, props);
    }

    public String getName() {
        switch (this) {
            case NONE: return "none";
            default: throw new RuntimeException("Unreachable Code");
        }
    }

    public static PackagingMethod forName(String name) {
        switch (name) {
            case "none": return NONE;
            default: throw new IllegalArgumentException("Invalid packaging method " + name);
        }
    }
}

interface Packager {
    String unpack(String packagedData, Properties props);
    String pack(String unpackagedData, Properties props);
}