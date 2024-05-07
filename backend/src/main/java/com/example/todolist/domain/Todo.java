package com.example.todolist.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "todo")
public class Todo {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "todo_id")
	private Long id;

    private String title;
    private LocalDateTime deadline;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean deleteYn;
    private Boolean doneYn;

    @ManyToOne
	@JoinColumn(name = "Email")
	private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public Todo(Long id, String title, LocalDateTime deadline, LocalDateTime createdAt, User user, Category category) {
        this.id = id;
        this.title = title;
        this.deadline = deadline;
        this.createdAt = createdAt;
        this.updatedAt = null;
        this.deleteYn = false;
        this.doneYn = false;
        this.user = user;
        this.category = category;
    }

    public void update(String title, LocalDateTime deadline, Boolean deleteYn, Boolean doneYn) {
        if (title != null && !title.isEmpty())
            this.title = title;
        if (deadline != null && !deadline.isBefore(LocalDateTime.now()))
            this.deadline = deadline;
        if (deleteYn != null)
            this.deleteYn = deleteYn;
        if (doneYn != null)
            this.doneYn = doneYn;
        this.updatedAt = LocalDateTime.now();
    }

    public void delete(){
        this.deleteYn = true;
        this.updatedAt = LocalDateTime.now();
    }
}
