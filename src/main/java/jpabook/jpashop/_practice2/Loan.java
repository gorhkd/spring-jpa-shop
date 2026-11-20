package jpabook.jpashop._practice2;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Loan {

    @Id
    @GeneratedValue
    @Column(name = "loan_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private LibraryMember member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(updatable = false)
    @CreationTimestamp  // 다른 JPA 구현체 교체 여지 없음
    private LocalDateTime borrowedAt;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    private Loan(LibraryMember member, Book book) {
        this.member = member;
        this.book = book;
        this.borrowedAt = LocalDateTime.now();
        this.status = LoanStatus.BORROWED;
    }

    public static Loan createLoan(LibraryMember member, Book book) {
        Loan loan = new Loan(member, book);
        book.borrowed();
        member.addLoans(loan);
        return loan;
    }

    public void returnBook() {
        this.status = LoanStatus.RETURNED;
        this.book.returned();
    }

}
