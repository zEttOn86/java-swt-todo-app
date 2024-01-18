package com.todo.app.utils;

import java.util.ArrayList;

public class TodoList {
    public ArrayList<TodoItem> todoList;
    public TodoList(){
        todoList = new ArrayList<TodoItem>();
    }
    public void add(TodoItem t){
        System.out.println("Add item");
        todoList.add(t);
        // new Thread(new ComboUpdater()).start();
        // shouldUpdate.set(true);
        //do other things you want to do when items are added 
    }
    public void remove(int index){
        System.out.println("Remove item");
        todoList.remove(index);
        // shouldUpdate.set(true);
        // new Thread(new ComboUpdater()).start();
        //do other things you want to do when items are removed
    }
    public int length(){
        return todoList.size();
    }
    public TodoItem getItem(int index){
        return todoList.get(index);
    }
    public void clear(){
        todoList.clear();
    }
}