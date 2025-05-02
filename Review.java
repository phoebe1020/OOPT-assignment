//Author: Melody Lee
//Module: User Management
//System: Online Shopping System
//Group: DFT1G12

public class Review extends User {

    private String comment;
    private int rating;
  
    public Review(String userId, String name, int rating, String comment) {
      super(userId, name, null, null, null);
      this.comment = comment;
      this.rating = rating;}
  
    public int getRating() {
      return rating;
    }

    public void setRating(int rating) {
      this.rating = rating;
    }

    public String getComment() {
      return comment;
    }

    public void setComment(String comment) {
      this.comment = comment;
    }
   
    @Override
    public String toString() {
      return String.format(super.toString()+"\nRating: %d\nComment: %s", rating, comment);
    }

    public static void viewReviews() {
      if (OnlineShoppingSystem.reviews.isEmpty()) {
          System.out.println("No reviews available.");
          return;
      }
  
      System.out.println("\n--- Reviews ---");
      for (Review review : OnlineShoppingSystem.reviews) {
          System.out.println(review.toString());
      }
  }

  public static void addReview(String userId, String name, String comment, int rating) {
    if (rating < 1 || rating > 5) {
        System.out.println("Invalid rating. Please provide a rating between 1 and 5.");
        return;
    }

    if (comment == null || comment.isEmpty()) {
        System.out.println("Comment cannot be empty.");
        return;
    }

    Review review = new Review(userId, name, rating, comment);
    OnlineShoppingSystem.reviews.add(review); 
    System.out.println("Review added successfully!");
}
  }



  

  