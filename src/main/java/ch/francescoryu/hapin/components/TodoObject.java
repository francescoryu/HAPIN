package ch.francescoryu.hapin.components;

public class TodoObject {

    String input = null;
    String date = null;
    String priority = null;

    public TodoObject() {

    }

    public TodoObject(String priority, String input, String date) {
        this.input = input;
        this.date = date;
        this.priority = priority;
    }

    public String getDate() {
        return date;
    }

    public String getInput() {
        return input;
    }

    public String getPriority() {
        return priority;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
