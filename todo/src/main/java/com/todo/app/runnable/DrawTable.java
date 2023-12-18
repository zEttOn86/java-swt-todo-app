package com.todo.app.runnable;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.text.TabExpander;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;

import com.todo.app.utils.ThreadHelper;
import com.todo.app.utils.TodoItem;
import com.todo.app.utils.TodoList;
import com.todo.app.views.TableRow;

public class DrawTable implements Runnable {

    private Table table;
    private TodoList todoList;
    private final AtomicBoolean shouldUpdate;

    public DrawTable(Table table, TodoList todoList, AtomicBoolean shouldUpdate){
        this.table = table;
        this.todoList = todoList;
        this.shouldUpdate = shouldUpdate;
    }
    public void run() {
        Display display = Display.getCurrent();
        if (display == null) {
            display = Display.getDefault();
        }
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
                    for(int i = 0; i < todoList.length(); i++){
                        new TableRow(table, todoList, i, shouldUpdate);
                    }
                }
            }
        );
    }
}
