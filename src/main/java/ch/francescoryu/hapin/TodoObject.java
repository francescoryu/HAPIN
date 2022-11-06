package ch.francescoryu.hapin;

public class TodoObject {

    String input = null;
    String date = null;

    public TodoObject() {

    }

    public TodoObject(String input, String date) {
        this.input = input;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getInput() {
        return input;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
