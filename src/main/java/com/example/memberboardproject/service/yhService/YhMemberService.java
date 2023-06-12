package com.example.memberboardproject.service.yhService;

import com.example.memberboardproject.repository.yhRepository.YhRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class YhMemberService {
    private final YhRepository yhRepository;
}
