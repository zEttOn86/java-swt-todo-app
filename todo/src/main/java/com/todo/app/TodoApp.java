package com.todo.app;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.todo.app.events.SavingBtnAdapter;
import com.todo.app.resources.Constants;
import com.todo.app.runnable.RefreshTableThread;
import com.todo.app.utils.TodoList;
import com.todo.app.views.Body;
import com.todo.app.views.Header;

/**
 * Hello world!
 *
 */
public class TodoApp 
{

    private TodoList todoList = new TodoList();
    private AtomicBoolean shouldUpdate = new AtomicBoolean(false);
    // private final ResourceBundle messages; 

    public static void main(String[] args) {

        Display display = new Display(); // アプリが OS に問い合わせる窓口
        TodoApp todoApp = new TodoApp();

        Shell shell = todoApp.open(display);

        while(!shell.isDisposed()) { // 終了指令がない間は無限ループ
            if(!display.readAndDispatch()) {
                display.sleep(); // イベントが発生しない間はスリープ
            }
        }
        display.dispose(); // リソースの解放
    }

    private Shell open(Display display){
        this.todoList = new TodoList();

        Shell shell = new Shell(display, SWT.TITLE|SWT.MIN|SWT.MAX);
        shell.setLayout(new GridLayout(1, false)); // makeColumnsEqualWidth: 列をすべて同じ幅にするかどうか、trueの場合同じ
        Image img = new Image(display, 
                              TodoApp.class.getResourceAsStream("resources/"+"outline_done_all_black_24dp.png"));
        shell.setImage(img);
        shell.setBackground(Constants.bkgColor);

        Locale currentLocale = Locale.getDefault();
        ResourceBundle messages = ResourceBundle.getBundle("com.todo.app.resources.MessagesBundle", 
                                                           currentLocale);

        // Set title
        shell.setText(messages.getString("shellTitle_str"));

        Menu menuBar = new Menu(shell, SWT.BAR);

        MenuItem file = new MenuItem(menuBar, SWT.CASCADE);
        file.setText("File");
        file.setAccelerator(SWT.MOD1 + 'f');
        
        Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
        file.setMenu(fileMenu);
        MenuItem importItem = new MenuItem(fileMenu, SWT.PUSH);
        importItem.setText("&Import");
        importItem.addSelectionListener(new SelectionAdapter(){
            public void widgetSelected(SelectionEvent event) {
                MenuItem item = (MenuItem)event.getSource();

                FileDialog dialog  = new FileDialog(item.getParent().getShell(), SWT.OPEN);
                dialog.setFilterExtensions(new String[] {"*.json"});
                String currentDir = System.getProperty("user.dir");
                dialog.setFilterPath(currentDir);
                String name = dialog.open();
                if(name.isEmpty() || name==null){
                    return;
                }

                TodoList loadedTodoList = null;
                try{
                    Gson gson = new Gson();
                    JsonReader reader = new JsonReader(new FileReader(name));
                    loadedTodoList = gson.fromJson(reader, TodoList.class);
                    if(loadedTodoList.length() == 0){
                        return;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }

                // Load imported file
                todoList.clear();
                for(int i = 0; i < loadedTodoList.length(); i++){
                    todoList.add(loadedTodoList.getItem(i));
                }
                shouldUpdate.set(true);
            }
        });
        MenuItem exportItem = new MenuItem(fileMenu, SWT.PUSH);
        exportItem.setText("&Export");
        exportItem.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                MenuItem item = (MenuItem)event.getSource();

                FileDialog fileSave  = new FileDialog(item.getParent().getShell(), SWT.SAVE);
                fileSave.setFilterExtensions(new String[] {"*.json"});
                String currentDir = System.getProperty("user.dir");
                fileSave.setFilterPath(currentDir);
                String name = fileSave.open();
                if(name.isEmpty() || name==null){
                    return;
                }

                File file = new File(name);
                //create a write object for the file
                try(Writer writerObject = new FileWriter(file)){
                    
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
        });

        shell.setMenuBar(menuBar);

        // Header composite
        Header header = new Header(shell, todoList, shouldUpdate, messages);

        // Body composite
        Body body = new Body(shell, messages);

        new Thread(new RefreshTableThread(body.table,
                                          todoList,
                                          shouldUpdate)).start();
        shell.open();
        return shell;
    }
}
