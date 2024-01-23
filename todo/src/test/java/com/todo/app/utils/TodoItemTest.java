package com.todo.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.todo.app.utils.TodoItem;

public class TodoItemTest {
    // Arrange
    TodoItem todoItem = new TodoItem("test");

    @Test
    public void testSetTodoTxt(){
        todoItem.setTodoTxt("dddd");
        assertEquals("dddd", todoItem.getTodoTxt());
    }

    @Test
    public void testSetIsDone(){
        todoItem.setIsDone(true);
        assertEquals(true, todoItem.getIsDone());

        todoItem.setIsDone(false);
        assertNotEquals(true, todoItem.getIsDone());
    }

    
    @Test
    public void testSetIsDisplay(){
        todoItem.setIsDisplay(true);
        assertEquals(true, todoItem.getIsDisplay());

        todoItem.setIsDisplay(false);
        assertNotEquals(true, todoItem.getIsDisplay());
    }
}
