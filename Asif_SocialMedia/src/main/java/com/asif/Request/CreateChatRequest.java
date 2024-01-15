package com.asif.Request;

import com.asif.Entity.Chat;
import com.asif.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateChatRequest {

    private Integer  userId;


}
