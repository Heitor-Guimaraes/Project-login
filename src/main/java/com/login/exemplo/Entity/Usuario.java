    package com.login.exemplo.Entity;

    import jakarta.persistence.*;

    @Entity
    @Table(name = "tb_usuario")
    public class Usuario {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private int id;
        private String name;

        @Column(unique = true)
        private String email;
        private String password;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Usuario(){

        }

        public Usuario(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
        }


    }