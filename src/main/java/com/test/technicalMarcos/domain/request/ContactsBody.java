package com.test.technicalMarcos.domain.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContactsBody {

    private String userId;

    private List<ContactBody> contacts;
}
