package com.example.bookingmodel.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity(name = "UserRole")
@Builder(toBuilder = true)
@Table(name = "user_role")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
public class UserRole {

    @Id
    @Column(name = "ID_USER_ROLE", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ID_USER", nullable = false)
    @NonNull
    private int id_user;

    @Column(name = "ID_ROLE", nullable = false)
    @NonNull
    private int id_role;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ID_USER", nullable = false)
//    @NonNull
//    private Customer idUser;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ID_ROLE", nullable = false)
//    @NonNull
//    private Role idRole;

}
