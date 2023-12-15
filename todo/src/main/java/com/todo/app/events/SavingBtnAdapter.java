package com.todo.app.events;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

import com.todo.app.utils.TodoItem;
import com.todo.app.utils.TodoList;

public class SavingBtnAdapter extends SelectionAdapter  {
    private StyledText todoText;
    private TodoList todoList;
    public SavingBtnAdapter(StyledText text, TodoList todoList){
        this.todoText = text;
        this.todoList = todoList;
    }

    public void widgetSelected(SelectionEvent e) {
        String txt = todoText.getText();
        todoText.setText("");

        // If text is empty, return
        if (txt == ""){
            return;
        }
        todoList.add(new TodoItem(txt));
        // System.out.println(todoList);
    }
}
