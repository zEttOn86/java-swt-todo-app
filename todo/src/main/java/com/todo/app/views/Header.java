package com.todo.app.views;

import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.todo.app.TodoApp;
import com.todo.app.events.SavingBtnAdapter;
import com.todo.app.events.TodoTextKeyAdapter;
import com.todo.app.resources.Constants;
import com.todo.app.utils.TodoList;

public class Header {
    
    public Button todoSavingBtn;
    public StyledText todoText;
    
    private final Shell shell;
    
    public Header(Shell shell, TodoList todoList, AtomicBoolean shouldUpdate, ResourceBundle messages){
        this.shell = shell;

        Composite c1 = new Composite(shell, SWT.NONE);
        c1.setLayout(new GridLayout(8, true));
        GridData gridData = new GridData(GridData.FILL,
                                         GridData.FILL,
                                         true,
                                         false,
                                         1,
                                         1);
        c1.setLayoutData(gridData);
        c1.setBackground(Constants.bkgColor);

        Label titleLabel = new Label(c1, SWT.LEFT);
        titleLabel.setText(messages.getString("titleLabel_str"));
        Font titleFont = new Font(titleLabel.getDisplay(), 
                                  new FontData("Meiryo", 30, SWT.BOLD));
        titleLabel.setFont(titleFont);
        gridData = new GridData(GridData.FILL, //horizontalAlignment
                                GridData.FILL, //verticalAlignment
                                true,          //grabExcessHorizontallSpace
                                false,         //grabExcessVerticalSpace
                                8,
                                1);
        titleLabel.setLayoutData(gridData);
        titleLabel.setBackground(Constants.bkgColor);

        todoText = new StyledText(c1, SWT.SINGLE | SWT.BORDER);
        Font todoFont = new Font(todoText.getDisplay(),
                                 new FontData("Meiryo", 15, SWT.NONE));
        todoText.setFont(todoFont);
        gridData = new GridData(GridData.FILL, //horizontalAlignment
                                SWT.FILL,      //verticalAlignment
                                false,         //grabExcessHorizontallSpace
                                false,         //grabExcessVerticalSpace
                                5,
                                1);
        todoText.setLayoutData(gridData);
        todoText.addKeyListener(new TodoTextKeyAdapter(todoList, 
                                                        shouldUpdate));

        todoSavingBtn = new Button(c1, SWT.PUSH | SWT.CENTER);
        gridData = new GridData(SWT.LEFT, //horizontalAlignment
                                SWT.CENTER, //verticalAlignment
                                true,          //grabExcessHorizontallSpace
                                true          //grabExcessVerticalSpace
                                );
        gridData.heightHint = 30;
        gridData.widthHint = 30;
        todoSavingBtn.setLayoutData(gridData);
        
        Image img = new Image(shell.getDisplay(), 
                              TodoApp.class.getResourceAsStream("resources/"+"pencil.png"));
        todoSavingBtn.setImage(img);

        todoSavingBtn.addSelectionListener(
            new SavingBtnAdapter(todoText,
                                 todoList,
                                 shouldUpdate));
    }
}
