package wecan.com.todo;

/**
 * Model class for ToDo
 */

class ToDo {
    private int id;
    private String title;
    private String details;
    private String priorityColor;
    private String date;
    private String time;

    ToDo(){}

    public ToDo(int id, String title, String details, String priorityColor, String date, String time) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.priorityColor = priorityColor;
        this.date = date;
        this.time = time;
    }

    String getTime() {
        return time;
    }

    void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPriorityColor() {
        return priorityColor;
    }

    public void setPriorityColor(String priorityColor) {
        this.priorityColor = priorityColor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
