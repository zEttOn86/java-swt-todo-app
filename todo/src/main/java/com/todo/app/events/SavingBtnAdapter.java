package com.todo.app.events;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

import com.todo.app.utils.TodoItem;
import com.todo.app.utils.TodoList;

public class SavingBtnAdapter extends SelectionAdapter  {
    private StyledText todoText;
    private TodoList todoList;
    private AtomicBoolean shouldUpdate;
    public SavingBtnAdapter(StyledText text, TodoList todoList, AtomicBoolean shouldUpdate){
        this.todoText = text;
        this.todoList = todoList;
        this.shouldUpdate = shouldUpdate;
    }

    public void widgetSelected(SelectionEvent e) {
        String txt = todoText.getText();
        todoText.setText("");

        // If text is empty, return
        if (txt == ""){
            return;
        }
        todoList.add(new TodoItem(txt));
        shouldUpdate.set(true);
        // System.out.println(todoList);
    }
}
