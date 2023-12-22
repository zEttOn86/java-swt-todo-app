package com.todo.app.events;

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableItem;

import com.todo.app.utils.TodoItem;
import com.todo.app.utils.TodoList;

public class DoneTodoBtnAdapter extends SelectionAdapter {

    private TableItem item;
    private TodoList todoList;
    private AtomicBoolean shouldUpdate;
    public DoneTodoBtnAdapter(TableItem item, TodoList todoList, AtomicBoolean shouldUpdate){
        this.item = item;
        this.todoList = todoList;
        this.shouldUpdate = shouldUpdate;
    }

    public void widgetSelected(SelectionEvent event) {
        // 現在のフォントを取得する。
        Font f = item.getFont(1);
        FontData fd = f.getFontData()[0];

        Button btn = (Button) event.getSource();
        int index = (int)btn.getData("ITEM_INDEX");
        TodoItem todoItem = todoList.getItem(index);
        if (btn.getSelection()){
            fd.data.lfStrikeOut = 1;
            todoItem.setIsDone(true);
        }
        else{
            fd.data.lfStrikeOut = 0;
            todoItem.setIsDone(false);
        }

        // 更新したフォントを設定する。
        Font font = new Font(btn.getDisplay(), fd);
        item.setFont(1, font);

        shouldUpdate.set(true);
    }
}
