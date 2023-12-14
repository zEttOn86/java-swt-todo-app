package com.todo.app.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class Body {
    public Table table;

    public Body(Shell shell){
        ScrolledComposite sc = new ScrolledComposite(shell, SWT.BORDER | SWT.V_SCROLL);
        sc.setExpandHorizontal(true);
        sc.setExpandVertical(true);
        sc.setLayout(new GridLayout(1, true));
        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        sc.setLayoutData(gridData);

        Composite c2 = new Composite(sc, SWT.NONE);
        sc.setContent(c2);
        c2.setLayout(new GridLayout(1, false));
        gridData = new GridData(GridData.FILL, GridData.FILL, true, true, 1, 1);
        c2.setLayoutData(gridData);
        
        table = new Table(c2,  SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);//|SWT.CHECK);
        table.setHeaderVisible(true);	// 見出しを表示する
        table.setLinesVisible(true);	// 行間の線を表示する
        gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        table.setLayoutData(gridData);

        // 列の設定
        //TODO: 動的に列の幅を変えたい。
        //参考：https://okwave.jp/qa/q2188444.html
        TableColumn col1 = new TableColumn(table, SWT.LEFT);
        col1.setText("No");
        col1.setWidth(100);

        TableColumn col2 = new TableColumn(table, SWT.LEFT);
        col2.setText("やること");
        col2.setWidth(700);

        TableColumn col3 = new TableColumn(table, SWT.CENTER);
        col3.setText("");
        col3.setWidth(75);

        TableColumn col4 = new TableColumn(table, SWT.CENTER);
        col4.setText("");
        col4.setWidth(75);
    }
}