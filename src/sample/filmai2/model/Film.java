package sample.filmai2.model;

public class Film {
    private int id;
    private String title;
    private String description;
    private int rating;
    private String category;

    public Film(String id, String title, String description, int rating, String category) {

    }

    public Film(String title, String description, int rating, String category) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.category = category;
    }

    public Film(int id, String title, String description, int rating, String category) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", category='" + category + '\'' +
                '}';
    }
}
