package com.todo.app.views;

import java.lang.constant.Constable;
import java.util.ResourceBundle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.todo.app.resources.Constants;

public class Body {
    public Table table;

    public Body(Shell shell, ResourceBundle messages){
        ScrolledComposite sc = new ScrolledComposite(shell, SWT.V_SCROLL | SWT.BORDER);
        sc.setExpandHorizontal(true);
        sc.setExpandVertical(true);
        sc.setLayout(new GridLayout(1, true));
        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        sc.setLayoutData(gridData);
        sc.setBackground(Constants.bkgColor);

        Composite c2 = new Composite(sc, SWT.NONE);
        sc.setContent(c2);
        c2.setLayout(new GridLayout(1, false));
        gridData = new GridData(GridData.FILL, GridData.FILL, true, true, 1, 1);
        c2.setLayoutData(gridData);
        c2.setBackground(Constants.bkgColor);

        table = new Table(c2,  SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);//|SWT.CHECK);
        table.setHeaderVisible(true);	// 見出しを表示する
        table.setLinesVisible(true);	// 行間の線を表示する
        gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        table.setLayoutData(gridData);

        // 列の設定
        //TODO: 動的に列の幅を変えたい。
        //参考：https://okwave.jp/qa/q2188444.html
        TableColumn col1 = new TableColumn(table, SWT.LEFT);
        col1.setText(messages.getString("col1_str"));
        col1.setWidth(100);

        TableColumn col2 = new TableColumn(table, SWT.LEFT);
        col2.setText(messages.getString("col2_str"));
        col2.setWidth(700);

        TableColumn col3 = new TableColumn(table, SWT.CENTER);
        col3.setText("");
        col3.setWidth(75);

        TableColumn col4 = new TableColumn(table, SWT.CENTER);
        col4.setText("");
        col4.setWidth(75);
    }
}
