package com.example.todolist.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TodoDTO {
    private Long id;
    private String title;
    private LocalDateTime deadline;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean deleteYn;
    private Boolean doneYn;

    private String userId;
    private Long categoryId;

    public Todo toEntity(User user, Category category) {
        return Todo.builder()
                .id(id)
                .title(title)
                .deadline(deadline)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .deleteYn(deleteYn)
                .doneYn(doneYn)
                .user(user)
                .category(category)
                .build();
    }

    public TodoDTO(Long id, String title, LocalDateTime deadline, LocalDateTime createdAt, LocalDateTime updatedAt, Boolean deleteYn, Boolean doneYn, String userId, Long categoryId) {
        this.id = id;
        this.title = title;
        this.deadline = deadline;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleteYn = deleteYn;
        this.doneYn = doneYn;
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public TodoDTO(Todo rtn) {
        this.id = rtn.getId();
        this.title = rtn.getTitle();
        this.deadline = rtn.getDeadline();
        this.createdAt = rtn.getCreatedAt();
        this.updatedAt = rtn.getUpdatedAt();
        this.deleteYn = rtn.getDeleteYn();
        this.doneYn = rtn.getDoneYn();
    }
}