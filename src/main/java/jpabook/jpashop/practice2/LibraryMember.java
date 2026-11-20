package jpabook.jpashop.practice2;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "library_member")
public class LibraryMember {

    @Id
    @GeneratedValue
    @Column(name = "library_member_id")
    private Long id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "member") // cascade = CascadeType.ALL 대출 기록 처리 미정
    private List<Loan> loans = new ArrayList<>();

    public LibraryMember(String name) {
        this.name = name;
    }

    public void addLoans(Loan loan) {
        this.loans.add(loan);
    }
}
