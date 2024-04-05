package com.jms.boilerplate.userservice.util;

import com.jms.boilerplate.userservice.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MockDataService {
    public static List<UserDto> users = new ArrayList<>();

    static {
        users.add(new UserDto("1", "John Doe", "john.doe@example.com"));
        users.add(new UserDto("2", "Jane Smith", "jane.smith@example.com"));
    }
}
