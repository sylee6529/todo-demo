package com.example.todoproject.todo.entity;

import com.example.todoproject.user.entity.User;
import com.example.todoproject.todo.dto.UpdateTodoRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "todos")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Builder.Default
    @Column(name = "is_done", nullable = false)
    private Boolean isDone = false;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    
    public void update(UpdateTodoRequest request) {
        Optional.ofNullable(request.title()).ifPresent(t -> this.title = t);
        Optional.ofNullable(request.content()).ifPresent(c -> this.content = c);
        Optional.ofNullable(request.isDone()).ifPresent(d -> this.isDone = d);
    }
}