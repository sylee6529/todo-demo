package com.example.todoproject.common;

public final class ValidationMessages {
    
    // CreateTodoRequest messages
    public static final String USER_ID_REQUIRED = "User ID는 필수입니다";
    public static final String USER_ID_POSITIVE = "User ID는 양수여야 합니다";
    public static final String TITLE_REQUIRED = "제목은 필수입니다";
    public static final String TITLE_SIZE_CREATE = "제목은 200자를 초과할 수 없습니다";
    public static final String CONTENT_SIZE = "내용은 5000자를 초과할 수 없습니다";
    
    // UpdateTodoRequest messages
    public static final String TITLE_SIZE_UPDATE = "제목은 1자 이상 200자 이하여야 합니다";
    
    // Path variable messages
    public static final String TODO_ID_POSITIVE = "Todo ID는 양수여야 합니다";
    
    private ValidationMessages() {
        // Utility class - prevent instantiation
    }
}