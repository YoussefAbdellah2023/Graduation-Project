package com.example.e_commercefuture.DataTypeOrModel;

public class DataTypeReviewAndComments {

    String rating , comment ;

    public DataTypeReviewAndComments(String rating, String comment) {
        this.rating = rating;
        this.comment = comment;

    }

    public DataTypeReviewAndComments() {
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
