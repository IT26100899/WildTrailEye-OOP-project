package com.wildtrail.modules.resources;

// Inheritance: a naturalist is a special kind of guide.
// NaturalistGuide: Java class for the WildTrail application.
public final class NaturalistGuide extends Guide {
    public NaturalistGuide(int id, String name, String phone, String license, boolean available) {
        super(id, name, phone, license, "NATURALIST", available);
    }

    @Override
    // Returns an existing value or related record.
    public String getAssignmentRole() {
        return "Wildlife naturalist and safari driver";
    }
}
