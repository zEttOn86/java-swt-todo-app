package com.todo.app.runnable;

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Table;

import com.todo.app.utils.TodoList;

public class RunTableUpdater implements Runnable {
    private AtomicBoolean shouldUpdate;
    private Display display;
    private Table table;
    private TodoList todoList;

    public RunTableUpdater(Display display,
                           Table table,
                           TodoList todoList,
                           AtomicBoolean shouldUpdate){
        this.display = display;
        this.table = table;
        this.todoList = todoList;
        this.shouldUpdate = shouldUpdate;
    }

    public void run(){
        while(true){
            // System.out.println("ddddddd");
            // if(shouldTerminate.get()==true){
            //     break;
            // }
            if(shouldUpdate.get()==false){
                continue;
            }

            shouldUpdate.set(false);
            new Thread(new TableUpdater(display, table, todoList)).start();
        }
    }
}
