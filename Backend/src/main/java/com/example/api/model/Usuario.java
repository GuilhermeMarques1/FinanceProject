package com.example.api.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Usuario_Type")
public class Usuario implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String email;
  private String senha;
  private String nome;
  private LocalDateTime nascimento;
  private String CPF;
  private float valorConta;

  private UserRole role;


  @OneToMany(mappedBy = "usuario", orphanRemoval = true, cascade = CascadeType.ALL)
  private List<Operacao> operacoes = new ArrayList<Operacao>();


  public Usuario(){
        
  }

  public Usuario(String login, String senha, String nome, UserRole role){
      this.email = login;
      this.senha = senha;
      this.nome = nome;
      this.role = role;
  }

  public Usuario(String login, String senha, UserRole role){
      this.email = login;
      this.senha = senha;
      this.role = role;
  }

  public long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getNome() {
    return nome;
  }

  public String getSenha() {
    return senha;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public List<Operacao> getOperacoes() {
    return operacoes;
  }

  public void setOperacoes(List<Operacao> operacoes) {
    this.operacoes = operacoes;
  }

  public String getCPF() {
      return CPF;
  }

  public void setCPF(String cPF) {
      CPF = cPF;
  }

  public LocalDateTime getNascimento() {
      return nascimento;
  }

  public void setNascimento(LocalDateTime nascimento) {
      this.nascimento = nascimento;
  }

  public float getValorConta() {
      return valorConta;
  }

  public void setValorConta(float valorConta) {
      this.valorConta = valorConta;
  }
  
  public UserRole getRole() {
      return role;
  }

  public void setRole(UserRole role) {
      this.role = role;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
        // O Spring Security já tem algumas roles implementadas. Repare que 
        // nesse método o retorno é uma colection, então cada usuário pode ter
        // vários papéis (roles). Por exemplo, um ADMIN é ao mesmo tempo USER
        // norma. Um CHEFE é ao mesmo tempo ADMIN e USER normal, ...
        if (this.role == UserRole.ADMIN){
          return List.of(
              new SimpleGrantedAuthority("ROLE_ADMIN"),   // Admin
              new SimpleGrantedAuthority("ROLE_USER")     // é ao mesmo tempo user normal
              );
      }else{
          return List.of(new SimpleGrantedAuthority("ROLE_USER"));
      }
  }

  @Override
  public String getPassword() {
    return this.getSenha();
  }

  @Override
  public String getUsername() {
    return this.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}