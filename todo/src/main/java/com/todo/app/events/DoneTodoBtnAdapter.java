package com.todo.app.events;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableItem;

public class DoneTodoBtnAdapter extends SelectionAdapter {

    private TableItem item;
    public DoneTodoBtnAdapter(TableItem item){
        this.item = item;
    }

    public void widgetSelected(SelectionEvent event) {
        // 現在のフォントを取得する。
        Font f = item.getFont(1);
        FontData fd = f.getFontData()[0];

        Button btn = (Button) event.getSource();
        if (btn.getSelection()){
            fd.data.lfStrikeOut = 1;
        }
        else{
            fd.data.lfStrikeOut = 0;
        }

        // 更新したフォントを設定する。
        Font font = new Font(btn.getDisplay(), fd);
        item.setFont(1, font);
    }
}
