package com.todo.app.events;

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

import com.todo.app.utils.TodoItem;
import com.todo.app.utils.TodoList;

public class TodoTextKeyAdapter extends KeyAdapter {
    private final TodoList todoList;
    private AtomicBoolean shouldUpdate;
    public TodoTextKeyAdapter(TodoList todoList, AtomicBoolean shouldUpdate){
        this.todoList = todoList;
        this.shouldUpdate = shouldUpdate;
    }

    public void keyPressed(KeyEvent arg0) {
        if (arg0.keyCode==SWT.CR || arg0.keyCode==SWT.KEYPAD_CR){
            StyledText txt = (StyledText) arg0.getSource();
            String str = txt.getText();
            txt.setText("");

            if (str == ""){
                return;
            }
            todoList.add(new TodoItem(str));
            shouldUpdate.set(true);
        }
    }
}