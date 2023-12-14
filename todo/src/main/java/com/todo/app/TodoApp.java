package com.todo.app;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Hello world!
 *
 */
public class TodoApp 
{

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
        Shell shell = new Shell(display);
        shell.setLayout(new GridLayout(1, false)); // makeColumnsEqualWidth: 列をすべて同じ幅にするかどうか、trueの場合同じ
        
        // Set title
        shell.setText("TODOリスト");

        // Header composite

        // Body composite


        shell.open();
        return shell;
    }
}
