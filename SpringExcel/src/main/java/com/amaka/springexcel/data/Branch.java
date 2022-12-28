package com.amaka.springexcel.data;

import lombok.*;
import javax.persistence.*;



    @Entity
    @Table(name = "BRANCHES")
    @Getter
    @Setter
    public class Branch {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;
        @Column(name = "br_cd", length = 3)
        private String code;
        @Column(name = "br_name", length = 100)
        private String name;
        @Column(name = "br_address")
        private String address;
        @Column(name = "CITY")
        private String city;
        @Column(name = "EMAIL")
        private String email;
        @Column(name = "PHONE_NO")
        private String phoneNumber;
        @Column(name = "HO_FG")
        private boolean headOffice;
        @Column(name = "REGION_FG")
        private boolean regionFg;
        @Column(name = "BIC_CD")
        private String swiftCode;

        @Override
        public String toString() {
            return "Branch{" +
                    "id=" + id +
                    ", code='" + code + '\'' +
                    ", name='" + name + '\'' +
                    ", address='" + address + '\'' +
                    ", city='" + city + '\'' +
                    ", email='" + email + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", headOffice=" + headOffice +
                    ", regionFg=" + regionFg +
                    ", swiftCode='" + swiftCode + '\'' +
                    '}';
        }
    }


