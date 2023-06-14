package com.example.memberboardproject.service.swService;

import com.example.memberboardproject.repository.SwRepository.SwBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SwBoardService {
    private final SwBoardRepository swBoardRepository;
}
