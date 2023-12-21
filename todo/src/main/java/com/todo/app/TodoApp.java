package com.todo.app;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.todo.app.events.SavingBtnAdapter;
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

        Shell shell = new Shell(display);
        shell.setLayout(new GridLayout(1, false)); // makeColumnsEqualWidth: 列をすべて同じ幅にするかどうか、trueの場合同じ

        Locale currentLocale = Locale.getDefault();
        System.out.println(currentLocale);
        ResourceBundle messages = ResourceBundle.getBundle("com.todo.app.resources.MessagesBundle", 
                                                           currentLocale);

        // Set title
        shell.setText(messages.getString("shellTitle_str"));

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
