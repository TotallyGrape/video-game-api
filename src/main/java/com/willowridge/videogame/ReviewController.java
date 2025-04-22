package com.willowridge.videogame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    // ✅ Get all reviews for a specific game
    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<Review>> getReviewsByGameId(@PathVariable String gameId) {
        List<Review> reviews = reviewRepository.findByGameId(gameId);
        return ResponseEntity.ok(reviews);
    }

    // ✅ Get one review by its ID
    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable String reviewId) {
        return reviewRepository.findById(reviewId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Post a review for a game (must be authenticated)
    @PostMapping("/game/{gameId}")
    public ResponseEntity<Review> addReview(@PathVariable String gameId,
                                            @RequestBody Review review,
                                            Authentication authentication) {
        System.out.println("🎯 Review endpoint hit!");

        String username = authentication.getName();
        review.setUsername(username);
        review.setGameId(gameId);

        System.out.println("Review before save: " + review);
        Review savedReview = reviewRepository.save(review);
        System.out.println("Saved review: " + savedReview);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedReview);
    }

    // ✅ Update a review (only the author or admin)
    @PutMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@PathVariable String reviewId,
                                          @RequestBody Review updatedReview,
                                          Authentication authentication) {

        Optional<Review> reviewOpt = reviewRepository.findById(reviewId);
        if (reviewOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Review not found");
        }

        Review existingReview = reviewOpt.get();
        String currentUsername = authentication.getName();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

        if (existingReview.getUsername().equals(currentUsername) || isAdmin) {
            existingReview.setRating(updatedReview.getRating());
            existingReview.setComment(updatedReview.getComment());
            Review saved = reviewRepository.save(existingReview);
            return ResponseEntity.ok(saved);
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You can't edit this review");
    }

    // ✅ Delete a review (admin only)
    @DeleteMapping("/{reviewId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable String reviewId) {
        Optional<Review> existingReview = reviewRepository.findById(reviewId);
        if (existingReview.isPresent()) {
            reviewRepository.delete(existingReview.get());
            return ResponseEntity.ok("Review deleted");
        }
        return ResponseEntity.notFound().build();
    }
}
