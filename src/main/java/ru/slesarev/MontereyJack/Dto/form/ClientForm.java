package ru.slesarev.MontereyJack.Dto.form;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientForm {

    private String email;

    private String password;

    private String firstName;

    private String lastName;




}
