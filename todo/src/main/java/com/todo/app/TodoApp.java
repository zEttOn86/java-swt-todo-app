package com.todo.app;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.todo.app.events.SavingBtnAdapter;
import com.todo.app.utils.TodoList;
import com.todo.app.views.Body;
import com.todo.app.views.Header;

/**
 * Hello world!
 *
 */
public class TodoApp 
{

    private TodoList todoList;
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

        // Set title
        shell.setText("TODOリスト");

        // Header composite
        Header header = new Header(shell);

        // Body composite
        Body body = new Body(shell);

        header.todoSavingBtn.addSelectionListener(
            new SavingBtnAdapter(header.todoText,
                                 todoList));


        shell.open();
        return shell;
    }
}
