package com.todo.app.utils;

public class TodoItem {
    // https://www.tohoho-web.com/java/class.htm
    // http://www.dicre.com/java/array/

    private Boolean isDone;
    private Boolean isDisplay;
    String todoTxt = null;

    public TodoItem(String txt){
        isDone = false;
        isDisplay = false;
        todoTxt = txt;
    }

    public void setTodoTxt(String txt){
        todoTxt = txt;
    }
    public void setIsDone(Boolean done){
        isDone = done;
    }
    public void setIsDisplay(Boolean display){
        isDisplay = display;
    }

    public String getTodoTxt(){
        return todoTxt;
    }
    public Boolean getIsDone(){
        return isDone;
    }
    public Boolean getIsDisplay(){
        return isDisplay;
    }
}
