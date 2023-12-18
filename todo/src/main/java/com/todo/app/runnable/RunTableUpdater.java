package com.todo.app.runnable;

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;

import com.todo.app.utils.TodoList;

public class RunTableUpdater implements Runnable {
    private Table table;
    private TodoList todoList;
    private AtomicBoolean shouldUpdate;

    public RunTableUpdater(Table table,
                           TodoList todoList,
                           AtomicBoolean shouldUpdate){
        this.table = table;
        this.todoList = todoList;
        this.shouldUpdate = shouldUpdate;
    }

    public void run(){
        while(true){
            Display display = Display.getCurrent();
            if (display == null) {
                display = Display.getDefault();
            }

            if(display.isDisposed()){
                return; 
            }

            // テーブルを更新する必要があれば、テーブルを更新する
            if(shouldUpdate.get()==false){
                continue;
            }
            shouldUpdate.set(false);
            new Thread(new TableUpdater(table, todoList, shouldUpdate)).start();
        }
    }
}
