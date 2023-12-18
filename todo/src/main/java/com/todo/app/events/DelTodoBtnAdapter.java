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
    private final Table fTable;
    private final TodoList todoList;
    private AtomicBoolean shouldUpdate;

    public DelTodoBtnAdapter(Table table, TodoList todoList, AtomicBoolean shouldUpdate){
        this.fTable = table;
        this.todoList = todoList;
        this.shouldUpdate = shouldUpdate;
    }
    public void widgetSelected(SelectionEvent event){
        Button btn = (Button) event.getSource();

        Point pt = new Point(event.x, event.y);
        System.out.println(pt);
        TableItem item = btn.getParent();
        if(item==null){
            System.out.println("This is null");
            return;
        }
        int index = fTable.indexOf(item);
        System.out.println(index);
        todoList.remove(index);
        shouldUpdate.set(true);
    }
}
