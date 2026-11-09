package com.wildtrail.modules.reviews;
// VerifiedReview: Java class for the WildTrail application.
public final class VerifiedReview extends Review {
    public VerifiedReview(int rating,String comment) {
        super(rating,comment);
    }
    @Override public String getType(){return "VERIFIED_SAFARI";}
}
