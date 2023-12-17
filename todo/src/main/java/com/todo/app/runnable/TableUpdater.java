package com.todo.app.runnable;

import javax.swing.text.TabExpander;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;

import com.todo.app.utils.ThreadHelper;
import com.todo.app.utils.TodoItem;
import com.todo.app.utils.TodoList;
import com.todo.app.views.TableRow;

public class TableUpdater implements Runnable {
    private Display display;
    private Table table;
    private TodoList todoList;

    public TableUpdater(Display display, Table table, TodoList todoList){
        this.display = display;
        this.table = table;
        this.todoList = todoList;
    }
    public void run() {
        ThreadHelper.checkAsyncExec(
            display,
            new Runnable(){
                public void run(){
                    // テーブルを削除
                    // https://stackoverflow.com/questions/52440051/java-swt-removeall-in-table-doesnt-delete-my-buttons-on-the-rows
                    table.removeAll();
                    for(Control control : table.getChildren()) {
                        if (control instanceof Button) {
                            control.dispose();
                        }
                    }

                    // テーブルを再描画
                    int index = 0;
                    for(TodoItem t : todoList.todoList){
                        index++;
                        new TableRow(table, index, t);
                    }
                }
            }
        );
    }
}
