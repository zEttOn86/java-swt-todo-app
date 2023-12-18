package com.todo.app.events;

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.todo.app.utils.TodoList;

public class DelTodoBtnAdapter extends SelectionAdapter {
    private final TodoList todoList;
    private final AtomicBoolean shouldUpdate;

    public DelTodoBtnAdapter(TodoList todoList, AtomicBoolean shouldUpdate){
        this.todoList = todoList;
        this.shouldUpdate = shouldUpdate;
    }
    public void widgetSelected(SelectionEvent event){
        Button btn = (Button) event.getSource();

        int index = (int) btn.getData("ITEM_INDEX");
        System.out.println(index);
        todoList.remove(index);
        shouldUpdate.set(true);
    }
}
