package com.todo.app.runnable;

import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.todo.app.resources.Constants;
import com.todo.app.utils.TodoList;

public class RefreshTableThread implements Runnable {
    private Table table;
    private TodoList todoList;
    private AtomicBoolean shouldUpdate;
    private final Display display;

    public RefreshTableThread(Table table,
                           TodoList todoList,
                           AtomicBoolean shouldUpdate){
        this.table = table;
        this.todoList = todoList;
        this.shouldUpdate = shouldUpdate;
        this.display = table.getShell().getDisplay();
    }

    public void run(){
        while(true){

            if(display.isDisposed()){
                return; 
            }

            // テーブルを更新する必要があれば、テーブルを更新する
            if(shouldUpdate.get()==false){
                continue;
            }
            shouldUpdate.set(false);
            new Thread(new DrawTable(display, table, todoList, shouldUpdate)).start();

            //create a write object for the file
            try(Writer writerObject = new FileWriter(Constants.todoFilePath)){
                
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                /*
                * Use the toJson method and specify
                * the writer and the list we want to write
                */
                gson.toJson(todoList, writerObject);
                
            }catch(Exception e) {
                e.printStackTrace();
            }
            
        }
    }
}
