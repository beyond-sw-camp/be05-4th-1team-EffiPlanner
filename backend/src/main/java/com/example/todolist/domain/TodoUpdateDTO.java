package com.example.todolist.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class TodoUpdateDTO {
    private String title;
    private LocalDateTime deadline;
    private LocalDateTime updatedAt;
    private Boolean deleteYn;
    private Boolean doneYn;

    @Builder
    public TodoUpdateDTO(String title,LocalDateTime deadline, LocalDateTime updatedAt, Boolean deleteYn, Boolean doneYn){
        this.title = title;
        this.deadline = deadline;
        this.updatedAt = updatedAt;
        this.deleteYn = deleteYn;
        this.doneYn = doneYn;
    }
    public Todo ToEntity(){
        return Todo.builder()
                .title(this.title)
                .deadline(this.deadline)
//                .updatedAt(this.updatedAt)
//                .deleteYn(this.deleteYn)
//                .doneYn(this.doneYn)
                .build();
    }

}
