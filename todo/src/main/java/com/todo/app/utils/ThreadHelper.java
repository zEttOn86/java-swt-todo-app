package com.todo.app.utils;

import org.eclipse.swt.widgets.Display;

public class ThreadHelper {

    public static boolean checkAsyncExec(Display display, Runnable r) {

        if (!display.isDisposed()) {
            display.asyncExec(r);
            return true;
        } 
        else {
            return false;
        }
    }
}
