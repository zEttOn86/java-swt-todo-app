package com.todo.app;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {

        Display display = new Display(); // アプリが OS に問い合わせる窓口
        Shell shell = new Shell(display); // ウィンドウを表現するクラス
        shell.setText("ウィンドウ名");
        shell.open();

        while(!shell.isDisposed()) { // 終了指令がない間は無限ループ
            if(!display.readAndDispatch()) {
                display.sleep(); // イベントが発生しない間はスリープ
            }
        }
        display.dispose(); // リソースの解放
    }
}
