package jpabook.jpashop._practice2;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {

    @Id @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    @NotBlank
    private String title;

    private boolean isbn;

    public Book(String title) {
        this.title = title;
        this.isbn = false;
    }

    public void borrowed() {
        validIsbn();
        this.isbn = true;
    }

    public void returned() {
        // 만약 예전에 반납한 사람이 또 호출한다면? 막는 코드
        this.isbn = false;
    }

    private void validIsbn() {
        if (isbn) {
            throw new IllegalArgumentException("이미 대출된 책입니다.");
        }
    }
}
