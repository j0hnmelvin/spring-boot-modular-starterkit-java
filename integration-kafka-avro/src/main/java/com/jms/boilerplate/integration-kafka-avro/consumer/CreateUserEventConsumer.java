package com.jms.boilerplate.integration.consumer;

import com.jms.boilerplate.integration.event.CreateUserEvent;
import com.jms.boilerplate.integration.mapper.UserMapper;
import com.jms.boilerplate.userservice.dto.UserDto;
import com.jms.boilerplate.userservice.service.UserService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@ConditionalOnProperty(
    prefix = "kafka.consumer",
    name = {"enabled"},
    matchIfMissing = true
)
@Service
public class CreateUserEventConsumer {
    private final UserService userService;

    public CreateUserEventConsumer(UserService userService) {
        this.userService = userService;
    }

    @KafkaListener(topics = "${kafka.topics.create-user-events}", containerFactory =
            "createUserEventContainerFactory")
    void onCreateUserEvent(ConsumerRecord<String, CreateUserEvent> consumerRecord) {
        var createUserEvent = consumerRecord.value();
        System.out.println("CreateUserEvent Processing Key: " + consumerRecord.key() + "    Value: " + createUserEvent.toString());
        UserDto userDto = userService.createUser(UserMapper.INSTANCE.toUserDto(createUserEvent));
        System.out.println("UserDto: " + userDto.toString());
    }
}
