package ch.francescoryu.hapin.components;

import javafx.scene.control.CheckBox;

public class TodoObject {

    String input = null;
    String date = null;
    String priority = null;
    CheckBox checkBox;

    public TodoObject() {

    }

    public TodoObject(String priority, String input, String date, CheckBox checkBox) {
        this.input = input;
        this.date = date;
        this.priority = priority;
        this.checkBox = checkBox;
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

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}
