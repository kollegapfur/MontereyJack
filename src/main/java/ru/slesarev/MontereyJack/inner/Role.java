package ru.slesarev.MontereyJack.inner;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    ROLE_ADMIN("Администратор"),
    ROLE_CLIENT("Клиент");

    private String name;
}
